package org.bayisehat.rdb2fhir.core.rdb2ol;

import org.bayisehat.rdb2fhir.core.fhir.NameService;
import org.bayisehat.rdb2fhir.core.fhir.NameServiceFactory;
import org.bayisehat.rdb2fhir.core.util.FhirTool;
import org.hl7.fhir.r4.model.Base;
import org.hl7.fhir.r4.model.Property;
import org.hl7.fhir.r4.model.ResourceFactory;

import java.util.HashSet;

public class PathService {

    private String type;

    private StringBuilder appPath = new StringBuilder();

    private StringBuilder fhirPath = new StringBuilder();

    private final HashSet<String> lazyPathList = new HashSet<>();

    private NameServiceFactory nameServiceFactory;

    public PathService(NameServiceFactory nameServiceFactory) {
        this.nameServiceFactory = nameServiceFactory;
    }

    public PathService load(String type, String path) throws RDB2OLException {
        this.type = type;
        Base base = ResourceFactory.createResource(type);

        traverse(base, path);
        return this;
    }

    /**
     * Transform implicit open type to explicit open type
     * e.g. name.multipleBirthBoolean => name.multipleBirth{boolean}
     * */
    private void traverse(Base base, String remainingName) throws RDB2OLException {
        if (remainingName.isEmpty()) {
            return;
        }

        /* Split by dot */
        String[] parts = remainingName.split("\\.", 2);
        String currentName = parts[0];

        NameService nameService = nameServiceFactory.create(base, currentName);
        String propertyName = nameService.getPropertyName();
        Property property = nameService.getProperty();

        String appPathStr = FhirTool.appendElementNameForApp(appPath, nameService.getNameRemovedIndexSlice().startsWith("_") ? "_" +
                propertyName : propertyName, nameService.getDataType(), nameService.getSlice(), nameService.getIndex()).toString();

        FhirTool.appendElementNameForFhir(fhirPath, nameService.getNameRemovedIndexSlice().startsWith("_") ? "_" +
                propertyName : propertyName, nameService.getDataType(), nameService.getSlice(), nameService.getIndex(), nameService.isResourceType()).toString();

        /* Basis */
        if (parts.length == 1) {
            return;
        }

        /* Recursive */
        Base elementObject;
        //use setProperty for "contained" instead of makeProperty
        if (property.getTypeCode().equals("Resource")) {
            //list
            if (!currentName.endsWith("]") && property.isList()) {
                addLazyTarget(appPathStr);
            }

            //access contained without type
            if (nameService.getDataType() == null) {
                throw new RDB2OLException("Invalid RDB2OL: 'contained' must have a resource type", 1024);
            }

            elementObject = ResourceFactory.createResourceOrType(nameService.getDataType());
            base.setProperty(propertyName , elementObject);
        }else{
            if ((! currentName.endsWith("]")) && property.isList()) {
                addLazyTarget(appPathStr);
            }

            if (nameService.getDataType() != null) {
                //choice
                elementObject = ResourceFactory.createType(nameService.getDataType());
                base.setProperty(property.getName(), elementObject);
            }else{
                elementObject = base.makeProperty(propertyName.hashCode(), propertyName);
            }

        }

        String nextElementName = parts[1];
        traverse(elementObject, nextElementName);
    }

    private void addLazyTarget(String target) {
        lazyPathList.add(target);
    }

    public String getAppPath() {
        return appPath.toString().replaceAll("_", "");
    }

    public String getFhirPath() {
        return fhirPath.toString().replaceAll("_", "");
    }

    public String getAppFullPath() {
        return String.join(appPath.isEmpty() ? "" : ".", type, appPath.toString()).replaceAll("_", "");
    }

    public String getFhirFullPath() {
        return String.join(fhirPath.isEmpty() ? "" : ".", type, fhirPath.toString().replaceAll("_", ""));
    }

    public HashSet<String> getLazyPathList() {
        return lazyPathList;
    }
}
