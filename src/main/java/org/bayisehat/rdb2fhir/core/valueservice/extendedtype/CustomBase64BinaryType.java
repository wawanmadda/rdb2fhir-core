package org.bayisehat.rdb2fhir.core.valueservice.extendedtype;

import org.hl7.fhir.r4.model.Base64BinaryType;

public class CustomBase64BinaryType extends Base64BinaryType {

    private String originalValue = null;

    @Override
    public void setValueAsString(String theValue) throws IllegalArgumentException {
        originalValue = theValue;
        super.setValueAsString(theValue);
    }

    @Override
    public String getValueAsString() {
        return originalValue;
    }

    @Override
    public String asStringValue() {
        return originalValue;
    }
}
