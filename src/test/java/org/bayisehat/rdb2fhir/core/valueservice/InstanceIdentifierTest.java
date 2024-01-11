package org.bayisehat.rdb2fhir.core.valueservice;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class InstanceIdentifierTest {

    private InstanceIdentifierFactory instanceIdentifierFactory = new InstanceIdentifierFactory();

    @Test
    void testHashCode() {
        HashMap<String, String> kv1 = new HashMap<>();
        kv1.put("col1", "aaa");
        kv1.put("col2", "bbb");
        InstanceIdentifier instanceIdentifier1 = instanceIdentifierFactory.create(kv1);

        HashMap<String, String> kv2 = new HashMap<>();
        kv2.put("col1", "aaa");
        kv2.put("col2", "bbb");
        InstanceIdentifier instanceIdentifier2 = instanceIdentifierFactory.create(kv2);

        assertEquals(instanceIdentifier1.hashCode(), instanceIdentifier2.hashCode());
    }

    @Test
    void testEqual() {
        HashMap<String, String> kv1 = new HashMap<>();
        kv1.put("col1", "aaa");
        kv1.put("col2", "bbb");
        InstanceIdentifier instanceIdentifier1 = instanceIdentifierFactory.create(kv1);

        HashMap<String, String> kv2 = new HashMap<>();
        kv2.put("col1", "aaa");
        kv2.put("col2", "bbb");
        InstanceIdentifier instanceIdentifier2 = instanceIdentifierFactory.create(kv2);

        assertEquals(instanceIdentifier1, instanceIdentifier2);
    }

    @Test
    void testEqualUsingHashMap() {
        HashMap<String, String> kv1 = new HashMap<>();
        kv1.put("col1", "aaa");
        kv1.put("col2", "bbb");
        InstanceIdentifier instanceIdentifier1 = instanceIdentifierFactory.create(kv1);

        HashMap<String, String> kv2 = new HashMap<>();
        kv2.put("col1", "aaa");
        kv2.put("col2", "bbb");

        assertEquals(instanceIdentifier1, kv2);
    }

    @Test
    void hashCodeUsingHashMap() {
        HashMap<String, String> kv1 = new HashMap<>();
        kv1.put("col1", "aaa");
        kv1.put("col2", "bbb");
        InstanceIdentifier instanceIdentifier1 = instanceIdentifierFactory.create(kv1);

        HashMap<String, String> kv2 = new HashMap<>();
        kv2.put("col1", "aaa");
        kv2.put("col2", "bbb");

        assertEquals(instanceIdentifier1.hashCode(), kv2.hashCode());
    }

    @Test
    void hashMapAsKeyPut() {
        HashMap<String, String> kv1 = new HashMap<>();
        kv1.put("col1", "aaa");
        kv1.put("col2", "bbb");
        InstanceIdentifier instanceIdentifier1 = instanceIdentifierFactory.create(kv1);

        HashMap<String, String> kv3 = new HashMap<>();
        kv3.put("col1", "aaa");
        kv3.put("col2", "ccc");
        InstanceIdentifier instanceIdentifier3 = instanceIdentifierFactory.create(kv3);

        HashMap<InstanceIdentifier, String> mapList = new HashMap<>();
        mapList.put(instanceIdentifier1, "a");
        mapList.put(instanceIdentifier3, "c");
        assertEquals(2, mapList.size());
    }

    @Test
    void hashMapAsKeyOverwrite() {
        HashMap<String, String> kv1 = new HashMap<>();
        kv1.put("col1", "aaa");
        kv1.put("col2", "bbb");
        InstanceIdentifier instanceIdentifier1 = instanceIdentifierFactory.create(kv1);

        HashMap<String, String> kv2 = new HashMap<>();
        kv2.put("col1", "aaa");
        kv2.put("col2", "bbb");
        InstanceIdentifier instanceIdentifier2 = instanceIdentifierFactory.create(kv2);

        HashMap<InstanceIdentifier, String> mapList = new HashMap<>();
        mapList.put(instanceIdentifier1, "a");
        mapList.put(instanceIdentifier2, "b");
        assertEquals(1, mapList.size());
    }

    @Test
    void hashMapGetUsingIdentifier() {
        HashMap<String, String> kv1 = new HashMap<>();
        kv1.put("col1", "aaa");
        kv1.put("col2", "bbb");
        InstanceIdentifier instanceIdentifier1 = instanceIdentifierFactory.create(kv1);

        HashMap<String, String> kv2 = new HashMap<>();
        kv2.put("col1", "aaa");
        kv2.put("col2", "bbb");
        InstanceIdentifier instanceIdentifier2 = instanceIdentifierFactory.create(kv2);

        HashMap<InstanceIdentifier, String> mapList = new HashMap<>();
        mapList.put(instanceIdentifier1, "www");

        assertSame("www", mapList.get(instanceIdentifier2));
    }

    @Test
    /**
     * This return null since we do not override method equal in the HashMap
     * */
    void hashMapGetUsingHashMap() {
        HashMap<String, String> kv1 = new HashMap<>();
        kv1.put("col1", "aaa");
        kv1.put("col2", "bbb");
        InstanceIdentifier instanceIdentifier1 = instanceIdentifierFactory.create(kv1);

        HashMap<String, String> kv2 = new HashMap<>();
        kv2.put("col1", "aaa");
        kv2.put("col2", "bbb");

        HashMap<InstanceIdentifier, String> mapList = new HashMap<>();
        mapList.put(instanceIdentifier1, "www");

        assertNull(mapList.get(kv2));
    }
}