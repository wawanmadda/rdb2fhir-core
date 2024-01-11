package org.bayisehat.rdb2fhir.core.example.util;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public interface BaseParser {

    void parse(String stringJson) throws Exception;

    LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>> getContainerKeyValueList();

    LinkedHashMap<String, LinkedHashSet<String>> getContainerKeyList();
    String getResourceType();

    String getColumnSubject();

    String getFileName();

    default LinkedHashMap<String, String> getUpperCaseList() {
        return new LinkedHashMap<>();
    }

    default String getId() {
        return null;
    }
}
