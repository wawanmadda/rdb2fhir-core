package org.bayisehat.rdb2fhir.core.valueservice;

public class InstancePoolFactory {

    private final InstanceIdentifierFactory instanceIdentifierFactory;

    public InstancePoolFactory(InstanceIdentifierFactory instanceIdentifierFactory) {
        this.instanceIdentifierFactory = instanceIdentifierFactory;
    }

    public InstancePool create() {
        return new InstancePool(instanceIdentifierFactory);
    }

}
