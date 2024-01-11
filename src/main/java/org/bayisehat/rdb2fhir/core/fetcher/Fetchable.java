package org.bayisehat.rdb2fhir.core.fetcher;

import org.bayisehat.rdb2fhir.core.rdb2ol.Quadruple;
import org.bayisehat.rdb2fhir.core.rdb2ol.Rdb2ol;

import java.util.ArrayList;

public interface Fetchable {

    ArrayList<ResultQuadruple> fetch(Rdb2ol rdb2ol);

}
