package org.bayisehat.rdb2fhir.core.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.bayisehat.rdb2fhir.core.fhir.Conformance;

import java.util.LinkedHashSet;
import java.util.Map;


public class MappingFileGenerator {

    private ObjectMapper objectMapper;

    private Conformance conformanceService;

    private BaseParser baseParser;

    public MappingFileGenerator(ObjectMapper objectMapper, Conformance conformanceService) {
        this.objectMapper = objectMapper;
        this.conformanceService = conformanceService;
    }


    public String generateMappingFile(BaseParser baseParser) throws Exception {
        this.baseParser = baseParser;
        ArrayNode mappingFile = objectMapper.createArrayNode();

        int i = -1;
        for (Map.Entry<String, LinkedHashSet<String>> row : baseParser.getContainerKeyList().entrySet()) {
            i++;
            LinkedHashSet<String> keyList = row.getValue();

            ObjectNode quintMapNode = objectMapper.createObjectNode();
            mappingFile.add(quintMapNode);

            //class
//            quintMapNode.put("class", conformanceService.getStructureDefinition(baseParser.getResourceType()).getUrl());
            quintMapNode.put("class", baseParser.getResourceType());

            //table name
//            ObjectNode tableNameNode = objectMapper.createObjectNode();
//            putTableName(tableNameNode, i);
//            quintMapNode.putIfAbsent("view", tableNameNode);

            //query
            ObjectNode queryNode = objectMapper.createObjectNode();
            putQuery(queryNode, i);
            quintMapNode.putIfAbsent("view", queryNode);

            //identifier
            ArrayNode identifierNode = objectMapper.createArrayNode();
            ObjectNode identifierListNode = objectMapper.createObjectNode();
            identifierListNode.put("path", "");
            identifierListNode.putIfAbsent("column", objectMapper.createArrayNode().add("_subject"));
            identifierNode.add(identifierListNode);
            quintMapNode.putIfAbsent("identifier", identifierNode);

            //mapping
            processMapping(keyList, quintMapNode);
        }

        return objectMapper.writeValueAsString(mappingFile);
    }

    private void putTableName(ObjectNode node, int i) {
        node.put("tableName", baseParser.getFileName() + "_" + i);
    }

    private void putQuery(ObjectNode node, int i) {
        node.put("query", "select * from " + baseParser.getFileName() + "_" + i);
    }

    private void processMapping(LinkedHashSet<String> keyList, ObjectNode node) {
        ArrayNode mappingListNode = objectMapper.createArrayNode();
        node.putIfAbsent("mapping", mappingListNode);

        int i = -1;
        for (String key : keyList) {
            i++;
            if (key.equals(baseParser.getColumnSubject())) {
                continue;
            }

            ObjectNode mappingNode = objectMapper.createObjectNode();
            if (key.equals("id")) {
                //set function for 'id'
                mappingNode.put("function", "return '" + baseParser.getId() +"'");
            }else{
                if (baseParser.getUpperCaseList().containsKey(key)) {
                    mappingNode.put("function", "return getValue('" + "a" + i +"').upper()");
                }
                mappingNode.putIfAbsent("column", objectMapper.createArrayNode().add("a" + i));
            }

            //remove underscore, for example in path contact[0].name._given[1].id => contact[0].name.given[1].id
            mappingNode.put("path", baseParser.getResourceType() + "." + key.replaceAll("_", ""));
            mappingListNode.add(mappingNode);
        }

    }

}
