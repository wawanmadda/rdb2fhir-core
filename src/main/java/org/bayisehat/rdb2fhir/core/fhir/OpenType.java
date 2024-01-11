package org.bayisehat.rdb2fhir.core.fhir;

import java.util.HashSet;

/**
 * Open choice type based on <a href="http://hl7.org/fhir/R4/datatypes.html#open">Data type</a>
 * */
public enum OpenType {
    Base64Binary("base64Binary"),
    Boolean("boolean"),
    Canonical("canonical"),
    Code("code"),
    Date("date"),
    DateTime("dateTime"),
    Decimal("decimal"),
    Id("id"),
    Instant("instant"),
    Integer("integer"),
    Markdown("markdown"),
    Oid("oid"),
    PositiveInt("positiveInt"),
    String("string"),
    Time("time"),
    UnsignedInt("unsignedInt"),
    Uri("uri"),
    Url("url"),
    Uuid("uuid"),
    Address("Address"),
    Age("Age"),
    Annotation("Annotation"),
    Attachment("Attachment"),
    CodeableConcept("CodeableConcept"),
    Coding("Coding"),
    ContactPoint("ContactPoint"),
    Count("Count"),
    Distance("Distance"),
    Duration("Duration"),
    HumanName("HumanName"),
    Identifier("Identifier"),
    Money("Money"),
    Period("Period"),
    Quantity("Quantity"),
    Range("Range"),
    Ratio("Ratio"),
    Reference("Reference"),
    SampledData("SampledData"),
    Signature("Signature"),
    Timing("Timing"),
    ContactDetail("ContactDetail"),
    Contributor("Contributor"),
    DataRequirement("DataRequirement"),
    Expression("Expression"),
    ParameterDefinition("ParameterDefinition"),
    RelatedArtifact("RelatedArtifact"),
    TriggerDefinition("TriggerDefinition"),
    UsageContext("UsageContext"),
    Dosage("Dosage"),
    Meta("Meta");

    private final String original;
    OpenType(String original) {
        this.original = original;
    }

    public static HashSet<String> getEnum() {

        HashSet<String> values = new HashSet<>();

        for (OpenType ot : OpenType.values()) {
            values.add(ot.name());
        }

        return values;
    }

    public String getOriginal() {
        return original;
    }
}
