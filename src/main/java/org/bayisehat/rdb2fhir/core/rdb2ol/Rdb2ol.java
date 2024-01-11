package org.bayisehat.rdb2fhir.core.rdb2ol;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.jena.sparql.core.Quad;

import java.util.ArrayList;
import java.util.HashSet;

public class Rdb2ol {

    private ArrayList<Quadruple> quadrupleList = new ArrayList<>();

    private ObjectMapper objectMapper;

    public Rdb2ol(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public enum PathType {
        APP, FHIR
    }

    public enum PathNaming {
        RELATIVE, FULL
    }

    public void addQuadruple(Quadruple quadruple) {
        quadrupleList.add(quadruple);
    }

    public ArrayList<Quadruple> getQuadrupleList() {
        return quadrupleList;
    }

    private void setViewNode(ObjectNode quadrupleNode, Quadruple quadruple) {
        ObjectNode viewNode = objectMapper.createObjectNode();
        quadrupleNode.put("view", viewNode);
        if (quadruple.getTableName() != null) {
            viewNode.put("tableName", quadruple.getTableName());
        }else{
            viewNode.put("query", quadruple.getQuery());
        }
    }

    private void setIdentifierNode(ObjectNode quadrupleNode, Quadruple quadruple, PathType pathType, PathNaming pathNaming) {
        ArrayNode identifierListNode = objectMapper.createArrayNode();
        quadrupleNode.put("identifier", identifierListNode);
        for (Identifier identifier : quadruple.getIdentifierList()) {
            ObjectNode identifierNode = objectMapper.createObjectNode();
            identifierListNode.add(identifierNode);
            identifierNode.put("path", getPath(identifier.getPath(), pathType, pathNaming));
            identifierNode.put("column", objectMapper.valueToTree(identifier.getColumnList()));
        }
    }

    private void setMappingNode(ObjectNode quadrupleNode, Quadruple quadruple, PathType pathType, PathNaming pathNaming) {
        ArrayNode mappingListNode = objectMapper.createArrayNode();
        quadrupleNode.put("mapping", mappingListNode);
        for (MappingItem mappingItem : quadruple.getMappingItemList()) {
            ObjectNode mappingItemNode = objectMapper.createObjectNode();
            mappingListNode.add(mappingItemNode);
            mappingItemNode.put("path", getPath(mappingItem.getPath(), pathType, pathNaming));
            if (mappingItem.hasFunction()) {
                mappingItemNode.put("function", mappingItem.getFunction());
            }
            if (! mappingItem.getColumnList().isEmpty()) {
                mappingItemNode.put("column", objectMapper.valueToTree(mappingItem.getColumnList()));
            }
        }
    }

    private String getPath(PathService path, PathType pathType, PathNaming pathNaming) {
        if (pathType == PathType.APP && pathNaming == PathNaming.RELATIVE) {
            return path.getAppPath();
        }

        if (pathType == PathType.APP && pathNaming == PathNaming.FULL) {
            return path.getAppFullPath();
        }

        if (pathType == PathType.FHIR && pathNaming == PathNaming.RELATIVE) {
            return path.getFhirPath();
        }

        if (pathType == PathType.FHIR && pathNaming == PathNaming.FULL) {
            return path.getFhirFullPath();
        }

        return null;
    }

    public JsonNode asJson(PathType pathType, PathNaming pathNaming) {
        ArrayNode retVal = objectMapper.createArrayNode();
        for (Quadruple quadruple : quadrupleList) {
            ObjectNode quadrupleNode = objectMapper.createObjectNode();
            retVal.add(quadrupleNode);

            //class
            quadrupleNode.put("class", quadruple.getClassName());

            setViewNode(quadrupleNode, quadruple);
            setIdentifierNode(quadrupleNode, quadruple, pathType, pathNaming);
            setMappingNode(quadrupleNode, quadruple, pathType, pathNaming);
        }
        return retVal;
    }

    public String asText(PathType pathType, PathNaming pathNaming) throws JsonProcessingException {
        return objectMapper.writeValueAsString(asJson(pathType, pathNaming));
    }

}

