package org.bayisehat.rdb2fhir.core.rdb2ol;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.jena.tdb.store.Hash;
import org.bayisehat.rdb2fhir.core.fhir.*;

import java.util.ArrayList;
import java.util.HashSet;


public class QuadrupleFactory {

    private final Conformance conformanceService;

    private final IdentifierFactory identifierFactory;

    private final MappingItemFactory mappingItemFactory;

    private final NameServiceFactory nameServiceFactory;

    private final PathServiceFactory pathServiceFactory;

    public QuadrupleFactory(Conformance conformance) {
        conformanceService = conformance;
        this.identifierFactory = new IdentifierFactory();
        this.mappingItemFactory = new MappingItemFactory();
        this.nameServiceFactory = new NameServiceFactory();
        this.pathServiceFactory = new PathServiceFactory();

    }

    public Quadruple create() {
        return new Quadruple(conformanceService, identifierFactory, mappingItemFactory, nameServiceFactory, pathServiceFactory);
    }

    public Quadruple create(JsonNode quadrupleNode) throws RDB2OLException {
        Quadruple quadruple = create();
        setClass(quadruple, quadrupleNode);
        setView(quadruple, quadrupleNode);
        setIdentifier(quadruple, quadrupleNode);
        setMapping(quadruple, quadrupleNode);

        return quadruple;
    }

    private void setClass(Quadruple quadruple, JsonNode quadrupleNode) throws RDB2OLException {
        //className
        JsonNode classNode = quadrupleNode.get("class");
        if (classNode == null) {
            throw new RDB2OLException("Invalid RDB2OL: A quadruple must have a 'class' component", 1014);
        }
        String className = quadrupleNode.get("class").asText();
        quadruple.setClass(className);
    }

    private void setView(Quadruple quadruple, JsonNode quadrupleNode) throws RDB2OLException {
        //view
        JsonNode viewNode = quadrupleNode.get("view");
        if (viewNode == null) {
            throw new RDB2OLException("Invalid RDB2OL: A quadruple must have a 'view' component", 1015);
        }
        String value;
        if (viewNode.has("tableName") && viewNode.has("query")) {
            throw new RDB2OLException("Invalid RDB20L: A 'view' component should have a 'tableName' or 'query' and not both", 1016);
        }

        if (viewNode.has("tableName")) {
            value = viewNode.get("tableName").asText();
            quadruple.setTableName(value);
            return;
        }

        if (viewNode.has("query")) {
            value = viewNode.get("query").asText();
            quadruple.setQuery(value);
            return;
        }

        throw new RDB2OLException("Invalid RDB20L: Unknown component in 'view' component", 1017);
    }

    private void setIdentifier(Quadruple quadruple, JsonNode quadrupleNode) throws RDB2OLException {
        JsonNode identifierNode = quadrupleNode.get("identifier");
        if (identifierNode == null || identifierNode.isEmpty()) {
            throw new RDB2OLException("Invalid RDB20L: A quadruple must have an 'identifier' component and cannot be empty", 1018);
        }
        parseIdentifierNode(quadruple, quadrupleNode.get("identifier"));
    }

    private void setMapping(Quadruple quadruple, JsonNode quadrupleNode) throws RDB2OLException {
        JsonNode mappingNode = quadrupleNode.get("mapping");
        if (mappingNode == null || mappingNode.isEmpty()) {
            throw new RDB2OLException("Invalid RDB20L: A quadruple must have a 'mapping' component and cannot be empty", 1019);
        }
        parseMappingNode(quadruple, quadrupleNode.get("mapping"));
    }

    private void parseIdentifierNode(Quadruple quadruple, JsonNode identifierNode) throws RDB2OLException {
        for (JsonNode identifierItemNode : identifierNode) {
            JsonNode pathNode = identifierItemNode.get("path");
            if (pathNode == null) {
                throw new RDB2OLException("Invalid RDB20L: An 'identifier' component must have a 'path' component", 1020);
            }
            String path = pathNode.asText();

            JsonNode columnListNode = identifierItemNode.get("column");
            if (columnListNode == null) {
                throw new RDB2OLException("Invalid RDB20L: An 'identifier' component must have a 'column' component", 1021);
            }
            HashSet<String> columnlist = new HashSet<>();
            for (JsonNode columnNode : columnListNode) {
                columnlist.add(columnNode.asText());
            }

            quadruple.addIdentifier(path, columnlist);
        }
    }

    private void parseMappingNode(Quadruple quadruple, JsonNode mappingNode) throws RDB2OLException {
        for (JsonNode mappingItemNode : mappingNode) {
            String function = null;
            if (mappingItemNode.has("function")) {
                function = mappingItemNode.get("function").asText();
            }

            //column
            HashSet<String> columnList = new HashSet<>();
            if (mappingItemNode.has("column")) {
                for (JsonNode sourceNode : mappingItemNode.get("column")) {
                    columnList.add(sourceNode.asText());
                }
            }

            //path
            JsonNode pathNode = mappingItemNode.get("path");
            if (pathNode == null) {
                throw new RDB2OLException("Invalid RDB20L: A 'mapping' item must have a 'path' component", 1022);
            }

            quadruple.addMapping(pathNode.asText(), function, columnList);
        }
    }


}
