package org.bayisehat.rdb2fhir.core.valueservice;

import org.bayisehat.rdb2fhir.core.util.FhirTool;
import org.bayisehat.rdb2fhir.core.fhir.NameService;
import org.bayisehat.rdb2fhir.core.fhir.NameServiceFactory;
import org.bayisehat.rdb2fhir.core.rdb2ol.RDB2OLException;
import org.bayisehat.rdb2fhir.core.valueservice.extendedtype.CustomBase64BinaryType;
import org.bayisehat.rdb2fhir.core.valueservice.extendedtype.CustomXhtmlNode;
import org.bayisehat.rdb2fhir.core.rdb2ol.Quadruple;
import org.hl7.fhir.instance.model.api.IPrimitiveType;
import org.hl7.fhir.r4.model.*;
import org.bayisehat.rdb2fhir.core.rdb2ol.Identifier;

import java.util.*;


/**
 * This class aims to fill the model instances with values
 * */
public class Allocator {

    private String currentProcessedPath;

    private final NameServiceFactory nameServiceFactory;

    /**
     * Temporary hold object base, only to list data type
     * To allow assigning one-to-many relationship
     * HashMap<element, Base>
     * ex: [ name => object,
     * name.extension => object ]
     * reset in every row (every call to seed())
     */
    private HashMap<String, Base> nodeBox;

    private Quadruple quadruple;

    private HashMap<String, Object> rowResult;

    private InstancePool instancePool;

    private HashSet<String> columnIdentifierList;

    public Allocator(NameServiceFactory nameServiceFactory){
        this.nameServiceFactory = nameServiceFactory;
    }

    /**
     * Fill model with values
     * This method will directly modify Instances in InstancePool
     */
    public void fill(InstancePool instancePool, KeyValueWrapper keyValueWrapper) throws InstanceException, ValueAssignmentException, RDB2OLException {
        nodeBox = new HashMap<>(); //reset
        this.instancePool = instancePool;
        quadruple = keyValueWrapper.quadruple();

        //row result is needed since it may be used by the identifier
        rowResult = keyValueWrapper.rowResult();

        for (Map.Entry<String, String> entry : keyValueWrapper.keyValueList().entrySet()) {
            //reset every seed to a path
            columnIdentifierList = new HashSet<>();
            columnIdentifierList.addAll(quadruple.getRootIdentifier().getColumnList());

            Base rootBase = instancePool.createIfNotExist(quadruple.getStructureDefinition(), "", getColumnValueIdentifier());

            currentProcessedPath = entry.getKey();

            String value = entry.getValue();
            if (value != null) {
                insertElement(rootBase, currentProcessedPath, value, new StringBuilder());
            }
        }

    }

    private void insertElement(Base base, String remainingName, Object value, StringBuilder accumulationTarget) throws ValueAssignmentException, InstanceException, RDB2OLException {
        /* Split by first dot */
        String[] parts = remainingName.split("\\.", 2);
        String currentName = parts[0];

        NameService nameService = nameServiceFactory.create(base, currentName);
        Property property = nameService.getProperty();

        /* Basis */
        if (parts.length == 1) {
            /* Since there is no div element in narrative class, it must be assigned using setDivAsString*/
            if (currentName.equals("div") && base instanceof Narrative) {
                if (((Narrative) base).getDivAsString() != null) {
                    if (!((Narrative) base).getDivAsString().equals(value)) {
                        throw new ValueAssignmentException(getOverwritingMessage(), 4001);
                    }
                    return;
                }

                ((Narrative) base).setDiv(new CustomXhtmlNode().setValue((String) value));
                return;
            }

            if (!property.isList()) {
                insertSingleValueLastElement(base, value, nameService, accumulationTarget);
                return;
            }

            insertMultiValueLastElement(base, value, nameService, accumulationTarget);
            return;
        }

        /* Recursive */
        String nextName = parts[1];
        if (!property.isList()) {
            insertSingleComplexElement(base, value, nextName, nameService, accumulationTarget);
            return;
        }

        insertArrayElement(base, value,  nextName, nameService, accumulationTarget);
    }

    private void insertArrayElement(Base base, Object value,  String nextElementName, NameService nameService, StringBuilder accumulationTarget) throws ValueAssignmentException, InstanceException, RDB2OLException {
        String propertyName = nameService.getPropertyName();
        String dataType = nameService.getDataType();
        Integer index = nameService.getIndex();
        Property property = nameService.getProperty();

        FhirTool.appendElementNameForApp(accumulationTarget, propertyName, dataType, nameService.getSlice(), index);

        Base[] baseList = base.getProperty(propertyName.hashCode(), propertyName, true);

        Base elementProperty = null;
        Identifier identifier = quadruple.getIdentifierByPath(accumulationTarget.toString());
        if (identifier != null) {
            columnIdentifierList.addAll(identifier.getColumnList());
            elementProperty = instancePool.getInstance(quadruple.getStructureDefinition(), accumulationTarget.toString(), getColumnValueIdentifier());
        }

        if (elementProperty == null) {
            elementProperty = nodeBox.get(accumulationTarget.toString());
            if (elementProperty == null) {
                if (index != null) {
                    if (baseList.length < index + 1) {
                        for (int i = baseList.length; i < index + 1; i++) {
                            if (dataType != null && property.getTypeCode().equals("Resource")) {
                                Base resource = ResourceFactory.createResource(dataType);
                                base.setProperty(propertyName, resource);
                            } else {
                                base.makeProperty(propertyName.hashCode(), propertyName);
                            }
                        }
                    }
                    baseList = base.getProperty(propertyName.hashCode(), propertyName, true);
                    elementProperty = baseList[index];

                    /*
                     * /elementProperty could be an object that does not conform to datatype/elementProperty could be object that does not conform to datatype
                     * example if contained{Provenance}[1] have been seeded before contained{Medication}[0], so in list indeks 0 will we Provenance
                     * So check if it is empty, replace it
                     */
                    if (elementProperty.isEmpty()) {
                        if (dataType != null && property.getTypeCode().equals("Resource")) {
                            List<Resource> containedList = new ArrayList<>();
                            for (Base b : baseList) {
                                containedList.add((Resource) b);
                            }

                            elementProperty = ResourceFactory.createResource(dataType);
                            containedList.set(index, (Resource) elementProperty);
                            ((DomainResource) base).setContained(containedList);
                        }
                    }
                    nodeBox.put(accumulationTarget.toString(), elementProperty);
                    instancePool.putInstance(quadruple.getStructureDefinition(), accumulationTarget.toString(), getColumnValueIdentifier(), elementProperty);
                } else {
                    if (dataType != null && property.getTypeCode().equals("Resource")) { //Resource Type, ex for contained : Patient
                        Base resource = ResourceFactory.createResource(dataType);
                        base.setProperty(propertyName, resource);
                    } else {
                        base.makeProperty(propertyName.hashCode(), propertyName);
                    }

                    //Get last inserted
                    baseList = base.getProperty(propertyName.hashCode(), propertyName, true);
                    elementProperty = baseList[baseList.length - 1];
                    nodeBox.put(accumulationTarget.toString(), elementProperty);
                    instancePool.putInstance(quadruple.getStructureDefinition(), accumulationTarget.toString(), getColumnValueIdentifier(), elementProperty);
                }
            }
        }

        insertElement(elementProperty, nextElementName, value, accumulationTarget);
    }

    private void insertSingleComplexElement(Base base, Object value, String nextElementName, NameService nameService, StringBuilder accumulationTarget) throws ValueAssignmentException, InstanceException, RDB2OLException {
        String propertyName = nameService.getPropertyName();
        String dataType = nameService.getDataType();
        FhirTool.appendElementNameForApp(accumulationTarget, propertyName, dataType, null, null);

        Base[] baseList = base.getProperty(propertyName.hashCode(), propertyName, true);

        /*
         * Delete previous (base[0]) if empty
         * This is important when seeding  bidirectional since same subject can have multiple row but different type code
         * If in 1 row, it fails if there are more than 1 column/field for value[x], example item[0].item.extension[0].valueInteger and item[0].item.extension[0].valueBoolean
         * This does not mean allowing overriding element!
         *
         * ex: most questionnaire in sample with path item[0].item.extension[0].value[x](typeCode) (in sample use item[0].item.extension[0].valueInteger, without bracket
         */
        Base elementProperty;
        if (baseList.length != 0) {
            //choice or contained
            if (dataType != null) {
                if (baseList[0].isEmpty()) {
                    base.setProperty(propertyName.hashCode(), propertyName, null);
                    elementProperty = ResourceFactory.createType(dataType);
                    if (elementProperty.isPrimitive()) {
                        throw new ValueAssignmentException("Cannot assign the value to primitive data type at path : "+ quotePath(accumulationTarget.toString()) + " of path: " + getCurrentProcessedPath(), 4002);
                    }
                    base.setProperty(propertyName + "[x]", elementProperty);
                }
            }
            elementProperty = baseList[0];
        } else {
            if (dataType != null) {
                if (nameService.getProperty().getTypeCode().equals("Resource")) {
                    elementProperty = ResourceFactory.createResource(dataType);
                    base.setProperty(propertyName, elementProperty);
                } else {
                    elementProperty = ResourceFactory.createType(dataType);
                    //here, elementProperty may be a primitive type, but it is allowed here since primitive can have a child (extension)/not terminal
                    base.setProperty(propertyName + "[x]", elementProperty);
                }
            } else {
                elementProperty = base.makeProperty(propertyName.hashCode(), propertyName);

            }
        }

        insertElement(elementProperty, nextElementName, value, accumulationTarget);
    }

    private void insertSingleValueLastElement(Base base, Object value, NameService nameService, StringBuilder accumulationTarget) throws ValueAssignmentException {
        String propertyName = nameService.getPropertyName();
        String dataType = nameService.getDataType();
        FhirTool.appendElementNameForApp(accumulationTarget, propertyName, dataType, null, null);

        Base[] baseList = base.getProperty(propertyName.hashCode(), propertyName, true);

        // cannot overwrite using different value
        if (baseList.length != 0) {

            //for fixed pattern
            if (value instanceof Base) {
                if (baseList[0].primitiveValue().equals(((Base) value).primitiveValue())) {
                    return;
                }
                throw new ValueAssignmentException(getOverwritingMessage(), 4004);
            }

            if (!baseList[0].isEmpty()) {
                /*
                 * for primitive which has extension, throw null exception when call ((IPrimitiveType) baseList[0]).getValueAsString().equals()
                 * example: _birthDate have already been seeded when try to seed birthDate
                 */
                if (((IPrimitiveType) baseList[0]).getValueAsString() == null) {
                    ((IPrimitiveType) baseList[0]).setValueAsString((String) value);
                    return;
                }

                if (((IPrimitiveType) baseList[0]).getValueAsString().equals(value)) {
                    return;
                } else {
                    throw new ValueAssignmentException(getOverwritingMessage(), 4005);
                }
            }

            /* Delete previous (base[0]) if empty */
            base.setProperty(propertyName.hashCode(), propertyName, null);
            baseList = base.getProperty(propertyName.hashCode(), propertyName, true);
        }

        //choice data type
        if (dataType != null) {
            Base dataTypeObj = ResourceFactory.createType(dataType);
            if (!dataTypeObj.isPrimitive()) {
                throw new ValueAssignmentException("Choice data type must be a primitive type at path: " + getCurrentProcessedPath(), 4006);
            }
            ((IPrimitiveType) dataTypeObj).setValueAsString((String) value);
            base.setProperty(propertyName + "[x]", dataTypeObj);
            return;
        }

        /* Make a new element */
        Base elementProperty = base.makeProperty(propertyName.hashCode(), propertyName);

        /* Exception if element is not primitive.
         *  Only primitive data type can be set using string
         * */
        if (!elementProperty.isPrimitive()) {
            throw new ValueAssignmentException("Cannot insert value to non-primitive type at path " + getCurrentProcessedPath(), 4007);
        }

        //base64
        if (elementProperty instanceof Base64BinaryType) {
            //replace base64 with custombase64
            CustomBase64BinaryType customBase64BinaryType = new CustomBase64BinaryType();
            customBase64BinaryType.setValueAsString((String) value);
            base.setProperty(propertyName, customBase64BinaryType);
            return;
        }

        ((IPrimitiveType) elementProperty).setValueAsString((String) value);
    }

    private void insertMultiValueLastElement(Base base, Object value, NameService nameService, StringBuilder accumulationTarget) throws ValueAssignmentException {
        String propertyName = nameService.getPropertyName();
        String dataType = nameService.getDataType();
        Integer index = nameService.getIndex();
        FhirTool.appendElementNameForApp(accumulationTarget, propertyName, dataType, null, index);

        if (value instanceof Base) {
            //@todo
            throw new ValueAssignmentException("Fixed pattern not yet supported in multi value at path: "+ getCurrentProcessedPath(), 4008);
        }

        Base[] baseList = base.getProperty(propertyName.hashCode(), propertyName, true);

        if (dataType != null) {
            throw new ValueAssignmentException("Choice data type must be single value and not indexed at path: " + getCurrentProcessedPath(), 4009);
        }

        Base elementProperty;
        if (index != null) {
            if (baseList.length < index + 1) {
                for (int i = baseList.length; i < index + 1; i++) {
                    base.makeProperty(propertyName.hashCode(), propertyName);
                }
            }
            baseList = base.getProperty(propertyName.hashCode(), propertyName, true);
            elementProperty = baseList[index];
        } else {
            elementProperty = nodeBox.get(accumulationTarget.toString());
            if (elementProperty == null) {

                base.makeProperty(propertyName.hashCode(), propertyName);

                //Get last inserted
                baseList = base.getProperty(propertyName.hashCode(), propertyName, true);
                elementProperty = baseList[baseList.length - 1];
                nodeBox.put(accumulationTarget.toString(), elementProperty);
            }


        }

        /* Exception if element is not primitive.
         *  Only primitive data type can be set using string
         * */
        if (!elementProperty.isPrimitive()) {
            throw new ValueAssignmentException("Cannot assign to non-primitive path at: " + getCurrentProcessedPath(), 4010);
        }

        //Cannot overwrite existing value
        if ((((IPrimitiveType) elementProperty).getValueAsString() != null) && !((IPrimitiveType) elementProperty).getValueAsString().equals(value)) {
            throw new ValueAssignmentException(getOverwritingMessage(), 4011);
        }

        ((IPrimitiveType) elementProperty).setValueAsString((String) value);
    }




    private HashMap<String, String> getColumnValueIdentifier() {
        HashMap<String, String> columnValueIdentifier = new HashMap<>();
        for (String column : columnIdentifierList) {
            String val = rowResult.get(column) instanceof String ? (String) rowResult.get(column):
                rowResult.get(column).toString();
            columnValueIdentifier.put(column, val);
        }
        return columnValueIdentifier;
    }

    private String getCurrentProcessedPath() {
        return quotePath(quadruple.getStructureDefinition().getType() + "." + currentProcessedPath);
    }

    private String quotePath(String path) {
        return "'" + path + "'";
    }

    private String getOverwritingMessage() {
        return "Overwriting using different value to the element at path: " + getCurrentProcessedPath() + " is not allowed";
    }


}
