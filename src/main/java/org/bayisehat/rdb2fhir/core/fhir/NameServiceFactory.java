package org.bayisehat.rdb2fhir.core.fhir;

import org.bayisehat.rdb2fhir.core.rdb2ol.RDB2OLException;
import org.hl7.fhir.r4.model.Base;

public class NameServiceFactory {
    public NameService create(Base base, String grossName) throws RDB2OLException {
        return new NameService(base, grossName);
    }

}
