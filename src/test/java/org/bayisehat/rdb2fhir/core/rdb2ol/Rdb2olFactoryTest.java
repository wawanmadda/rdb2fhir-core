package org.bayisehat.rdb2fhir.core.rdb2ol;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.bayisehat.rdb2fhir.core.util.HelperTool;
import org.bayisehat.rdb2fhir.core.fhir.HasConformance;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Rdb2olFactoryTest implements HasConformance {
    private Rdb2olFactory rdb2olFactory;

    private ObjectMapper objectMapper;

    private ArrayNode rdb2olNode;

    @BeforeAll
    void setUp() throws IOException {
        rdb2olFactory = new Rdb2olFactory(new QuadrupleFactory(getConformance()), new ObjectMapper());
        objectMapper = new ObjectMapper();
    }

    @BeforeEach
    void setUpEach() {
        rdb2olNode = objectMapper.createArrayNode();
    }

    @Test
    void parseEmptyString() {
        RDB2OLException exception = assertThrows(RDB2OLException.class, () -> rdb2olFactory.create(""));
        assertEquals(1023, exception.getErrorCode());
    }

    @Test
    void parseEmptyNode() {
        RDB2OLException exception = assertThrows(RDB2OLException.class, () -> rdb2olFactory.create(objectMapper.createArrayNode()));
        assertEquals(1023, exception.getErrorCode());
    }


    @Test
    void parseWrongString() {
        assertThrows(JsonParseException.class, () -> rdb2olFactory.create("adfas"));
    }

    @Test
    void parseNoClass() {
        ObjectNode quadrupleNode = objectMapper.createObjectNode();
        rdb2olNode.add(quadrupleNode);
        quadrupleNode.put("other", "askdjflaksdjf");
        RDB2OLException exception = assertThrows(RDB2OLException.class, () -> rdb2olFactory.create(rdb2olNode));
        assertEquals(1014, exception.getErrorCode());
    }

    @Test
    void parseNoView() {
        ObjectNode quadrupleNode = objectMapper.createObjectNode();
        rdb2olNode.add(quadrupleNode);
        quadrupleNode.put("class", "Patient");
        RDB2OLException exception = assertThrows(RDB2OLException.class, () -> rdb2olFactory.create(rdb2olNode));
        assertEquals(1015, exception.getErrorCode());
    }

    @Test
    void parseViewAndTableNameExist() {
        ObjectNode quadrupleNode = objectMapper.createObjectNode();
        rdb2olNode.add(quadrupleNode);
        quadrupleNode.put("class", "Patient");
        ObjectNode viewNode = objectMapper.createObjectNode();
        quadrupleNode.put("view", viewNode);
        viewNode.put("tableName", "aaaa");
        viewNode.put("query", "asdjfaksdjf");
        RDB2OLException exception = assertThrows(RDB2OLException.class, () -> rdb2olFactory.create(rdb2olNode));
        assertEquals(1016, exception.getErrorCode());
    }

    @Test
    void parseNoTablenameAndQuery() {
        ObjectNode quadrupleNode = objectMapper.createObjectNode();
        rdb2olNode.add(quadrupleNode);
        quadrupleNode.put("class", "Patient");
        ObjectNode viewNode = objectMapper.createObjectNode();
        quadrupleNode.put("view", viewNode);
        RDB2OLException exception = assertThrows(RDB2OLException.class, () -> rdb2olFactory.create(rdb2olNode));
        assertEquals(1017, exception.getErrorCode());
    }


    @Test
    void parseNoIdentifier() {
        ObjectNode quadrupleNode = objectMapper.createObjectNode();
        rdb2olNode.add(quadrupleNode);
        quadrupleNode.put("class", "Patient");

        ObjectNode viewNode = objectMapper.createObjectNode();
        quadrupleNode.put("view", viewNode);
        viewNode.put("tableName", "aaaa");

        RDB2OLException exception = assertThrows(RDB2OLException.class, () -> rdb2olFactory.create(rdb2olNode));
        assertEquals(1018, exception.getErrorCode());
    }

    @Test
    void parseIdentifierEmpty() {
        ObjectNode quadrupleNode = objectMapper.createObjectNode();
        rdb2olNode.add(quadrupleNode);
        quadrupleNode.put("class", "Patient");

        ObjectNode viewNode = objectMapper.createObjectNode();
        quadrupleNode.put("view", viewNode);
        viewNode.put("tableName", "aaaa");

        ArrayNode identifierNode = objectMapper.createArrayNode();
        quadrupleNode.put("identifier", identifierNode);

        RDB2OLException exception = assertThrows(RDB2OLException.class, () -> rdb2olFactory.create(rdb2olNode));
        assertEquals(1018, exception.getErrorCode());
    }

    @Test
    void parseIdentifierNoPath() {
        ObjectNode quadrupleNode = objectMapper.createObjectNode();
        rdb2olNode.add(quadrupleNode);
        quadrupleNode.put("class", "Patient");

        ObjectNode viewNode = objectMapper.createObjectNode();
        quadrupleNode.put("view", viewNode);
        viewNode.put("tableName", "aaaa");

        ArrayNode identifierNode = objectMapper.createArrayNode();
        quadrupleNode.put("identifier", identifierNode);
        ObjectNode itemIdentifierNode = objectMapper.createObjectNode();
        identifierNode.add(itemIdentifierNode);

        RDB2OLException exception = assertThrows(RDB2OLException.class, () -> rdb2olFactory.create(rdb2olNode));
        assertEquals(1020, exception.getErrorCode());
    }

    @Test
    void parseIdentifierNoColumn() {
        ObjectNode quadrupleNode = objectMapper.createObjectNode();
        rdb2olNode.add(quadrupleNode);
        quadrupleNode.put("class", "Patient");

        ObjectNode viewNode = objectMapper.createObjectNode();
        quadrupleNode.put("view", viewNode);
        viewNode.put("tableName", "aaaa");

        ArrayNode identifierNode = objectMapper.createArrayNode();
        quadrupleNode.put("identifier", identifierNode);
        ObjectNode itemIdentifierNode = objectMapper.createObjectNode();
        identifierNode.add(itemIdentifierNode);
        itemIdentifierNode.put("path", "Patient.id");

        RDB2OLException exception = assertThrows(RDB2OLException.class, () -> rdb2olFactory.create(rdb2olNode));
        assertEquals(1021, exception.getErrorCode());
    }

    @Test
    void parseIdentifierColumnEmpty() {
        ObjectNode quadrupleNode = objectMapper.createObjectNode();
        rdb2olNode.add(quadrupleNode);
        quadrupleNode.put("class", "Patient");

        ObjectNode viewNode = objectMapper.createObjectNode();
        quadrupleNode.put("view", viewNode);
        viewNode.put("tableName", "aaaa");

        ArrayNode identifierNode = objectMapper.createArrayNode();
        quadrupleNode.put("identifier", identifierNode);
        ObjectNode itemIdentifierNode = objectMapper.createObjectNode();
        identifierNode.add(itemIdentifierNode);
        itemIdentifierNode.put("path", "Patient.id");
        itemIdentifierNode.put("column", objectMapper.createObjectNode());

        RDB2OLException exception = assertThrows(RDB2OLException.class, () -> rdb2olFactory.create(rdb2olNode));
        assertEquals(1005, exception.getErrorCode());
    }

    @Test
    void parseNoMapping() {
        ObjectNode quadrupleNode = objectMapper.createObjectNode();
        rdb2olNode.add(quadrupleNode);
        quadrupleNode.put("class", "Patient");

        ObjectNode viewNode = objectMapper.createObjectNode();
        quadrupleNode.put("view", viewNode);
        viewNode.put("tableName", "aaaa");

        ArrayNode identifierNode = objectMapper.createArrayNode();
        quadrupleNode.put("identifier", identifierNode);
        ObjectNode itemIdentifierNode = objectMapper.createObjectNode();
        identifierNode.add(itemIdentifierNode);
        itemIdentifierNode.put("path", "Patient.id");
        itemIdentifierNode.put("column",
                objectMapper.createArrayNode().add("column1"));

        RDB2OLException exception = assertThrows(RDB2OLException.class, () -> rdb2olFactory.create(rdb2olNode));
        assertEquals(1019, exception.getErrorCode());
    }

    @Test
    void parseMappingEmpty() {
        ObjectNode quadrupleNode = objectMapper.createObjectNode();
        rdb2olNode.add(quadrupleNode);
        quadrupleNode.put("class", "Patient");

        ObjectNode viewNode = objectMapper.createObjectNode();
        quadrupleNode.put("view", viewNode);
        viewNode.put("tableName", "aaaa");

        ArrayNode identifierNode = objectMapper.createArrayNode();
        quadrupleNode.put("identifier", identifierNode);
        ObjectNode itemIdentifierNode = objectMapper.createObjectNode();
        identifierNode.add(itemIdentifierNode);
        itemIdentifierNode.put("path", "Patient.id");
        itemIdentifierNode.put("column",
                objectMapper.createArrayNode().add("column1"));

        ArrayNode mappingNode = objectMapper.createArrayNode();
        quadrupleNode.put("mapping", mappingNode);

        RDB2OLException exception = assertThrows(RDB2OLException.class, () -> rdb2olFactory.create(rdb2olNode));
        assertEquals(1019, exception.getErrorCode());
    }

    @Test
    void parseMappingNoPath() {
        ObjectNode quadrupleNode = objectMapper.createObjectNode();
        rdb2olNode.add(quadrupleNode);
        quadrupleNode.put("class", "Patient");

        ObjectNode viewNode = objectMapper.createObjectNode();
        quadrupleNode.put("view", viewNode);
        viewNode.put("tableName", "aaaa");

        ArrayNode identifierNode = objectMapper.createArrayNode();
        quadrupleNode.put("identifier", identifierNode);
        ObjectNode itemIdentifierNode = objectMapper.createObjectNode();
        identifierNode.add(itemIdentifierNode);
        itemIdentifierNode.put("path", "Patient.id");
        itemIdentifierNode.put("column",
                objectMapper.createArrayNode().add("column1"));

        ArrayNode mappingNode = objectMapper.createArrayNode();
        quadrupleNode.put("mapping", mappingNode);
        ObjectNode itemMappingNode = objectMapper.createObjectNode();
        mappingNode.add(itemMappingNode);

        RDB2OLException exception = assertThrows(RDB2OLException.class, () -> rdb2olFactory.create(rdb2olNode));
        assertEquals(1022, exception.getErrorCode());
    }


    @Test
    void parseRDB2OLString() throws Exception {
        String rdb2olStr = HelperTool.loadFile("src/test/resources/rdb2ol/sample1.json");
        Rdb2ol rdb2ol = rdb2olFactory.create(rdb2olStr);
        assertEquals(2, rdb2ol.getQuadrupleList().size());
    }

    @Test
    void parseRDB2OLJsonNode() throws Exception {
        JsonNode node = objectMapper.readTree(HelperTool.loadFile("src/test/resources/rdb2ol/sample1.json"));
        Rdb2ol rdb2ol = rdb2olFactory.create(node);
        assertEquals(2, rdb2ol.getQuadrupleList().size());
    }
}