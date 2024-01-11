package org.bayisehat.rdb2fhir.core.valueservice;

import org.bayisehat.rdb2fhir.core.rdb2ol.Quadruple;

import java.util.HashMap;

public record KeyValueWrapper(Quadruple quadruple, HashMap<String, Object> rowResult,
                              HashMap<String, String> keyValueList) {

}
