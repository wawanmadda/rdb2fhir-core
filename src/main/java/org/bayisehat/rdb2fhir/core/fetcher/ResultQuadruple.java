package org.bayisehat.rdb2fhir.core.fetcher;

import org.bayisehat.rdb2fhir.core.rdb2ol.Quadruple;

import java.util.HashMap;
import java.util.List;

public record ResultQuadruple(Quadruple quadruple, List<HashMap<String, Object>> result) {

}
