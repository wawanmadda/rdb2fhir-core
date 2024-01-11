package org.bayisehat.rdb2fhir.core.rdb2ol;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.jena.sparql.core.Quad;

public class Rdb2olFactory {

    private ObjectMapper objectMapper;

    private QuadrupleFactory quadrupleFactory;

    public Rdb2olFactory(QuadrupleFactory quadrupleFactory, ObjectMapper objectMapper) {
        this.quadrupleFactory = quadrupleFactory;
        this.objectMapper = objectMapper;

    }

    public Rdb2ol create() {
        return new Rdb2ol(objectMapper);
    }

    public Rdb2ol create(String rdb2ol) throws JsonProcessingException, RDB2OLException {
        return create(objectMapper.readTree(rdb2ol));
    }

    public Rdb2ol create(JsonNode rdb2olNode) throws RDB2OLException {
        if (rdb2olNode.isEmpty()) {
            throw new RDB2OLException("Invalid RDB20L: No quadruple provided", 1023);
        }

        Rdb2ol rdb2ol = new Rdb2ol(objectMapper);
        for (JsonNode quadrupleNode : rdb2olNode) {
            Quadruple quadruple = quadrupleFactory.create(quadrupleNode);
            rdb2ol.addQuadruple(quadruple);
        }

        if (rdb2ol.getQuadrupleList().isEmpty()) {
            throw new RDB2OLException("Invalid RDB20L: No quadruple provided", 1024);
        }


        return rdb2ol;
    }

}
