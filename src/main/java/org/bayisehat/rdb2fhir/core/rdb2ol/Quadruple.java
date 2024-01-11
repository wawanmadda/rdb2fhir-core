package org.bayisehat.rdb2fhir.core.rdb2ol;

import org.bayisehat.rdb2fhir.core.fhir.ConformanceException;
import org.bayisehat.rdb2fhir.core.util.FhirTool;
import org.bayisehat.rdb2fhir.core.fhir.Conformance;
import org.bayisehat.rdb2fhir.core.fhir.NameService;
import org.bayisehat.rdb2fhir.core.fhir.NameServiceFactory;
import org.hl7.fhir.r4.model.Base;
import org.hl7.fhir.r4.model.Property;
import org.hl7.fhir.r4.model.ResourceFactory;
import org.hl7.fhir.r4.model.StructureDefinition;

import java.util.*;

/**
 * This class represent a quadruple, an item in RDB2OL
 * Each quadruple must consist of: class, view (tableName or query), identifier, and mapping item
 * */
public class Quadruple {


    private StructureDefinition structureDefinition;

    private String className;

    /**
     * SQL query represent a table create on the fly
     * */
    private String query;

    /**
     * Table name that exist in database
     * */
    private String tableName;

    /**
     * Identifiers represent FHIR instance identifier, that are used to identify instances uniquely
     * */
    private final HashSet<Identifier> identifierList = new HashSet<>();

    /**
     * Mapping from columns to FHIR elements
     * */
    private final ArrayList<MappingItem> mappingItemList = new ArrayList<>();

    /**
     * Map consists of one source/column to one or more FHIR elements
     * */
    private final HashMap<String, HashSet<String>> columnToPathList = new HashMap<>();

    /**
     * Map contains FHIRPath that is added to lazy targets.
     * Lazy paths are FHIRPath that contain an element of type collection which is accessed
     * without an index.
     * */
    private final HashSet<String> lazyPathList = new HashSet<>();


    /**
     * All columns that are used in Identifier and mapping item
     * */
    private final HashSet<String> columnNameList = new HashSet<>();


    /**
     * A Map of a FHIR element to a function
     * */
    private final HashMap<String, String> pathToFunction = new HashMap<>();

    private final IdentifierFactory identifierFactory;

    private final MappingItemFactory mappingItemFactory;

    private final NameServiceFactory nameServiceFactory;

    private final Conformance conformanceService;

    private final PathServiceFactory pathServiceFactory;

    public Quadruple(Conformance conformanceService, IdentifierFactory identifierFactory,
                     MappingItemFactory mappingItemFactory, NameServiceFactory nameServiceFactory,
                     PathServiceFactory pathServiceFactory) {
        this.conformanceService = conformanceService;
        this.identifierFactory = identifierFactory;
        this.mappingItemFactory = mappingItemFactory;
        this.nameServiceFactory = nameServiceFactory;
        this.pathServiceFactory = pathServiceFactory;
    }

    /**
     * Set resource type for mapping
     * @param resource URL, resourceType, or ID of StructureDefinition
     * */
    public Quadruple setClass(String resource) throws RDB2OLException {
        try {
            structureDefinition = conformanceService.getStructureDefinition(resource);
            className = resource;
        } catch (ConformanceException e) {
            throw new RDB2OLException("Invalid RDB2OL: Resource type couldn't be found for: " + resource, 1001);
        }
        return this;
    }

    /**
     * Set resource type for mapping
     * */
    public Quadruple setClass(StructureDefinition structureDefinition) {
        this.structureDefinition = structureDefinition;
        return this;
    }

    public StructureDefinition getStructureDefinition() {
        return structureDefinition;
    }

    /**
     * Set table name as data source for mapping
     * @param tableName table name that exists in the database
     * */
    public Quadruple setTableName(String tableName) throws RDB2OLException {
        if (query != null) {
            throw new RDB2OLException("Invalid RDB2OL: Cannot use the table since a query already defined", 1002);
        }
        this.tableName = tableName;
        return this;
    }

    /**
     * Set query as data source for mapping
     * @param query SQL query
     * */
    public Quadruple setQuery(String query) throws RDB2OLException {
        if (tableName != null) {
            throw new RDB2OLException("Invalid RDB2OL: Cannot use the query since a table already defined", 1003);
        }
        this.query = query;
        return this;
    }

    /**
     * Add identifier or the quadruple,
     * There should be one identifier which has one path for root
     * */
    public Quadruple addIdentifier(String path, Set<String> columnList) throws RDB2OLException {
        if (structureDefinition == null) {
            throw new RDB2OLException("Set a 'class' component first", 1004);
        }

        if (columnList.size() == 0) {
            throw new RDB2OLException("Invalid RDB2OL: At least one column must be provided", 1005);
        }

        String prefix = structureDefinition.getType();
        String pathWithoutPrefix = path.equals(prefix) ? "": path;
        if (path.startsWith(prefix + ".")) {
            pathWithoutPrefix = path.replaceFirst(prefix + ".", "");
        }

        PathService pathService = pathServiceFactory.create().load(prefix, pathWithoutPrefix);

        Identifier identifier = identifierFactory.create(pathService, columnList);
        for (Identifier identifier1 : identifierList) {
            if (identifier1.getPath().getAppPath().equals(pathService.getAppPath())) {
                throw new RDB2OLException("Invalid RDB2OL: Duplicate identifier at path " + path, 1006);
            }
        }

        columnNameList.addAll(columnList);
        identifierList.add(identifier);
        return this;
    }

    /**
     * Add Mapping,
     * Each mapping must contains a path, a function xor list of columns
     * @param grossPath full qualified path or relative path. Open type can be explicit or implicitly declared
     * */
    public Quadruple addMapping(String grossPath, String function, Set<String> columnList) throws RDB2OLException {
        if (structureDefinition == null) {
            throw new RDB2OLException("Set a 'class' component first", 1007);
        }

        if (grossPath == null || grossPath.isEmpty()) {
            throw new RDB2OLException("Invalid RDB2OL: A 'path' item must be provided", 1008);
        }

        if (function == null && (columnList == null || columnList.isEmpty())) {
            throw new RDB2OLException("Invalid RDB2OL: A 'mapping' item must consists of 'path' and a function or columns", 1009);
        }

        if (function == null && columnList.size() > 1) {
            throw new RDB2OLException("Invalid RDB2OL: Multiple columns mapped to a 'path' component must have a 'function' component. Path :" + grossPath, 1010);
        }

        String removedPrefixPath = FhirTool.removePrefixClassName(structureDefinition.getType(), grossPath);
        if (removedPrefixPath.isEmpty()) {
            throw new RDB2OLException("Invalid RDB2OL: You cannot map to root path", 1023);
        }

        PathService path = pathServiceFactory.create().load(structureDefinition.getType(), removedPrefixPath);
        lazyPathList.addAll(path.getLazyPathList());

        MappingItem mappingItem = mappingItemFactory.create(path, function, columnList);
        for (MappingItem mappingItem1 : mappingItemList) {
            if (mappingItem1.getPath().getAppPath().equals(path.getAppPath())) {
               throw new RDB2OLException("Invalid RDB2OL: Duplicated mapping to the same path at: " + grossPath, 1011);
            }
        }
        mappingItemList.add(mappingItem);

        if (columnList != null) {
            columnNameList.addAll(columnList);
            for (String column : columnList) {
                addColumnToPath(column, path.getAppPath());
            }
        }

        if (function != null) {
            addPathToFunction(path.getAppPath(), function);
        }

        return this;
    }


    /**
     * Add Mapping from a column to a FHIR element
     * */
    private void addColumnToPath(String column, String path) {
        HashSet<String> pathList = columnToPathList.computeIfAbsent(column, k -> new HashSet<>());
        pathList.add(path);
    }

    /**
     * Add mapping from a FHIR element to a function
     * */
    private void addPathToFunction(String path, String function) {
        pathToFunction.put(path, function);
    }



    /**
     * Get All columns  that are used in identifier and mapping item
     * */
    public HashSet<String> getColumnNameList() {
        return columnNameList;
    }


    public Identifier getIdentifierByPath(String relativeAppPath) {
        for (Identifier identifier : identifierList) {
            if (identifier.getPath().getAppPath().equals(relativeAppPath)) {
                return identifier;
            }
        }
        return null;
    }

    public Identifier getRootIdentifier() throws RDB2OLException {
        Identifier identifier = getIdentifierByPath("");
        if (identifier == null) {
            throw new RDB2OLException("Invalid RDB2OL: 'identifier' component cannot be empty. There must be at least 1 'identifier' component for root path", 1012);
        }

        return identifier;
    }


    public String getQuery() {
        return query;
    }

    public String getTableName() {
        return tableName;
    }

    public boolean isTableName() {
        return this.tableName != null;
    }

    public HashSet<Identifier> getIdentifierList() {
        return identifierList;
    }

    public ArrayList<MappingItem> getMappingItemList() {
        return mappingItemList;
    }

    public HashSet<String> getLazyPathList() {
        return lazyPathList;
    }

    public HashMap<String, String> getPathToFunction() {
        return pathToFunction;
    }

    public HashMap<String, HashSet<String>> getColumnToPathList() {
        return columnToPathList;
    }

    public String getClassName() {
        return className;
    }

}
