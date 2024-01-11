package org.bayisehat.rdb2fhir.core.valueservice;

import org.hl7.fhir.r4.model.Base;
import org.hl7.fhir.r4.model.ResourceFactory;
import org.hl7.fhir.r4.model.StructureDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * Instance Pool acts as a pool for all the instances generated
 * An instance is uniquely identified using a structureDefinition, chosen columns and its values
 * */
public class InstancePool {

    private final HashMap<StructureDefinition, HashMap<String, HashMap<InstanceIdentifier, Base>>> pool = new HashMap<>();

    private final InstanceIdentifierFactory instanceIdentifierFactory;

    public InstancePool(InstanceIdentifierFactory instanceIdentifierFactory) {
        this.instanceIdentifierFactory = instanceIdentifierFactory;
    }

    /**
     * Put an instance to the pool,
     * Within the pool, an instance identified by the structureDefinition, columns and its values.
     * Within a structureDefinition, an instance identifier by chosen columns and its values.
     * Path is used to identify location of the instance in the hierarchy
     *
     * @param keyValueList column and its values used to identify resources within a StructureDefinition
     *
     * */
    public void putInstance(StructureDefinition structureDefinition, String path, HashMap<String, String> keyValueList, Base base) {
        InstanceIdentifier instanceIdentifier = instanceIdentifierFactory.create(keyValueList);
        putInstance(structureDefinition, path, instanceIdentifier, base);
    }

    /**
     * Put an instance to the pool,
     * Within the pool, an instance identified by the structureDefinition, columns and its values.
     * Within a structureDefinition, an instance identifier by chosen columns and its values.
     * Path is used to identify location of the instance in the hierarchy
     *
     * @param instanceIdentifier identifier used to identify resources within a StructureDefinition
     *
     * */
    public void putInstance(StructureDefinition structureDefinition, String path, InstanceIdentifier instanceIdentifier, Base base) {
        HashMap<String, HashMap<InstanceIdentifier, Base>> pathList = pool.computeIfAbsent(structureDefinition, k -> new HashMap<>());

        HashMap<InstanceIdentifier, Base> instanceIdentifierToBaseList = pathList.computeIfAbsent(path, k -> new HashMap<>());

        instanceIdentifierToBaseList.put(instanceIdentifier, base);
        pathList.put(path, instanceIdentifierToBaseList);
    }

    /**
     * Get an instance from the pool
     * @return null if not found
     */
    public Base getInstance(StructureDefinition structureDefinition, String path, HashMap<String, String> keyValueList) throws InstanceException {
        for (Map.Entry<String, String> entry : keyValueList.entrySet()) {
            if (entry.getValue() == null) {
                throw new InstanceException("Null value in column " + entry.getKey() + " cannot be used as an identifier");
            }
        }

        HashMap<String, HashMap<InstanceIdentifier, Base>> pathList = pool.get(structureDefinition);
        if (pathList == null) {
            return null;
        }

        HashMap<InstanceIdentifier, Base> instanceIdentifierToBaseList = pathList.get(path);
        if (instanceIdentifierToBaseList == null) {
            return null;
        }

        return instanceIdentifierToBaseList.get(instanceIdentifierFactory.create(keyValueList));
    }

    /**
     * Get an instance from the pool
     * @return null if not found
     */
    public Base getInstance(StructureDefinition structureDefinition, String path, InstanceIdentifier instanceIdentifier) throws InstanceException {
        return getInstance(structureDefinition, path, instanceIdentifier.getKeyValueList());
    }

    /**
     * Get an instance from the pool, create it if not exists
     * */
    public Base createIfNotExist(StructureDefinition structureDefinition, String path, HashMap<String, String> keyValueList) throws InstanceException {
        Base instance = getInstance(structureDefinition, path, keyValueList);

        //not exist, create new instance
        if (instance == null) {
            instance = ResourceFactory.createResourceOrType(structureDefinition.getType());
            putInstance(structureDefinition, path, keyValueList, instance);
        }

        return instance;
    }

    public HashMap<StructureDefinition, HashMap<String, HashMap<InstanceIdentifier, Base>>> getPool() {
        return pool;
    }
}
