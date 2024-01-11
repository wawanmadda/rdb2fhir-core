package org.bayisehat.rdb2fhir.core.fhir;

import org.apache.commons.lang3.StringUtils;
import org.bayisehat.rdb2fhir.core.rdb2ol.RDB2OLException;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r4.model.Base;
import org.hl7.fhir.r4.model.Narrative;
import org.hl7.fhir.r4.model.Property;

import java.util.stream.Stream;

public class NameService {

    private Integer index;

    private String nameRemovedIndex;

    private String slice;

    private String nameRemovedIndexSlice;

    //property is null for 'div' in Narrative
    private Property property;

    /**
     * propertyName does not contain [x], while Property.getName() may contain [x]
     * */
    private String propertyName;

    private String dataType;

    public NameService(Base base, String grossName) throws RDB2OLException {
        parseIndex(grossName);
        parseSlice(nameRemovedIndex);
        parseExplicitDataType(grossName);
        parseProperty(base, nameRemovedIndexSlice);
    }

    private void parseIndex(String str) {
        index = null;
        nameRemovedIndex = str;
        if (str.contains("[")) {
            index = Integer.parseInt(StringUtils.substringBetween(str, "[", "]"));
            nameRemovedIndex = str.substring(0, str.indexOf("["));
        }
    }

    private void parseSlice(String str) {
        slice = null;
        nameRemovedIndexSlice = str;
        if (str.contains(":")) {
            slice = str.substring(str.indexOf(":") + 1);
            nameRemovedIndexSlice = str.substring(0, str.indexOf(":"));
        }
    }

    private void parseExplicitDataType(String str) {
        dataType = str.contains("{") ? StringUtils.substringBetween(str, "{", "}") : null;
    }

    private void parseProperty(Base base, String str) throws RDB2OLException {
        /* Note, some choice data type throw exception using method getNamedProperty
         * ex: valueBoolean does not throw exception, but valueAddress throw exception
         * */
        propertyName = getPropertyName(str);

        property = null;
        try {
            property = base.getNamedProperty(propertyName.hashCode(), propertyName, true);
            if (! propertyName.equals(property.getName())) {
                //e.g. valueBoolean
                throw new FHIRException(""); //used only to get into catch block
            }

            if (property.getTypeCode().equals("Resource") && dataType == null) {
                throw new RDB2OLException("Element: " + propertyName + " should have a type declared explicitly", 1025);
            }

        } catch (FHIRException fhirException) {
            if (dataType == null) {
                //check if open type (choice), example effectiveDateTime, multipleBirthBoolean, containedPatient
                for (String dataType1 : Stream.of(OpenType.values()).map(Enum::name).toList()) {
                    if (propertyName.endsWith(dataType1)) {
                        propertyName =  propertyName.substring(0, propertyName.lastIndexOf(dataType1));
                        dataType = OpenType.valueOf(dataType1).getOriginal();
                        break;
                    }
                }

                //no property for div
                if (! (base instanceof Narrative) || ! propertyName.equals("div")) {
                    property = base.getNamedProperty(propertyName.hashCode(), propertyName, true);
                }
            }
        }

    }

    /**
     * Get only property name
     * e.g.: effective[x] => effective
     * effective[x](Period) => effective
     * effective[x](Period)[1] => effective
     * name => name
     * name[1] => name
     * name:slice => name
     * _baseDefinition => baseDefinition
     * @return String not null
     * */
    private String getPropertyName(String str) {
        /*
         * for primitive type which has extension, example: baseDefinition which has extension in _baseDefinition
         * */
        if (str.startsWith("_")) {
            str = str.substring(1);
        }

        //slice
        if (str.contains(":")) {
            str = str.substring(0, str.indexOf(":"));
        }

        //data type
        if (str.contains("{")) {
            str = str.substring(0, str.indexOf("{"));
        }

        //index
        if (str.contains("[")) {
            str = str.substring(0, str.indexOf("["));
        }

        return str;
    }

    public Integer getIndex() {
        return index;
    }

    public String getNameRemovedIndex() {
        return nameRemovedIndex;
    }

    public String getSlice() {
        return slice;
    }

    public String getNameRemovedIndexSlice() {
        return nameRemovedIndexSlice;
    }

    public Property getProperty() {
        return property;
    }

    public String getDataType() {
        return dataType;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public boolean isResourceType() {
        //property is null for 'div' in narrative
        if (property == null) {
            return false;
        }
        return property.getTypeCode().equals("Resource");
    }
}
