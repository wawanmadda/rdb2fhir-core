package org.bayisehat.rdb2fhir.core.rdb2ol;

import java.util.HashSet;
import java.util.Set;

/**
 * A Mapping item contains mapping from zero or one function, zero or more column to a FHIR element
 * */
public class MappingItem {

    /**
     * A FHIR element path
     * */
    private final PathService path;

    /**
     * Python function that returns a value
     * */
    private final String function;

    /**
     * Columns that are mapped to a FHIR element
     * */
    private final HashSet<String> columnList = new HashSet<>();

//    public MappingItem(String path, Set<String> columnList) {
//        this(path, null, columnList);
//    }
//
//    public MappingItem(String path, String function) {
//        this(path, function, new HashSet<>());
//    }

//    public MappingItem(String path, String function, Set<String> columnList) {
//        this.path = path;
//        this.function = function;
//        if (columnList != null) {
//            this.columnList.addAll(columnList);
//        }
//    }

    public MappingItem(PathService path, String function, Set<String> columnList) {
        this.path = path;
        this.function = function;
        if (columnList != null) {
            this.columnList.addAll(columnList);
        }
    }

    /**
     * Return a relative path
     * */
    public PathService getPath() {
        return path;
    }

    public String getFunction() {
        return function;
    }

    public boolean hasFunction() {
        return function != null;
    }

    public HashSet<String> getColumnList() {
        return columnList;
    }

    public void addColumnList(String source) {
        columnList.add(source);
    }
}
