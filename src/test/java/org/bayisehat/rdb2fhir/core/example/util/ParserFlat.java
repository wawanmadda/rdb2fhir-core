package org.bayisehat.rdb2fhir.core.example.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ParserFlat implements BaseParser {

    /**
     * "" (empty string) is identifier for list of key and value in parent/primary table/workspace
     * child workspace use target path which is list (more than threshold/max limit) as identifier
     * */
    private LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>> containerKeyValueList;

    /**
     * LinkedHashMap<identifier, List of key>
     *     "" (empty string) is identifier for list of key in parent/primary table/workspace
     *     child workspace use target path which is list (more than threshold/max limit) as identifier
     *
     * */
    private LinkedHashMap<String, LinkedHashSet<String>> containerKeyList;

    private String resourceType;

    private final String columnSubject = "_subject";

    private final int columnLimitPerTable = 150;

    private String fileName;

    /**
     * Max list size threshold for creating a new table
     * */
    private final int maxList = 8;

    private final ObjectMapper objectMapper;

    public ParserFlat(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void parse(String jsonString) throws Exception {
        containerKeyList = new LinkedHashMap<>();
        containerKeyValueList = new LinkedHashMap<>();
        resourceType = null;

        Path pathObj = Path.of(jsonString);
        fileName = pathObj.getFileName().toString();
        fileName = fileName.substring(0, fileName.lastIndexOf("."));
        fileName = "a" + Math.abs(fileName.hashCode());
        JsonNode rootNode = objectMapper.readTree(Files.readString(pathObj, StandardCharsets.UTF_8));


        //remove resourceType property
        resourceType = rootNode.get("resourceType").asText();
        ((ObjectNode)rootNode).remove("resourceType");
        parseNode(rootNode);

        alignKey();
        alignValue();

        splitKey();
        splitValue();
    }

    private void parseNode(JsonNode node) {
        ArrayList<LinkedHashMap<String, String>> container = new ArrayList<>();
        container.add(new LinkedHashMap<>());
        containerKeyValueList.put("", container);

        LinkedHashSet<String> containerKey = new LinkedHashSet<>();
        containerKey.add(columnSubject); //id/subject in a table

        /**
         * "" (empty string) is identifier for list of key in parent/primary table/workspace
         * */
        containerKeyList.put("", containerKey);

        traverse(node, "", 0, container, containerKey, true);
    }





    private void traverse(JsonNode node, String keyAccumulation, int indexInContainer,
                  ArrayList<LinkedHashMap<String, String>> container,
                  LinkedHashSet<String> containerKey, boolean hasPassedListThreshold) {
        if (node.isObject()) {
            traverseObject(node, keyAccumulation, indexInContainer, container, containerKey, hasPassedListThreshold);
            return;
        }

        traverseArray(node, keyAccumulation, indexInContainer, container, containerKey, hasPassedListThreshold);
    }



    private void traverseArray(JsonNode node, String keyAccumulation, int indexInContainer,
                       ArrayList<LinkedHashMap<String, String>> container,
                       LinkedHashSet<String> containerKey, boolean hasPassedListThreshold) {
//        if (keyAccumulation.contains("item[0].item")) {
//            String a = "b";
//        }

        String keyWithoutLastSquareBracket = keyAccumulation.replaceAll("\\[\\d+\\]$", "");

        int nodeSize = node.size(); //maximum item in list before split into new mapping / new child / new table
        if (! isSpecialElement(keyWithoutLastSquareBracket) && node.size() > maxList && ! hasPassedListThreshold) {
//            firstLevelListKey.put(keyWithoutLastSquareBracket, new LinkedHashSet<>());
            container = new ArrayList<>();
            indexInContainer = -1; //reset index in new container

            containerKey = new LinkedHashSet<>();
            containerKey.add(columnSubject);
        }

        boolean tempHasPassedListThreshold = hasPassedListThreshold == true ? true : ! isSpecialElement(keyWithoutLastSquareBracket) && nodeSize > maxList;
        int index = -1;
        for (JsonNode row : node) {
            index++;

            String nextKeyAccumulation = keyAccumulation;
            if (isSpecialElement(keyWithoutLastSquareBracket)) {
                nextKeyAccumulation = nextKeyAccumulation + "{" + row.get("resourceType").asText() + "}";
                ((ObjectNode) row).remove("resourceType");
            }

            nextKeyAccumulation = nextKeyAccumulation + "[" + index + "]";
            if (! isSpecialElement(keyWithoutLastSquareBracket) && nodeSize > maxList && ! hasPassedListThreshold) {
                container.add(new LinkedHashMap<>());
                indexInContainer++;
                nextKeyAccumulation = keyWithoutLastSquareBracket;
            }

            if (!row.isValueNode()) {
                /** hasPassedListThreshold (true) is used to limit adding new container after first match in parent  */
                traverse(row, nextKeyAccumulation, indexInContainer, container, containerKey, tempHasPassedListThreshold);
                continue;
            }

            //case: contact[0].name._given[0] in json-edge-cases
            //case: us example capabilitystatement_us_core_client : _implementationGuide
            //dont add if value is null && last element contain underscore
            String lastElement = nextKeyAccumulation.contains(".") ? nextKeyAccumulation.substring(nextKeyAccumulation.lastIndexOf(".") + 1) : nextKeyAccumulation;
            if (row.isNull() && lastElement.contains("_")) {
                //do nothing
            } else{
                container.get(indexInContainer).put(nextKeyAccumulation, row.isNull() ? null : row.asText());
                containerKey.add(nextKeyAccumulation);
            }

        }

        if (! isSpecialElement(keyWithoutLastSquareBracket) && nodeSize > maxList && ! hasPassedListThreshold) {
            containerKeyValueList.put(keyWithoutLastSquareBracket, container);
            containerKeyList.put(keyWithoutLastSquareBracket, containerKey);
        }

    }


    private void traverseObject(JsonNode node, String keyAccumulation, int indexInContainer,
                        ArrayList<LinkedHashMap<String, String>> container,
                        LinkedHashSet<String> containerKey, boolean hasPassedListThreshold) {
        Iterator<String> iteratorFieldNames = node.fieldNames();
        List<String> fieldNameList = new ArrayList<>();
        iteratorFieldNames.forEachRemaining(fieldNameList::add);

        for (String fieldName: fieldNameList) {
            String key = fieldName;
            JsonNode value = node.get(key);
            String nextKeyAccumulation = keyAccumulation.equals("") ? keyAccumulation + key : String.join(".", keyAccumulation, key);

            //resource parameters has element at path Parameters.parameter.resource
            //@todo what if not only Parameters.parameter.resource ?
            if ((resourceType.equals("Parameters") || resourceType.equals("Bundle")) &&
                    key.equals("resource") || key.equals("issues") || key.equals("outcome")) {
                if (value.get("resourceType") != null) {
                    nextKeyAccumulation = nextKeyAccumulation + "{" + value.get("resourceType").asText() + "}";
                    ((ObjectNode) value).remove("resourceType");
                }

            }

            if (!value.isValueNode()) {
                traverse(value, nextKeyAccumulation, indexInContainer, container, containerKey, hasPassedListThreshold);
                continue;
            }
            container.get(indexInContainer).put(nextKeyAccumulation, value.asText());
            containerKey.add(nextKeyAccumulation);
        }
    }



    private void alignValue() {
        LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>> newContainerKeyValueList = new LinkedHashMap<>();

        int indexSubject = 1;
        Iterator<Map.Entry<String, ArrayList<LinkedHashMap<String, String>>>> iterator = containerKeyValueList.entrySet().iterator();
        /** for every table/workspace*/
        while (iterator.hasNext()) {
            Map.Entry<String, ArrayList<LinkedHashMap<String, String>>> entry = iterator.next();
            String group = entry.getKey();
            ArrayList<LinkedHashMap<String, String>> keyValueList1 = entry.getValue(); //keyValueList from instanceComponentParser

            ArrayList<LinkedHashMap<String, String>> newKeyValueList = new ArrayList<>();
            /** For every row in table*/
            for (LinkedHashMap<String, String> keyValue1 : keyValueList1) {
                LinkedHashSet<String> alignedKeyList = containerKeyList.get(group);

                LinkedHashMap<String, String> newKeyValue = new LinkedHashMap<>();

                for (String alignedKey : alignedKeyList) {
                    if (alignedKey.equals(columnSubject)) {
                        newKeyValue.put(alignedKey, String.valueOf(indexSubject));
                    }else{
                        newKeyValue.put(alignedKey, keyValue1.get(alignedKey));
                    }
                }
                newKeyValueList.add(newKeyValue);
            }
            newContainerKeyValueList.put(group, newKeyValueList);
        }

        containerKeyValueList = newContainerKeyValueList;
    }

    private void alignKey() {
        LinkedHashMap<String, LinkedHashSet<String>> newContainerKeyList = new LinkedHashMap<>();

        Iterator<Map.Entry<String, LinkedHashSet<String>>> iterator = containerKeyList.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, LinkedHashSet<String>> entry = iterator.next();
            String group = entry.getKey();
            LinkedHashSet<String> keyList1 = entry.getValue(); //keyList from instanceComponentParser

            LinkedHashSet<String> currentKeyList = newContainerKeyList.get(group); //keyList from all instanceComponentParser
            if (currentKeyList == null) {
                newContainerKeyList.put(group, keyList1);
            }else{
                currentKeyList.addAll(keyList1);
            }
        }

        containerKeyList = newContainerKeyList;
    }

    private void splitKey() {
        for (Map.Entry<String, LinkedHashSet<String>> entry : containerKeyList.entrySet()) {
            LinkedHashSet<String> keyList = entry.getValue();

            if (keyList.size() > columnLimitPerTable) {
                LinkedHashSet<String> group = new LinkedHashSet<>();
                int groupId = 0;
                for (String key : keyList) {
                    if (group.size() == columnLimitPerTable) {
                        containerKeyList.put(entry.getKey() + "_" + groupId , group);
                        group = new LinkedHashSet<>();
                        groupId++;
                    }

                    if (group.isEmpty()) {
                        group.add(columnSubject);
                    }
                    if (group.size() < columnLimitPerTable) {
                        group.add(key);
                    }
                }
                containerKeyList.put(entry.getKey() + "_" + groupId , group);

                //remove parent
                containerKeyList.remove(entry.getKey());
            }

        }
    }

    private void splitValue() {
        /** For every table*/
        for (Map.Entry<String, ArrayList<LinkedHashMap<String, String>>> entry : containerKeyValueList.entrySet()) {
            ArrayList<LinkedHashMap<String, String>> containerKeyValue = entry.getValue();
            int groupSize = containerKeyValue.get(0).size();

            if (groupSize > columnLimitPerTable) {
                ArrayList<LinkedHashMap<String, String>> arr = new ArrayList<>();
                String subjectVal= null;

                /** For every row in table*/
                for (LinkedHashMap<String, String> keyValue : containerKeyValue) {
                    LinkedHashMap<String, String> group = new LinkedHashMap<>();
                    int groupId = 0;

                    /** For every column with value*/
                    for (Map.Entry<String, String> entry1 : keyValue.entrySet()) {
                        String columnName = entry1.getKey();
                        String value = entry1.getValue();

                        if (group.size() == columnLimitPerTable) {
                            arr.add(group);
                            containerKeyValueList.put(entry.getKey() + "_" + groupId, arr);
                            group = new LinkedHashMap<>();
                            arr = new ArrayList<>();
                            groupId++;
                        }

                        if (subjectVal == null && columnName.equals(columnSubject)) {
                            subjectVal = value;
                        }

                        if (group.isEmpty()) {
                            group.put(columnSubject, subjectVal);
                        }
                        if (group.size() < columnLimitPerTable) {
                            group.put(columnName, value);
                        }
                    }
                    arr.add(group);
                    containerKeyValueList.put(entry.getKey() + "_" + groupId , arr);
                }

                /** Remove old containerKeyValue*/
                containerKeyValueList.remove(entry.getKey());

            }

        }

    }

    private boolean isSpecialElement(String str) {
        return str.endsWith("contained");
    }


    @Override
    public LinkedHashMap<String, ArrayList<LinkedHashMap<String, String>>> getContainerKeyValueList() {
        return containerKeyValueList;
    }

    @Override
    public LinkedHashMap<String, LinkedHashSet<String>> getContainerKeyList() {
        return containerKeyList;
    }

    @Override
    public String getResourceType() {
        return resourceType;
    }

    @Override
    public String getColumnSubject() {
        return columnSubject;
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
