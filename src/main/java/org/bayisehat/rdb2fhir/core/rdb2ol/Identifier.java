package org.bayisehat.rdb2fhir.core.rdb2ol;

import java.util.ArrayList;
import java.util.Set;

/**
 * Identifier use resource type, columns, and its values as unique identifier for all resource instances
 * Identifier can be stacked
 * */
public class Identifier {

    /**
     * Columns used as identifier for a path
     * Must preserve insertion order */
    private final ArrayList<String> columnList = new ArrayList<>();

    /**
     * A Path represent FHIRPath.
     * Resource type can be omitted
     * */
    private final PathService path;

    public Identifier(PathService path, Set<String> columnList) {
        this.path = path;
        this.columnList.addAll(columnList);
    }

    public ArrayList<String> getColumnList() {
        return new ArrayList<>(columnList);
    }

    public void addColumn(String column) {
        columnList.add(column);
    }

    public PathService getPath() {
        return path;
    }

}
