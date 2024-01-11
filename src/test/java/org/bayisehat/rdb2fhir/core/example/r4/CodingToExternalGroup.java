package org.bayisehat.rdb2fhir.core.example.r4;

import org.bayisehat.rdb2fhir.core.example.BaseExampleTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class CodingToExternalGroup extends BaseExampleTest {
    @Test  void coding_profile() throws Exception { assertEqual("coding.profile.json"); }
    @Test  void communication_example_fm_attachment() throws Exception { assertEqual("communication-example-fm-attachment.json"); }
    @Test  void communication_example_fm_solicited_attachment() throws Exception { assertEqual("communication-example-fm-solicited-attachment.json"); }
    @Test  void communication_example() throws Exception { assertEqual("communication-example.json"); }
    @Test  void communication_questionnaire() throws Exception { assertEqual("communication-questionnaire.json"); }
    @Test  void communication_profile() throws Exception { assertEqual("communication.profile.json"); }
    @Test  void communicationrequest_example_fm_solicit_attachment() throws Exception { assertEqual("communicationrequest-example-fm-solicit-attachment.json"); }
    @Test  void communicationrequest_example() throws Exception { assertEqual("communicationrequest-example.json"); }
    @Test  void communicationrequest_questionnaire() throws Exception { assertEqual("communicationrequest-questionnaire.json"); }
    @Test  void communicationrequest_profile() throws Exception { assertEqual("communicationrequest.profile.json"); }
    @Test @Tag("multi")  void compartmentdefinition_device() throws Exception { assertEqual("compartmentdefinition-device.json"); }
    @Test @Tag("multi")  void compartmentdefinition_encounter() throws Exception { assertEqual("compartmentdefinition-encounter.json"); }
    @Test  void compartmentdefinition_example() throws Exception { assertEqual("compartmentdefinition-example.json"); }
    @Test @Tag("multi")  void compartmentdefinition_patient() throws Exception { assertEqual("compartmentdefinition-patient.json"); }
    @Test @Tag("multi")  void compartmentdefinition_practitioner() throws Exception { assertEqual("compartmentdefinition-practitioner.json"); }
    @Test  void compartmentdefinition_questionnaire() throws Exception { assertEqual("compartmentdefinition-questionnaire.json"); }
    @Test @Tag("multi")  void compartmentdefinition_relatedperson() throws Exception { assertEqual("compartmentdefinition-relatedperson.json"); }
    @Test  void compartmentdefinition_profile() throws Exception { assertEqual("compartmentdefinition.profile.json"); }
    @Test  void composition_example_mixed() throws Exception { assertEqual("composition-example-mixed.json"); }
    @Test  void composition_example() throws Exception { assertEqual("composition-example.json"); }
    @Test  void composition_questionnaire() throws Exception { assertEqual("composition-questionnaire.json"); }
    @Test  void composition_profile() throws Exception { assertEqual("composition.profile.json"); }
    @Test  void computableplandefinition_questionnaire() throws Exception { assertEqual("computableplandefinition-questionnaire.json"); }
    @Test  void computableplandefinition_profile() throws Exception { assertEqual("computableplandefinition.profile.json"); }
    @Test  void conceptmap_103() throws Exception { assertEqual("conceptmap-103.json"); }
    @Test  void conceptmap_cdshooks_indicator() throws Exception { assertEqual("conceptmap-cdshooks-indicator.json"); }
    @Test  void conceptmap_example_2() throws Exception { assertEqual("conceptmap-example-2.json"); }
    @Test @Tag("multi")  void conceptmap_example_specimen_type() throws Exception { assertEqual("conceptmap-example-specimen-type.json"); }
    @Test  void conceptmap_example() throws Exception { assertEqual("conceptmap-example.json"); }
    @Test  void conceptmap_questionnaire() throws Exception { assertEqual("conceptmap-questionnaire.json"); }
    @Test  void conceptmap_profile() throws Exception { assertEqual("conceptmap.profile.json"); }
    @Test @Tag("bundle")  void conceptmaps() throws Exception { assertEqual("conceptmaps.json"); }
    @Test  void condition_example_f001_heart() throws Exception { assertEqual("condition-example-f001-heart.json"); }
    @Test  void condition_example_f002_lung() throws Exception { assertEqual("condition-example-f002-lung.json"); }
    @Test  void condition_example_f003_abscess() throws Exception { assertEqual("condition-example-f003-abscess.json"); }
    @Test  void condition_example_f201_fever() throws Exception { assertEqual("condition-example-f201-fever.json"); }
    @Test  void condition_example_f202_malignancy() throws Exception { assertEqual("condition-example-f202-malignancy.json"); }
    @Test  void condition_example_f203_sepsis() throws Exception { assertEqual("condition-example-f203-sepsis.json"); }
    @Test  void condition_example_f204_renal() throws Exception { assertEqual("condition-example-f204-renal.json"); }
    @Test  void condition_example_f205_infection() throws Exception { assertEqual("condition-example-f205-infection.json"); }
    @Test  void condition_example_family_history() throws Exception { assertEqual("condition-example-family-history.json"); }
    @Test  void condition_example_stroke() throws Exception { assertEqual("condition-example-stroke.json"); }
    @Test  void condition_example() throws Exception { assertEqual("condition-example.json"); }
    @Test  void condition_example2() throws Exception { assertEqual("condition-example2.json"); }
    @Test  void condition_questionnaire() throws Exception { assertEqual("condition-questionnaire.json"); }
    @Test  void condition_profile() throws Exception { assertEqual("condition.profile.json"); }
    @Test  void consent_example_Emergency() throws Exception { assertEqual("consent-example-Emergency.json"); }
    @Test  void consent_example_Out() throws Exception { assertEqual("consent-example-Out.json"); }
    @Test  void consent_example_grantor() throws Exception { assertEqual("consent-example-grantor.json"); }
    @Test  void consent_example_notAuthor() throws Exception { assertEqual("consent-example-notAuthor.json"); }
    @Test  void consent_example_notOrg() throws Exception { assertEqual("consent-example-notOrg.json"); }
    @Test  void consent_example_notThem() throws Exception { assertEqual("consent-example-notThem.json"); }
    @Test  void consent_example_notThis() throws Exception { assertEqual("consent-example-notThis.json"); }
    @Test  void consent_example_notTime() throws Exception { assertEqual("consent-example-notTime.json"); }
    @Test @Tag("multi")  void consent_example_pkb() throws Exception { assertEqual("consent-example-pkb.json"); }
    @Test  void consent_example_signature() throws Exception { assertEqual("consent-example-signature.json"); }
    @Test  void consent_example_smartonfhir() throws Exception { assertEqual("consent-example-smartonfhir.json"); }
    @Test  void consent_example() throws Exception { assertEqual("consent-example.json"); }
    @Test  void consent_questionnaire() throws Exception { assertEqual("consent-questionnaire.json"); }
    @Test  void consent_profile() throws Exception { assertEqual("consent.profile.json"); }
    @Test  void contactdetail_profile() throws Exception { assertEqual("contactdetail.profile.json"); }
    @Test  void contactpoint_profile() throws Exception { assertEqual("contactpoint.profile.json"); }
    @Test  void contract_example_42cfr_part2() throws Exception { assertEqual("contract-example-42cfr-part2.json"); }
    @Test  void contract_example_ins_policy() throws Exception { assertEqual("contract-example-ins-policy.json"); }
    @Test  void contract_example() throws Exception { assertEqual("contract-example.json"); }
    @Test  void contract_questionnaire() throws Exception { assertEqual("contract-questionnaire.json"); }
    @Test  void contract_profile() throws Exception { assertEqual("contract.profile.json"); }
    @Test  void contributor_profile() throws Exception { assertEqual("contributor.profile.json"); }
    @Test  void coord_0base_example() throws Exception { assertEqual("coord-0base-example.json"); }
    @Test  void coord_1base_example() throws Exception { assertEqual("coord-1base-example.json"); }
    @Test  void count_profile() throws Exception { assertEqual("count.profile.json"); }
    @Test  void coverage_example_2() throws Exception { assertEqual("coverage-example-2.json"); }
    @Test  void coverage_example_ehic() throws Exception { assertEqual("coverage-example-ehic.json"); }
    @Test  void coverage_example_selfpay() throws Exception { assertEqual("coverage-example-selfpay.json"); }
    @Test @Tag("multi")  void coverage_example() throws Exception { assertEqual("coverage-example.json"); }
    @Test  void coverage_questionnaire() throws Exception { assertEqual("coverage-questionnaire.json"); }
    @Test  void coverage_profile() throws Exception { assertEqual("coverage.profile.json"); }
    @Test  void coverageeligibilityrequest_example_2() throws Exception { assertEqual("coverageeligibilityrequest-example-2.json"); }
    @Test  void coverageeligibilityrequest_example() throws Exception { assertEqual("coverageeligibilityrequest-example.json"); }
    @Test  void coverageeligibilityrequest_questionnaire() throws Exception { assertEqual("coverageeligibilityrequest-questionnaire.json"); }
    @Test  void coverageeligibilityrequest_profile() throws Exception { assertEqual("coverageeligibilityrequest.profile.json"); }
    @Test  void coverageeligibilityresponse_example_benefits_2() throws Exception { assertEqual("coverageeligibilityresponse-example-benefits-2.json"); }
    @Test  void coverageeligibilityresponse_example_benefits() throws Exception { assertEqual("coverageeligibilityresponse-example-benefits.json"); }
    @Test  void coverageeligibilityresponse_example_error() throws Exception { assertEqual("coverageeligibilityresponse-example-error.json"); }
    @Test  void coverageeligibilityresponse_example() throws Exception { assertEqual("coverageeligibilityresponse-example.json"); }
    @Test  void coverageeligibilityresponse_questionnaire() throws Exception { assertEqual("coverageeligibilityresponse-questionnaire.json"); }
    @Test  void coverageeligibilityresponse_profile() throws Exception { assertEqual("coverageeligibilityresponse.profile.json"); }
    @Test  void cqf_questionnaire_questionnaire() throws Exception { assertEqual("cqf-questionnaire-questionnaire.json"); }
    @Test  void cqf_questionnaire_profile() throws Exception { assertEqual("cqf-questionnaire.profile.json"); }
    @Test  void cqllibrary_questionnaire() throws Exception { assertEqual("cqllibrary-questionnaire.json"); }
    @Test  void cqllibrary_profile() throws Exception { assertEqual("cqllibrary.profile.json"); }
    @Test @Tag("bundle")  void dataelements() throws Exception { assertEqual("dataelements.json"); }
    @Test @Tag("multi")  void datarequirement_profile() throws Exception { assertEqual("datarequirement.profile.json"); }
    @Test  void date_profile() throws Exception { assertEqual("date.profile.json"); }
    @Test  void datetime_profile() throws Exception { assertEqual("datetime.profile.json"); }
    @Test  void decimal_profile() throws Exception { assertEqual("decimal.profile.json"); }
    @Test @Tag("multi")  void definition() throws Exception { assertEqual("definition.json"); }
    @Test  void detectedissue_example_allergy() throws Exception { assertEqual("detectedissue-example-allergy.json"); }
    @Test  void detectedissue_example_dup() throws Exception { assertEqual("detectedissue-example-dup.json"); }
    @Test  void detectedissue_example_lab() throws Exception { assertEqual("detectedissue-example-lab.json"); }
    @Test  void detectedissue_example() throws Exception { assertEqual("detectedissue-example.json"); }
    @Test  void detectedissue_questionnaire() throws Exception { assertEqual("detectedissue-questionnaire.json"); }
    @Test  void detectedissue_profile() throws Exception { assertEqual("detectedissue.profile.json"); }
    @Test  void device_example_f001_feedingtube() throws Exception { assertEqual("device-example-f001-feedingtube.json"); }
    @Test  void device_example() throws Exception { assertEqual("device-example.json"); }
    @Test  void device_extensions_Device_din() throws Exception { assertEqual("device-extensions-Device-din.json"); }
    @Test  void device_questionnaire() throws Exception { assertEqual("device-questionnaire.json"); }
    @Test  void device_profile() throws Exception { assertEqual("device.profile.json"); }
    @Test  void devicedefinition_example() throws Exception { assertEqual("devicedefinition-example.json"); }
    @Test  void devicedefinition_questionnaire() throws Exception { assertEqual("devicedefinition-questionnaire.json"); }
    @Test  void devicedefinition_profile() throws Exception { assertEqual("devicedefinition.profile.json"); }
    @Test  void devicemetric_example() throws Exception { assertEqual("devicemetric-example.json"); }
    @Test  void devicemetric_questionnaire() throws Exception { assertEqual("devicemetric-questionnaire.json"); }
    @Test  void devicemetric_profile() throws Exception { assertEqual("devicemetric.profile.json"); }
    @Test  void devicemetricobservation_questionnaire() throws Exception { assertEqual("devicemetricobservation-questionnaire.json"); }
    @Test  void devicemetricobservation_profile() throws Exception { assertEqual("devicemetricobservation.profile.json"); }
    @Test  void devicerequest_example_insulinpump() throws Exception { assertEqual("devicerequest-example-insulinpump.json"); }
    @Test  void devicerequest_example() throws Exception { assertEqual("devicerequest-example.json"); }
    @Test  void devicerequest_left_lens() throws Exception { assertEqual("devicerequest-left-lens.json"); }
    @Test  void devicerequest_questionnaire() throws Exception { assertEqual("devicerequest-questionnaire.json"); }
    @Test  void devicerequest_right_lens() throws Exception { assertEqual("devicerequest-right-lens.json"); }
    @Test  void devicerequest_profile() throws Exception { assertEqual("devicerequest.profile.json"); }
    @Test  void deviceusestatement_example() throws Exception { assertEqual("deviceusestatement-example.json"); }
    @Test  void deviceusestatement_questionnaire() throws Exception { assertEqual("deviceusestatement-questionnaire.json"); }
    @Test  void deviceusestatement_profile() throws Exception { assertEqual("deviceusestatement.profile.json"); }
    @Test  void diagnosticreport_example_dxa() throws Exception { assertEqual("diagnosticreport-example-dxa.json"); }
    @Test @Tag("bundle")  void diagnosticreport_example_f001_bloodexam() throws Exception { assertEqual("diagnosticreport-example-f001-bloodexam.json"); }
    @Test  void diagnosticreport_example_f201_brainct() throws Exception { assertEqual("diagnosticreport-example-f201-brainct.json"); }
    @Test @Tag("bundle")  void diagnosticreport_example_f202_bloodculture() throws Exception { assertEqual("diagnosticreport-example-f202-bloodculture.json"); }
    @Test @Tag("bundle")  void diagnosticreport_example_ghp() throws Exception { assertEqual("diagnosticreport-example-ghp.json"); }
    @Test  void diagnosticreport_example_gingival_mass() throws Exception { assertEqual("diagnosticreport-example-gingival-mass.json"); }
    @Test @Tag("bundle")  void diagnosticreport_example_lipids() throws Exception { assertEqual("diagnosticreport-example-lipids.json"); }
    @Test @Tag("bundle")  void diagnosticreport_example_lri() throws Exception { assertEqual("diagnosticreport-example-lri.json"); }
    @Test  void diagnosticreport_example_papsmear() throws Exception { assertEqual("diagnosticreport-example-papsmear.json"); }
    @Test  void diagnosticreport_example_pgx() throws Exception { assertEqual("diagnosticreport-example-pgx.json"); }
    @Test  void diagnosticreport_example_ultrasound() throws Exception { assertEqual("diagnosticreport-example-ultrasound.json"); }
    @Test @Tag("bundle")  void diagnosticreport_example() throws Exception { assertEqual("diagnosticreport-example.json"); }
    @Test @Tag("bundle")  void diagnosticreport_examples_general() throws Exception { assertEqual("diagnosticreport-examples-general.json"); }
    @Test  void diagnosticreport_genetic_DiagnosticReport_assessed_condition() throws Exception { assertEqual("diagnosticreport-genetic-DiagnosticReport-assessed-condition.json"); }
    @Test @Tag("bundle")  void diagnosticreport_genetics_comprehensive_bone_marrow_report() throws Exception { assertEqual("diagnosticreport-genetics-comprehensive-bone-marrow-report.json"); }
    @Test @Tag("bundle")  void diagnosticreport_genetics_example_2_familyhistory() throws Exception { assertEqual("diagnosticreport-genetics-example-2-familyhistory.json"); }
    @Test  void diagnosticreport_genetics_questionnaire() throws Exception { assertEqual("diagnosticreport-genetics-questionnaire.json"); }
    @Test  void diagnosticreport_genetics_profile() throws Exception { assertEqual("diagnosticreport-genetics.profile.json"); }
    @Test @Tag("bundle")  void diagnosticreport_hla_genetics_results_example() throws Exception { assertEqual("diagnosticreport-hla-genetics-results-example.json"); }
    @Test @Tag("bundle")  void diagnosticreport_micro1() throws Exception { assertEqual("diagnosticreport-micro1.json"); }
    @Test  void diagnosticreport_questionnaire() throws Exception { assertEqual("diagnosticreport-questionnaire.json"); }
    @Test  void diagnosticreport_profile() throws Exception { assertEqual("diagnosticreport.profile.json"); }
    @Test  void distance_profile() throws Exception { assertEqual("distance.profile.json"); }
    @Test @Tag("bundle")  void document_example_dischargesummary() throws Exception { assertEqual("document-example-dischargesummary.json"); }
    @Test  void documentmanifest_example() throws Exception { assertEqual("documentmanifest-example.json"); }
    @Test  void documentmanifest_fm_attachment() throws Exception { assertEqual("documentmanifest-fm-attachment.json"); }
    @Test  void documentmanifest_questionnaire() throws Exception { assertEqual("documentmanifest-questionnaire.json"); }
    @Test  void documentmanifest_profile() throws Exception { assertEqual("documentmanifest.profile.json"); }
    @Test  void documentreference_example() throws Exception { assertEqual("documentreference-example.json"); }
    @Test  void documentreference_questionnaire() throws Exception { assertEqual("documentreference-questionnaire.json"); }
    @Test  void documentreference_profile() throws Exception { assertEqual("documentreference.profile.json"); }
    @Test @Tag("multi")  void domainresource_profile() throws Exception { assertEqual("domainresource.profile.json"); }
    @Test @Tag("multi")  void dosage_profile() throws Exception { assertEqual("dosage.profile.json"); }
    @Test  void duration_profile() throws Exception { assertEqual("duration.profile.json"); }
    @Test  void effectevidencesynthesis_example() throws Exception { assertEqual("effectevidencesynthesis-example.json"); }
    @Test  void effectevidencesynthesis_questionnaire() throws Exception { assertEqual("effectevidencesynthesis-questionnaire.json"); }
    @Test  void effectevidencesynthesis_profile() throws Exception { assertEqual("effectevidencesynthesis.profile.json"); }
    @Test  void element_profile() throws Exception { assertEqual("element.profile.json"); }
    @Test  void elementdefinition_de_questionnaire() throws Exception { assertEqual("elementdefinition-de-questionnaire.json"); }
    @Test  void elementdefinition_de_profile() throws Exception { assertEqual("elementdefinition-de.profile.json"); }
    @Test  void elementdefinition_profile() throws Exception { assertEqual("elementdefinition.profile.json"); }
    @Test  void encounter_example_emerg() throws Exception { assertEqual("encounter-example-emerg.json"); }
    @Test  void encounter_example_f001_heart() throws Exception { assertEqual("encounter-example-f001-heart.json"); }
    @Test  void encounter_example_f002_lung() throws Exception { assertEqual("encounter-example-f002-lung.json"); }
    @Test  void encounter_example_f003_abscess() throws Exception { assertEqual("encounter-example-f003-abscess.json"); }
    @Test  void encounter_example_f201_20130404() throws Exception { assertEqual("encounter-example-f201-20130404.json"); }
    @Test  void encounter_example_f202_20130128() throws Exception { assertEqual("encounter-example-f202-20130128.json"); }
    @Test  void encounter_example_f203_20130311() throws Exception { assertEqual("encounter-example-f203-20130311.json"); }
    @Test  void encounter_example_home() throws Exception { assertEqual("encounter-example-home.json"); }
    @Test  void encounter_example_xcda() throws Exception { assertEqual("encounter-example-xcda.json"); }
    @Test  void encounter_example() throws Exception { assertEqual("encounter-example.json"); }
    @Test  void encounter_questionnaire() throws Exception { assertEqual("encounter-questionnaire.json"); }
    @Test  void encounter_profile() throws Exception { assertEqual("encounter.profile.json"); }
    @Test  void endpoint_example_direct() throws Exception { assertEqual("endpoint-example-direct.json"); }
    @Test  void endpoint_example_iid() throws Exception { assertEqual("endpoint-example-iid.json"); }
    @Test  void endpoint_example_wadors() throws Exception { assertEqual("endpoint-example-wadors.json"); }
    @Test  void endpoint_example() throws Exception { assertEqual("endpoint-example.json"); }
    @Test @Tag("bundle")  void endpoint_examples_general_template() throws Exception { assertEqual("endpoint-examples-general-template.json"); }
    @Test  void endpoint_questionnaire() throws Exception { assertEqual("endpoint-questionnaire.json"); }
    @Test  void endpoint_profile() throws Exception { assertEqual("endpoint.profile.json"); }
    @Test  void enrollmentrequest_example() throws Exception { assertEqual("enrollmentrequest-example.json"); }
    @Test  void enrollmentrequest_questionnaire() throws Exception { assertEqual("enrollmentrequest-questionnaire.json"); }
    @Test  void enrollmentrequest_profile() throws Exception { assertEqual("enrollmentrequest.profile.json"); }
    @Test  void enrollmentresponse_example() throws Exception { assertEqual("enrollmentresponse-example.json"); }
    @Test  void enrollmentresponse_questionnaire() throws Exception { assertEqual("enrollmentresponse-questionnaire.json"); }
    @Test  void enrollmentresponse_profile() throws Exception { assertEqual("enrollmentresponse.profile.json"); }
    @Test  void episodeofcare_example() throws Exception { assertEqual("episodeofcare-example.json"); }
    @Test  void episodeofcare_questionnaire() throws Exception { assertEqual("episodeofcare-questionnaire.json"); }
    @Test  void episodeofcare_profile() throws Exception { assertEqual("episodeofcare.profile.json"); }
    @Test @Tag("multi")  void event() throws Exception { assertEqual("event.json"); }
    @Test  void eventdefinition_example() throws Exception { assertEqual("eventdefinition-example.json"); }
    @Test  void eventdefinition_questionnaire() throws Exception { assertEqual("eventdefinition-questionnaire.json"); }
    @Test  void eventdefinition_profile() throws Exception { assertEqual("eventdefinition.profile.json"); }
    @Test  void evidence_example() throws Exception { assertEqual("evidence-example.json"); }
    @Test  void evidence_questionnaire() throws Exception { assertEqual("evidence-questionnaire.json"); }
    @Test  void evidence_profile() throws Exception { assertEqual("evidence.profile.json"); }
    @Test  void evidencevariable_example() throws Exception { assertEqual("evidencevariable-example.json"); }
    @Test  void evidencevariable_questionnaire() throws Exception { assertEqual("evidencevariable-questionnaire.json"); }
    @Test  void evidencevariable_profile() throws Exception { assertEqual("evidencevariable.profile.json"); }
    @Test  void examplescenario_example() throws Exception { assertEqual("examplescenario-example.json"); }
    @Test  void examplescenario_questionnaire() throws Exception { assertEqual("examplescenario-questionnaire.json"); }
    @Test  void examplescenario_profile() throws Exception { assertEqual("examplescenario.profile.json"); }
    @Test  void explanationofbenefit_example_2() throws Exception { assertEqual("explanationofbenefit-example-2.json"); }
    @Test  void explanationofbenefit_example() throws Exception { assertEqual("explanationofbenefit-example.json"); }
    @Test  void explanationofbenefit_questionnaire() throws Exception { assertEqual("explanationofbenefit-questionnaire.json"); }
    @Test  void explanationofbenefit_profile() throws Exception { assertEqual("explanationofbenefit.profile.json"); }
    @Test  void expression_profile() throws Exception { assertEqual("expression.profile.json"); }
    @Test  void extension_11179_objectclass() throws Exception { assertEqual("extension-11179-objectclass.json"); }
    @Test  void extension_11179_objectclassproperty() throws Exception { assertEqual("extension-11179-objectclassproperty.json"); }
    @Test  void extension_11179_permitted_value_conceptmap() throws Exception { assertEqual("extension-11179-permitted-value-conceptmap.json"); }
    @Test  void extension_11179_permitted_value_valueset() throws Exception { assertEqual("extension-11179-permitted-value-valueset.json"); }
    @Test  void extension_allergyintolerance_asserteddate() throws Exception { assertEqual("extension-allergyintolerance-asserteddate.json"); }
    @Test  void extension_allergyintolerance_certainty() throws Exception { assertEqual("extension-allergyintolerance-certainty.json"); }
    @Test  void extension_allergyintolerance_duration() throws Exception { assertEqual("extension-allergyintolerance-duration.json"); }
    @Test  void extension_allergyintolerance_reasonrefuted() throws Exception { assertEqual("extension-allergyintolerance-reasonrefuted.json"); }
    @Test  void extension_allergyintolerance_resolutionage() throws Exception { assertEqual("extension-allergyintolerance-resolutionage.json"); }
    @Test @Tag("multi")  void extension_allergyintolerance_substanceexposurerisk() throws Exception { assertEqual("extension-allergyintolerance-substanceexposurerisk.json"); }
    @Test  void extension_auditevent_accession() throws Exception { assertEqual("extension-auditevent-accession.json"); }
    @Test  void extension_auditevent_anonymized() throws Exception { assertEqual("extension-auditevent-anonymized.json"); }
    @Test  void extension_auditevent_encrypted() throws Exception { assertEqual("extension-auditevent-encrypted.json"); }
    @Test  void extension_auditevent_instance() throws Exception { assertEqual("extension-auditevent-instance.json"); }
    @Test  void extension_auditevent_mpps() throws Exception { assertEqual("extension-auditevent-mpps.json"); }
    @Test  void extension_auditevent_numberofinstances() throws Exception { assertEqual("extension-auditevent-numberofinstances.json"); }
    @Test  void extension_auditevent_participantobjectcontainsstudy() throws Exception { assertEqual("extension-auditevent-participantobjectcontainsstudy.json"); }
    @Test  void extension_auditevent_sopclass() throws Exception { assertEqual("extension-auditevent-sopclass.json"); }
    @Test  void extension_bodysite() throws Exception { assertEqual("extension-bodysite.json"); }
    @Test  void extension_capabilities() throws Exception { assertEqual("extension-capabilities.json"); }
    @Test  void extension_capabilitystatement_expectation() throws Exception { assertEqual("extension-capabilitystatement-expectation.json"); }
    @Test  void extension_capabilitystatement_prohibited() throws Exception { assertEqual("extension-capabilitystatement-prohibited.json"); }
    @Test @Tag("multi")  void extension_capabilitystatement_search_parameter_combination() throws Exception { assertEqual("extension-capabilitystatement-search-parameter-combination.json"); }
    @Test  void extension_capabilitystatement_supported_system() throws Exception { assertEqual("extension-capabilitystatement-supported-system.json"); }
    @Test  void extension_capabilitystatement_websocket() throws Exception { assertEqual("extension-capabilitystatement-websocket.json"); }
    @Test  void extension_careplan_activity_title() throws Exception { assertEqual("extension-careplan-activity-title.json"); }
    @Test @Tag("multi")  void extension_codesystem_alternate() throws Exception { assertEqual("extension-codesystem-alternate.json"); }
    @Test  void extension_codesystem_author() throws Exception { assertEqual("extension-codesystem-author.json"); }
    @Test  void extension_codesystem_concept_comments() throws Exception { assertEqual("extension-codesystem-concept-comments.json"); }
    @Test  void extension_codesystem_conceptorder() throws Exception { assertEqual("extension-codesystem-conceptorder.json"); }
    @Test  void extension_codesystem_effectivedate() throws Exception { assertEqual("extension-codesystem-effectivedate.json"); }
    @Test  void extension_codesystem_expirationdate() throws Exception { assertEqual("extension-codesystem-expirationdate.json"); }
    @Test @Tag("multi")  void extension_codesystem_history() throws Exception { assertEqual("extension-codesystem-history.json"); }
    @Test  void extension_codesystem_keyword() throws Exception { assertEqual("extension-codesystem-keyword.json"); }
    @Test  void extension_codesystem_label() throws Exception { assertEqual("extension-codesystem-label.json"); }
    @Test  void extension_codesystem_map() throws Exception { assertEqual("extension-codesystem-map.json"); }
    @Test @Tag("multi")  void extension_codesystem_othername() throws Exception { assertEqual("extension-codesystem-othername.json"); }
    @Test  void extension_codesystem_replacedby() throws Exception { assertEqual("extension-codesystem-replacedby.json"); }
    @Test  void extension_codesystem_sourcereference() throws Exception { assertEqual("extension-codesystem-sourcereference.json"); }
    @Test  void extension_codesystem_trusted_expansion() throws Exception { assertEqual("extension-codesystem-trusted-expansion.json"); }
    @Test @Tag("multi")  void extension_codesystem_usage() throws Exception { assertEqual("extension-codesystem-usage.json"); }
    @Test  void extension_codesystem_warning() throws Exception { assertEqual("extension-codesystem-warning.json"); }
    @Test  void extension_codesystem_workflowstatus() throws Exception { assertEqual("extension-codesystem-workflowstatus.json"); }
    @Test  void extension_coding_sctdescid() throws Exception { assertEqual("extension-coding-sctdescid.json"); }
    @Test  void extension_communication_media() throws Exception { assertEqual("extension-communication-media.json"); }
    @Test  void extension_communicationrequest_initiatinglocation() throws Exception { assertEqual("extension-communicationrequest-initiatinglocation.json"); }
    @Test  void extension_composition_clinicaldocument_otherconfidentiality() throws Exception { assertEqual("extension-composition-clinicaldocument-otherconfidentiality.json"); }
    @Test  void extension_composition_clinicaldocument_versionnumber() throws Exception { assertEqual("extension-composition-clinicaldocument-versionnumber.json"); }
    @Test  void extension_composition_section_subject() throws Exception { assertEqual("extension-composition-section-subject.json"); }
    @Test  void extension_concept_bidirectional() throws Exception { assertEqual("extension-concept-bidirectional.json"); }
    @Test  void extension_condition_asserteddate() throws Exception { assertEqual("extension-condition-asserteddate.json"); }
    @Test  void extension_condition_dueto() throws Exception { assertEqual("extension-condition-dueto.json"); }
    @Test  void extension_condition_occurredfollowing() throws Exception { assertEqual("extension-condition-occurredfollowing.json"); }
    @Test  void extension_condition_outcome() throws Exception { assertEqual("extension-condition-outcome.json"); }
    @Test  void extension_condition_related() throws Exception { assertEqual("extension-condition-related.json"); }
    @Test  void extension_condition_ruledout() throws Exception { assertEqual("extension-condition-ruledout.json"); }
    @Test  void extension_consent_location() throws Exception { assertEqual("extension-consent-location.json"); }
    @Test  void extension_consent_notificationendpoint() throws Exception { assertEqual("extension-consent-notificationendpoint.json"); }
    @Test  void extension_consent_transcriber() throws Exception { assertEqual("extension-consent-transcriber.json"); }
    @Test  void extension_consent_witness() throws Exception { assertEqual("extension-consent-witness.json"); }
    @Test  void extension_contactpoint_area() throws Exception { assertEqual("extension-contactpoint-area.json"); }
    @Test  void extension_contactpoint_country() throws Exception { assertEqual("extension-contactpoint-country.json"); }
    @Test  void extension_contactpoint_extension() throws Exception { assertEqual("extension-contactpoint-extension.json"); }
    @Test  void extension_contactpoint_local() throws Exception { assertEqual("extension-contactpoint-local.json"); }
    @Test  void extension_cqf_calculatedvalue() throws Exception { assertEqual("extension-cqf-calculatedvalue.json"); }
    @Test  void extension_cqf_cdshooksendpoint() throws Exception { assertEqual("extension-cqf-cdshooksendpoint.json"); }
    @Test  void extension_cqf_citation() throws Exception { assertEqual("extension-cqf-citation.json"); }
    @Test  void extension_cqf_encounterclass() throws Exception { assertEqual("extension-cqf-encounterclass.json"); }
    @Test  void extension_cqf_encountertype() throws Exception { assertEqual("extension-cqf-encountertype.json"); }
    @Test  void extension_cqf_expression() throws Exception { assertEqual("extension-cqf-expression.json"); }
    @Test  void extension_cqf_initialvalue() throws Exception { assertEqual("extension-cqf-initialvalue.json"); }
    @Test  void extension_cqf_initiatingorganization() throws Exception { assertEqual("extension-cqf-initiatingorganization.json"); }
    @Test  void extension_cqf_initiatingperson() throws Exception { assertEqual("extension-cqf-initiatingperson.json"); }
    @Test  void extension_cqf_library() throws Exception { assertEqual("extension-cqf-library.json"); }
    @Test @Tag("multi")  void extension_cqf_measureinfo() throws Exception { assertEqual("extension-cqf-measureinfo.json"); }
    @Test  void extension_cqf_qualityofevidence() throws Exception { assertEqual("extension-cqf-qualityofevidence.json"); }
    @Test  void extension_cqf_receivingorganization() throws Exception { assertEqual("extension-cqf-receivingorganization.json"); }
    @Test  void extension_cqf_receivingperson() throws Exception { assertEqual("extension-cqf-receivingperson.json"); }
    @Test  void extension_cqf_recipientlanguage() throws Exception { assertEqual("extension-cqf-recipientlanguage.json"); }
    @Test  void extension_cqf_recipienttype() throws Exception { assertEqual("extension-cqf-recipienttype.json"); }
    @Test @Tag("multi")  void extension_cqf_relativedatetime() throws Exception { assertEqual("extension-cqf-relativedatetime.json"); }
    @Test  void extension_cqf_strengthofrecommendation() throws Exception { assertEqual("extension-cqf-strengthofrecommendation.json"); }
    @Test  void extension_cqf_systemuserlanguage() throws Exception { assertEqual("extension-cqf-systemuserlanguage.json"); }
    @Test  void extension_cqf_systemusertaskcontext() throws Exception { assertEqual("extension-cqf-systemusertaskcontext.json"); }
    @Test  void extension_cqf_systemusertype() throws Exception { assertEqual("extension-cqf-systemusertype.json"); }
    @Test  void extension_cqm_validityperiod() throws Exception { assertEqual("extension-cqm-validityperiod.json"); }
    @Test  void extension_data_absent_reason() throws Exception { assertEqual("extension-data-absent-reason.json"); }
    @Test @Tag("bundle")  void extension_definitions() throws Exception { assertEqual("extension-definitions.json"); }
    @Test  void extension_designnote() throws Exception { assertEqual("extension-designnote.json"); }
    @Test  void extension_device_implantstatus() throws Exception { assertEqual("extension-device-implantstatus.json"); }
    @Test @Tag("multi")  void extension_devicerequest_patientinstruction() throws Exception { assertEqual("extension-devicerequest-patientinstruction.json"); }
    @Test  void extension_diagnosticreport_addendumof() throws Exception { assertEqual("extension-diagnosticreport-addendumof.json"); }
    @Test  void extension_diagnosticreport_extends() throws Exception { assertEqual("extension-diagnosticreport-extends.json"); }
    @Test @Tag("multi")  void extension_diagnosticreport_geneticsanalysis() throws Exception { assertEqual("extension-diagnosticreport-geneticsanalysis.json"); }
    @Test  void extension_diagnosticreport_geneticsassessedcondition() throws Exception { assertEqual("extension-diagnosticreport-geneticsassessedcondition.json"); }
    @Test  void extension_diagnosticreport_geneticsfamilymemberhistory() throws Exception { assertEqual("extension-diagnosticreport-geneticsfamilymemberhistory.json"); }
    @Test @Tag("multi")  void extension_diagnosticreport_geneticsreferences() throws Exception { assertEqual("extension-diagnosticreport-geneticsreferences.json"); }
    @Test  void extension_diagnosticreport_locationperformed() throws Exception { assertEqual("extension-diagnosticreport-locationperformed.json"); }
    @Test  void extension_diagnosticreport_replaces() throws Exception { assertEqual("extension-diagnosticreport-replaces.json"); }
    @Test  void extension_diagnosticreport_risk() throws Exception { assertEqual("extension-diagnosticreport-risk.json"); }
    @Test  void extension_diagnosticreport_summaryof() throws Exception { assertEqual("extension-diagnosticreport-summaryof.json"); }
    @Test  void extension_display() throws Exception { assertEqual("extension-display.json"); }
    @Test  void extension_elementdefinition_allowedunits() throws Exception { assertEqual("extension-elementdefinition-allowedunits.json"); }
    @Test  void extension_elementdefinition_bestpractice_explanation() throws Exception { assertEqual("extension-elementdefinition-bestpractice-explanation.json"); }
    @Test  void extension_elementdefinition_bestpractice() throws Exception { assertEqual("extension-elementdefinition-bestpractice.json"); }
    @Test  void extension_elementdefinition_bindingname() throws Exception { assertEqual("extension-elementdefinition-bindingname.json"); }
    @Test  void extension_elementdefinition_equivalence() throws Exception { assertEqual("extension-elementdefinition-equivalence.json"); }
    @Test  void extension_elementdefinition_identifier() throws Exception { assertEqual("extension-elementdefinition-identifier.json"); }
    @Test  void extension_elementdefinition_inheritedextensiblevalueset() throws Exception { assertEqual("extension-elementdefinition-inheritedextensiblevalueset.json"); }
    @Test  void extension_elementdefinition_iscommonbinding() throws Exception { assertEqual("extension-elementdefinition-iscommonbinding.json"); }
    @Test  void extension_elementdefinition_maxvalueset() throws Exception { assertEqual("extension-elementdefinition-maxvalueset.json"); }
    @Test  void extension_elementdefinition_minvalueset() throws Exception { assertEqual("extension-elementdefinition-minvalueset.json"); }
    @Test  void extension_elementdefinition_namespace() throws Exception { assertEqual("extension-elementdefinition-namespace.json"); }
    @Test  void extension_elementdefinition_profile_element() throws Exception { assertEqual("extension-elementdefinition-profile-element.json"); }
    @Test  void extension_elementdefinition_question() throws Exception { assertEqual("extension-elementdefinition-question.json"); }
    @Test  void extension_elementdefinition_selector() throws Exception { assertEqual("extension-elementdefinition-selector.json"); }
    @Test  void extension_elementdefinition_translatable() throws Exception { assertEqual("extension-elementdefinition-translatable.json"); }
    @Test  void extension_encounter_associatedencounter() throws Exception { assertEqual("extension-encounter-associatedencounter.json"); }
    @Test  void extension_encounter_modeofarrival() throws Exception { assertEqual("extension-encounter-modeofarrival.json"); }
    @Test  void extension_encounter_reasoncancelled() throws Exception { assertEqual("extension-encounter-reasoncancelled.json"); }
    @Test  void extension_entryformat() throws Exception { assertEqual("extension-entryformat.json"); }
    @Test  void extension_event_basedon() throws Exception { assertEqual("extension-event-basedon.json"); }
    @Test  void extension_event_eventhistory() throws Exception { assertEqual("extension-event-eventhistory.json"); }
    @Test  void extension_event_location() throws Exception { assertEqual("extension-event-location.json"); }
    @Test  void extension_event_partof() throws Exception { assertEqual("extension-event-partof.json"); }
    @Test  void extension_event_performerfunction() throws Exception { assertEqual("extension-event-performerfunction.json"); }
    @Test  void extension_event_statusreason() throws Exception { assertEqual("extension-event-statusreason.json"); }
    @Test  void extension_family_member_history_genetics_observation() throws Exception { assertEqual("extension-family-member-history-genetics-observation.json"); }
    @Test @Tag("multi")  void extension_family_member_history_genetics_parent() throws Exception { assertEqual("extension-family-member-history-genetics-parent.json"); }
    @Test @Tag("multi")  void extension_family_member_history_genetics_sibling() throws Exception { assertEqual("extension-family-member-history-genetics-sibling.json"); }
    @Test  void extension_familymemberhistory_abatement() throws Exception { assertEqual("extension-familymemberhistory-abatement.json"); }
    @Test  void extension_familymemberhistory_patient_record() throws Exception { assertEqual("extension-familymemberhistory-patient-record.json"); }
    @Test  void extension_familymemberhistory_severity() throws Exception { assertEqual("extension-familymemberhistory-severity.json"); }
    @Test  void extension_familymemberhistory_type() throws Exception { assertEqual("extension-familymemberhistory-type.json"); }
    @Test  void extension_flag_detail() throws Exception { assertEqual("extension-flag-detail.json"); }
    @Test  void extension_flag_priority() throws Exception { assertEqual("extension-flag-priority.json"); }
    @Test @Tag("multi")  void extension_geolocation() throws Exception { assertEqual("extension-geolocation.json"); }
    @Test @Tag("multi")  void extension_goal_acceptance() throws Exception { assertEqual("extension-goal-acceptance.json"); }
    @Test  void extension_goal_reasonrejected() throws Exception { assertEqual("extension-goal-reasonrejected.json"); }
    @Test @Tag("multi")  void extension_goal_relationship() throws Exception { assertEqual("extension-goal-relationship.json"); }
    @Test  void extension_hla_genotyping_results_allele_database() throws Exception { assertEqual("extension-hla-genotyping-results-allele-database.json"); }
    @Test @Tag("multi")  void extension_hla_genotyping_results_glstring() throws Exception { assertEqual("extension-hla-genotyping-results-glstring.json"); }
    @Test @Tag("multi")  void extension_hla_genotyping_results_haploid() throws Exception { assertEqual("extension-hla-genotyping-results-haploid.json"); }
    @Test  void extension_hla_genotyping_results_method() throws Exception { assertEqual("extension-hla-genotyping-results-method.json"); }
    @Test  void extension_http_response_header() throws Exception { assertEqual("extension-http-response-header.json"); }
    @Test  void extension_humanname_assembly_order() throws Exception { assertEqual("extension-humanname-assembly-order.json"); }
    @Test  void extension_humanname_fathers_family() throws Exception { assertEqual("extension-humanname-fathers-family.json"); }
    @Test  void extension_humanname_mothers_family() throws Exception { assertEqual("extension-humanname-mothers-family.json"); }
    @Test  void extension_humanname_own_name() throws Exception { assertEqual("extension-humanname-own-name.json"); }
    @Test  void extension_humanname_own_prefix() throws Exception { assertEqual("extension-humanname-own-prefix.json"); }
    @Test  void extension_humanname_partner_name() throws Exception { assertEqual("extension-humanname-partner-name.json"); }
    @Test  void extension_humanname_partner_prefix() throws Exception { assertEqual("extension-humanname-partner-prefix.json"); }
    @Test  void extension_identifier_validdate() throws Exception { assertEqual("extension-identifier-validdate.json"); }
    @Test  void extension_iso21090_ad_use() throws Exception { assertEqual("extension-iso21090-ad-use.json"); }
    @Test  void extension_iso21090_adxp_additionallocator() throws Exception { assertEqual("extension-iso21090-adxp-additionallocator.json"); }
    @Test  void extension_iso21090_adxp_buildingnumbersuffix() throws Exception { assertEqual("extension-iso21090-adxp-buildingnumbersuffix.json"); }
    @Test  void extension_iso21090_adxp_careof() throws Exception { assertEqual("extension-iso21090-adxp-careof.json"); }
    @Test  void extension_iso21090_adxp_censustract() throws Exception { assertEqual("extension-iso21090-adxp-censustract.json"); }
    @Test  void extension_iso21090_adxp_delimiter() throws Exception { assertEqual("extension-iso21090-adxp-delimiter.json"); }
    @Test  void extension_iso21090_adxp_deliveryaddressline() throws Exception { assertEqual("extension-iso21090-adxp-deliveryaddressline.json"); }
    @Test  void extension_iso21090_adxp_deliveryinstallationarea() throws Exception { assertEqual("extension-iso21090-adxp-deliveryinstallationarea.json"); }
    @Test  void extension_iso21090_adxp_deliveryinstallationqualifier() throws Exception { assertEqual("extension-iso21090-adxp-deliveryinstallationqualifier.json"); }
    @Test  void extension_iso21090_adxp_deliveryinstallationtype() throws Exception { assertEqual("extension-iso21090-adxp-deliveryinstallationtype.json"); }
    @Test  void extension_iso21090_adxp_deliverymode() throws Exception { assertEqual("extension-iso21090-adxp-deliverymode.json"); }
    @Test  void extension_iso21090_adxp_deliverymodeidentifier() throws Exception { assertEqual("extension-iso21090-adxp-deliverymodeidentifier.json"); }
    @Test  void extension_iso21090_adxp_direction() throws Exception { assertEqual("extension-iso21090-adxp-direction.json"); }
    @Test  void extension_iso21090_adxp_housenumber() throws Exception { assertEqual("extension-iso21090-adxp-housenumber.json"); }
    @Test  void extension_iso21090_adxp_housenumbernumeric() throws Exception { assertEqual("extension-iso21090-adxp-housenumbernumeric.json"); }
    @Test  void extension_iso21090_adxp_postbox() throws Exception { assertEqual("extension-iso21090-adxp-postbox.json"); }
    @Test  void extension_iso21090_adxp_precinct() throws Exception { assertEqual("extension-iso21090-adxp-precinct.json"); }
    @Test  void extension_iso21090_adxp_streetaddressline() throws Exception { assertEqual("extension-iso21090-adxp-streetaddressline.json"); }
    @Test  void extension_iso21090_adxp_streetname() throws Exception { assertEqual("extension-iso21090-adxp-streetname.json"); }
    @Test  void extension_iso21090_adxp_streetnamebase() throws Exception { assertEqual("extension-iso21090-adxp-streetnamebase.json"); }
    @Test  void extension_iso21090_adxp_streetnametype() throws Exception { assertEqual("extension-iso21090-adxp-streetnametype.json"); }
    @Test  void extension_iso21090_adxp_unitid() throws Exception { assertEqual("extension-iso21090-adxp-unitid.json"); }
    @Test  void extension_iso21090_adxp_unittype() throws Exception { assertEqual("extension-iso21090-adxp-unittype.json"); }
    @Test  void extension_iso21090_en_qualifier() throws Exception { assertEqual("extension-iso21090-en-qualifier.json"); }
    @Test  void extension_iso21090_en_representation() throws Exception { assertEqual("extension-iso21090-en-representation.json"); }
    @Test  void extension_iso21090_en_use() throws Exception { assertEqual("extension-iso21090-en-use.json"); }
    @Test  void extension_iso21090_nullflavor() throws Exception { assertEqual("extension-iso21090-nullflavor.json"); }
    @Test  void extension_iso21090_pq_translation() throws Exception { assertEqual("extension-iso21090-pq-translation.json"); }
    @Test  void extension_iso21090_preferred() throws Exception { assertEqual("extension-iso21090-preferred.json"); }
    @Test  void extension_iso21090_sc_coding() throws Exception { assertEqual("extension-iso21090-sc-coding.json"); }
    @Test  void extension_iso21090_tel_address() throws Exception { assertEqual("extension-iso21090-tel-address.json"); }
    @Test  void extension_iso21090_uncertainty() throws Exception { assertEqual("extension-iso21090-uncertainty.json"); }
    @Test  void extension_iso21090_uncertaintytype() throws Exception { assertEqual("extension-iso21090-uncertaintytype.json"); }
    @Test  void extension_language() throws Exception { assertEqual("extension-language.json"); }
    @Test  void extension_list_changebase() throws Exception { assertEqual("extension-list-changebase.json"); }
    @Test  void extension_location_boundary_geojson() throws Exception { assertEqual("extension-location-boundary-geojson.json"); }
    @Test  void extension_location_distance() throws Exception { assertEqual("extension-location-distance.json"); }
    @Test  void extension_match_grade() throws Exception { assertEqual("extension-match-grade.json"); }
    @Test  void extension_maxdecimalplaces() throws Exception { assertEqual("extension-maxdecimalplaces.json"); }
    @Test  void extension_maxsize() throws Exception { assertEqual("extension-maxsize.json"); }
    @Test  void extension_maxvalue() throws Exception { assertEqual("extension-maxvalue.json"); }
    @Test  void extension_messageheader_response_request() throws Exception { assertEqual("extension-messageheader-response-request.json"); }
    @Test  void extension_mimetype() throws Exception { assertEqual("extension-mimetype.json"); }
    @Test  void extension_minlength() throws Exception { assertEqual("extension-minlength.json"); }
    @Test  void extension_minvalue() throws Exception { assertEqual("extension-minvalue.json"); }
    @Test  void extension_narrativelink() throws Exception { assertEqual("extension-narrativelink.json"); }
    @Test  void extension_nutritionorder_adaptivefeedingdevice() throws Exception { assertEqual("extension-nutritionorder-adaptivefeedingdevice.json"); }
    @Test @Tag("multi")  void extension_oauth_uris() throws Exception { assertEqual("extension-oauth-uris.json"); }
    @Test  void extension_observation_bodyposition() throws Exception { assertEqual("extension-observation-bodyposition.json"); }
    @Test  void extension_observation_delta() throws Exception { assertEqual("extension-observation-delta.json"); }
    @Test  void extension_observation_devicecode() throws Exception { assertEqual("extension-observation-devicecode.json"); }
    @Test  void extension_observation_focuscode() throws Exception { assertEqual("extension-observation-focuscode.json"); }
    @Test  void extension_observation_gatewaydevice() throws Exception { assertEqual("extension-observation-gatewaydevice.json"); }
    @Test @Tag("multi")  void extension_observation_geneticsallele() throws Exception { assertEqual("extension-observation-geneticsallele.json"); }
    @Test @Tag("multi")  void extension_observation_geneticsaminoacidchange() throws Exception { assertEqual("extension-observation-geneticsaminoacidchange.json"); }
    @Test @Tag("multi")  void extension_observation_geneticsancestry() throws Exception { assertEqual("extension-observation-geneticsancestry.json"); }
    @Test  void extension_observation_geneticscopynumberevent() throws Exception { assertEqual("extension-observation-geneticscopynumberevent.json"); }
    @Test  void extension_observation_geneticsdnaregionname() throws Exception { assertEqual("extension-observation-geneticsdnaregionname.json"); }
    @Test  void extension_observation_geneticsgene() throws Exception { assertEqual("extension-observation-geneticsgene.json"); }
    @Test  void extension_observation_geneticsgenomicsourceclass() throws Exception { assertEqual("extension-observation-geneticsgenomicsourceclass.json"); }
    @Test  void extension_observation_geneticsinterpretation() throws Exception { assertEqual("extension-observation-geneticsinterpretation.json"); }
    @Test @Tag("multi")  void extension_observation_geneticsphaseset() throws Exception { assertEqual("extension-observation-geneticsphaseset.json"); }
    @Test @Tag("multi")  void extension_observation_geneticsvariant() throws Exception { assertEqual("extension-observation-geneticsvariant.json"); }
    @Test  void extension_observation_precondition() throws Exception { assertEqual("extension-observation-precondition.json"); }
    @Test  void extension_observation_reagent() throws Exception { assertEqual("extension-observation-reagent.json"); }
    @Test  void extension_observation_replaces() throws Exception { assertEqual("extension-observation-replaces.json"); }
    @Test  void extension_observation_secondaryfinding() throws Exception { assertEqual("extension-observation-secondaryfinding.json"); }
    @Test  void extension_observation_sequelto() throws Exception { assertEqual("extension-observation-sequelto.json"); }
    @Test  void extension_observation_specimencode() throws Exception { assertEqual("extension-observation-specimencode.json"); }
    @Test  void extension_observation_timeoffset() throws Exception { assertEqual("extension-observation-timeoffset.json"); }
    @Test  void extension_openehr_administration() throws Exception { assertEqual("extension-openehr-administration.json"); }
    @Test  void extension_openehr_careplan() throws Exception { assertEqual("extension-openehr-careplan.json"); }
    @Test  void extension_openehr_exposuredate() throws Exception { assertEqual("extension-openehr-exposuredate.json"); }
    @Test  void extension_openehr_exposuredescription() throws Exception { assertEqual("extension-openehr-exposuredescription.json"); }
    @Test  void extension_openehr_exposureduration() throws Exception { assertEqual("extension-openehr-exposureduration.json"); }
    @Test  void extension_openehr_location() throws Exception { assertEqual("extension-openehr-location.json"); }
    @Test  void extension_openehr_management() throws Exception { assertEqual("extension-openehr-management.json"); }
    @Test  void extension_openehr_test() throws Exception { assertEqual("extension-openehr-test.json"); }
    @Test  void extension_operationdefinition_allowed_type() throws Exception { assertEqual("extension-operationdefinition-allowed-type.json"); }
    @Test  void extension_operationdefinition_profile() throws Exception { assertEqual("extension-operationdefinition-profile.json"); }
    @Test  void extension_operationoutcome_authority() throws Exception { assertEqual("extension-operationoutcome-authority.json"); }
    @Test  void extension_operationoutcome_detectedissue() throws Exception { assertEqual("extension-operationoutcome-detectedissue.json"); }
    @Test  void extension_operationoutcome_issue_source() throws Exception { assertEqual("extension-operationoutcome-issue-source.json"); }
    @Test  void extension_ordinalvalue() throws Exception { assertEqual("extension-ordinalvalue.json"); }
    @Test  void extension_organization_period() throws Exception { assertEqual("extension-organization-period.json"); }
    @Test  void extension_organization_preferredcontact() throws Exception { assertEqual("extension-organization-preferredcontact.json"); }
    @Test  void extension_organizationaffiliation_primaryind() throws Exception { assertEqual("extension-organizationaffiliation-primaryind.json"); }
    @Test  void extension_originaltext() throws Exception { assertEqual("extension-originaltext.json"); }
    @Test  void extension_parameters_fullurl() throws Exception { assertEqual("extension-parameters-fullurl.json"); }
    @Test  void extension_patient_adoptioninfo() throws Exception { assertEqual("extension-patient-adoptioninfo.json"); }
    @Test @Tag("multi")  void extension_patient_animal() throws Exception { assertEqual("extension-patient-animal.json"); }
    @Test  void extension_patient_birthplace() throws Exception { assertEqual("extension-patient-birthplace.json"); }
    @Test  void extension_patient_birthtime() throws Exception { assertEqual("extension-patient-birthtime.json"); }
    @Test  void extension_patient_cadavericdonor() throws Exception { assertEqual("extension-patient-cadavericdonor.json"); }
    @Test @Tag("multi")  void extension_patient_citizenship() throws Exception { assertEqual("extension-patient-citizenship.json"); }
    @Test  void extension_patient_congregation() throws Exception { assertEqual("extension-patient-congregation.json"); }
    @Test  void extension_patient_disability() throws Exception { assertEqual("extension-patient-disability.json"); }
    @Test  void extension_patient_genderidentity() throws Exception { assertEqual("extension-patient-genderidentity.json"); }
    @Test  void extension_patient_importance() throws Exception { assertEqual("extension-patient-importance.json"); }
    @Test  void extension_patient_interpreterrequired() throws Exception { assertEqual("extension-patient-interpreterrequired.json"); }
    @Test  void extension_patient_mothersmaidenname() throws Exception { assertEqual("extension-patient-mothersmaidenname.json"); }
    @Test @Tag("multi")  void extension_patient_nationality() throws Exception { assertEqual("extension-patient-nationality.json"); }
    @Test  void extension_patient_preferencetype() throws Exception { assertEqual("extension-patient-preferencetype.json"); }
    @Test @Tag("multi")  void extension_patient_proficiency() throws Exception { assertEqual("extension-patient-proficiency.json"); }
    @Test  void extension_patient_relatedperson() throws Exception { assertEqual("extension-patient-relatedperson.json"); }
    @Test  void extension_patient_religion() throws Exception { assertEqual("extension-patient-religion.json"); }
    @Test  void extension_practitioner_animalspecies() throws Exception { assertEqual("extension-practitioner-animalspecies.json"); }
    @Test  void extension_practitionerrole_primaryind() throws Exception { assertEqual("extension-practitionerrole-primaryind.json"); }
    @Test  void extension_procedure_approachbodystructure() throws Exception { assertEqual("extension-procedure-approachbodystructure.json"); }
    @Test @Tag("multi")  void extension_procedure_causedby() throws Exception { assertEqual("extension-procedure-causedby.json"); }
    @Test  void extension_procedure_directedby() throws Exception { assertEqual("extension-procedure-directedby.json"); }
    @Test  void extension_procedure_incisiondatetime() throws Exception { assertEqual("extension-procedure-incisiondatetime.json"); }
    @Test  void extension_procedure_method() throws Exception { assertEqual("extension-procedure-method.json"); }
    @Test  void extension_procedure_progressstatus() throws Exception { assertEqual("extension-procedure-progressstatus.json"); }
    @Test  void extension_procedure_schedule() throws Exception { assertEqual("extension-procedure-schedule.json"); }
    @Test  void extension_procedure_targetbodystructure() throws Exception { assertEqual("extension-procedure-targetbodystructure.json"); }
    @Test  void extension_quantity_precision() throws Exception { assertEqual("extension-quantity-precision.json"); }
    @Test  void extension_questionnaire_basetype() throws Exception { assertEqual("extension-questionnaire-basetype.json"); }
    @Test  void extension_questionnaire_choiceorientation() throws Exception { assertEqual("extension-questionnaire-choiceorientation.json"); }
    @Test @Tag("multi")  void extension_questionnaire_constraint() throws Exception { assertEqual("extension-questionnaire-constraint.json"); }
    @Test  void extension_questionnaire_displaycategory() throws Exception { assertEqual("extension-questionnaire-displaycategory.json"); }
    @Test  void extension_questionnaire_fhirtype() throws Exception { assertEqual("extension-questionnaire-fhirtype.json"); }
    @Test  void extension_questionnaire_hidden() throws Exception { assertEqual("extension-questionnaire-hidden.json"); }
    @Test  void extension_questionnaire_itemcontrol() throws Exception { assertEqual("extension-questionnaire-itemcontrol.json"); }
    @Test  void extension_questionnaire_maxoccurs() throws Exception { assertEqual("extension-questionnaire-maxoccurs.json"); }
    @Test  void extension_questionnaire_minoccurs() throws Exception { assertEqual("extension-questionnaire-minoccurs.json"); }
    @Test  void extension_questionnaire_optionexclusive() throws Exception { assertEqual("extension-questionnaire-optionexclusive.json"); }
    @Test  void extension_questionnaire_optionprefix() throws Exception { assertEqual("extension-questionnaire-optionprefix.json"); }
    @Test  void extension_questionnaire_referencefilter() throws Exception { assertEqual("extension-questionnaire-referencefilter.json"); }
    @Test  void extension_questionnaire_referenceprofile() throws Exception { assertEqual("extension-questionnaire-referenceprofile.json"); }
    @Test  void extension_questionnaire_referenceresource() throws Exception { assertEqual("extension-questionnaire-referenceresource.json"); }
    @Test  void extension_questionnaire_signaturerequired() throws Exception { assertEqual("extension-questionnaire-signaturerequired.json"); }
    @Test  void extension_questionnaire_sliderstepvalue() throws Exception { assertEqual("extension-questionnaire-sliderstepvalue.json"); }
    @Test  void extension_questionnaire_supportlink() throws Exception { assertEqual("extension-questionnaire-supportlink.json"); }
    @Test  void extension_questionnaire_unit() throws Exception { assertEqual("extension-questionnaire-unit.json"); }
    @Test  void extension_questionnaire_unitoption() throws Exception { assertEqual("extension-questionnaire-unitoption.json"); }
    @Test  void extension_questionnaire_unitvalueset() throws Exception { assertEqual("extension-questionnaire-unitvalueset.json"); }
    @Test  void extension_questionnaire_usagemode() throws Exception { assertEqual("extension-questionnaire-usagemode.json"); }
    @Test  void extension_questionnaireresponse_author() throws Exception { assertEqual("extension-questionnaireresponse-author.json"); }
    @Test  void extension_questionnaireresponse_completionmode() throws Exception { assertEqual("extension-questionnaireresponse-completionmode.json"); }
    @Test  void extension_questionnaireresponse_reason() throws Exception { assertEqual("extension-questionnaireresponse-reason.json"); }
    @Test  void extension_questionnaireresponse_reviewer() throws Exception { assertEqual("extension-questionnaireresponse-reviewer.json"); }
    @Test  void extension_questionnaireresponse_signature() throws Exception { assertEqual("extension-questionnaireresponse-signature.json"); }
    @Test  void extension_regex() throws Exception { assertEqual("extension-regex.json"); }
    @Test @Tag("multi")  void extension_relative_date() throws Exception { assertEqual("extension-relative-date.json"); }
    @Test @Tag("multi")  void extension_rendered_value() throws Exception { assertEqual("extension-rendered-value.json"); }
    @Test  void extension_rendering_markdown() throws Exception { assertEqual("extension-rendering-markdown.json"); }
    @Test  void extension_rendering_style() throws Exception { assertEqual("extension-rendering-style.json"); }
    @Test  void extension_rendering_stylesensitive() throws Exception { assertEqual("extension-rendering-stylesensitive.json"); }
    @Test  void extension_rendering_xhtml() throws Exception { assertEqual("extension-rendering-xhtml.json"); }
    @Test @Tag("multi")  void extension_replaces() throws Exception { assertEqual("extension-replaces.json"); }
    @Test  void extension_request_donotperform() throws Exception { assertEqual("extension-request-donotperform.json"); }
    @Test  void extension_request_insurance() throws Exception { assertEqual("extension-request-insurance.json"); }
    @Test  void extension_request_performerorder() throws Exception { assertEqual("extension-request-performerorder.json"); }
    @Test  void extension_request_relevanthistory() throws Exception { assertEqual("extension-request-relevanthistory.json"); }
    @Test  void extension_request_replaces() throws Exception { assertEqual("extension-request-replaces.json"); }
    @Test  void extension_request_statusreason() throws Exception { assertEqual("extension-request-statusreason.json"); }
    @Test @Tag("multi")  void extension_resource_approvaldate() throws Exception { assertEqual("extension-resource-approvaldate.json"); }
    @Test @Tag("multi")  void extension_resource_effectiveperiod() throws Exception { assertEqual("extension-resource-effectiveperiod.json"); }
    @Test @Tag("multi")  void extension_resource_lastreviewdate() throws Exception { assertEqual("extension-resource-lastreviewdate.json"); }
    @Test  void extension_resource_pertainstogoal() throws Exception { assertEqual("extension-resource-pertainstogoal.json"); }
    @Test @Tag("multi")  void extension_servicerequest_geneticsitem() throws Exception { assertEqual("extension-servicerequest-geneticsitem.json"); }
    @Test  void extension_servicerequest_precondition() throws Exception { assertEqual("extension-servicerequest-precondition.json"); }
    @Test  void extension_servicerequest_questionnairerequest() throws Exception { assertEqual("extension-servicerequest-questionnairerequest.json"); }
    @Test  void extension_specimen_collectionpriority() throws Exception { assertEqual("extension-specimen-collectionpriority.json"); }
    @Test  void extension_specimen_isdryweight() throws Exception { assertEqual("extension-specimen-isdryweight.json"); }
    @Test  void extension_specimen_processingtime() throws Exception { assertEqual("extension-specimen-processingtime.json"); }
    @Test  void extension_specimen_sequencenumber() throws Exception { assertEqual("extension-specimen-sequencenumber.json"); }
    @Test  void extension_specimen_specialhandling() throws Exception { assertEqual("extension-specimen-specialhandling.json"); }
    @Test  void extension_structuredefinition_ancestor() throws Exception { assertEqual("extension-structuredefinition-ancestor.json"); }
    @Test  void extension_structuredefinition_applicable_version() throws Exception { assertEqual("extension-structuredefinition-applicable-version.json"); }
    @Test  void extension_structuredefinition_category() throws Exception { assertEqual("extension-structuredefinition-category.json"); }
    @Test  void extension_structuredefinition_codegen_super() throws Exception { assertEqual("extension-structuredefinition-codegen-super.json"); }
    @Test  void extension_structuredefinition_dependencies() throws Exception { assertEqual("extension-structuredefinition-dependencies.json"); }
    @Test  void extension_structuredefinition_display_hint() throws Exception { assertEqual("extension-structuredefinition-display-hint.json"); }
    @Test  void extension_structuredefinition_explicit_type_name() throws Exception { assertEqual("extension-structuredefinition-explicit-type-name.json"); }
    @Test  void extension_structuredefinition_fhir_type() throws Exception { assertEqual("extension-structuredefinition-fhir-type.json"); }
    @Test  void extension_structuredefinition_fmm_no_warnings() throws Exception { assertEqual("extension-structuredefinition-fmm-no-warnings.json"); }
    @Test  void extension_structuredefinition_fmm() throws Exception { assertEqual("extension-structuredefinition-fmm.json"); }
    @Test  void extension_structuredefinition_hierarchy() throws Exception { assertEqual("extension-structuredefinition-hierarchy.json"); }
    @Test  void extension_structuredefinition_normative_version() throws Exception { assertEqual("extension-structuredefinition-normative-version.json"); }
    @Test  void extension_structuredefinition_security_category() throws Exception { assertEqual("extension-structuredefinition-security-category.json"); }
    @Test  void extension_structuredefinition_standards_status() throws Exception { assertEqual("extension-structuredefinition-standards-status.json"); }
    @Test  void extension_structuredefinition_summary() throws Exception { assertEqual("extension-structuredefinition-summary.json"); }
    @Test  void extension_structuredefinition_table_name() throws Exception { assertEqual("extension-structuredefinition-table-name.json"); }
    @Test  void extension_structuredefinition_template_status() throws Exception { assertEqual("extension-structuredefinition-template-status.json"); }
    @Test  void extension_structuredefinition_wg() throws Exception { assertEqual("extension-structuredefinition-wg.json"); }
    @Test  void extension_structuredefinition_xml_no_order() throws Exception { assertEqual("extension-structuredefinition-xml-no-order.json"); }
    @Test  void extension_task_candidatelist() throws Exception { assertEqual("extension-task-candidatelist.json"); }
    @Test  void extension_task_replaces() throws Exception { assertEqual("extension-task-replaces.json"); }
    @Test  void extension_timing_dayofmonth() throws Exception { assertEqual("extension-timing-dayofmonth.json"); }
    @Test @Tag("multi")  void extension_timing_daysofcycle() throws Exception { assertEqual("extension-timing-daysofcycle.json"); }
    @Test  void extension_timing_exact() throws Exception { assertEqual("extension-timing-exact.json"); }
    @Test @Tag("multi")  void extension_translation() throws Exception { assertEqual("extension-translation.json"); }
    @Test  void extension_tz_code() throws Exception { assertEqual("extension-tz-code.json"); }
    @Test  void extension_tz_offset() throws Exception { assertEqual("extension-tz-offset.json"); }
    @Test  void extension_usagecontext_group() throws Exception { assertEqual("extension-usagecontext-group.json"); }
    @Test  void extension_valueset_activitystatusdate() throws Exception { assertEqual("extension-valueset-activitystatusdate.json"); }
    @Test  void extension_valueset_author() throws Exception { assertEqual("extension-valueset-author.json"); }
    @Test  void extension_valueset_authoritativesource() throws Exception { assertEqual("extension-valueset-authoritativesource.json"); }
    @Test  void extension_valueset_casesensitive() throws Exception { assertEqual("extension-valueset-casesensitive.json"); }
    @Test  void extension_valueset_concept_comments() throws Exception { assertEqual("extension-valueset-concept-comments.json"); }
    @Test  void extension_valueset_concept_definition() throws Exception { assertEqual("extension-valueset-concept-definition.json"); }
    @Test  void extension_valueset_conceptorder() throws Exception { assertEqual("extension-valueset-conceptorder.json"); }
    @Test  void extension_valueset_deprecated() throws Exception { assertEqual("extension-valueset-deprecated.json"); }
    @Test  void extension_valueset_effectivedate() throws Exception { assertEqual("extension-valueset-effectivedate.json"); }
    @Test @Tag("multi")  void extension_valueset_expand_group() throws Exception { assertEqual("extension-valueset-expand-group.json"); }
    @Test  void extension_valueset_expand_rules() throws Exception { assertEqual("extension-valueset-expand-rules.json"); }
    @Test  void extension_valueset_expansionsource() throws Exception { assertEqual("extension-valueset-expansionsource.json"); }
    @Test  void extension_valueset_expirationdate() throws Exception { assertEqual("extension-valueset-expirationdate.json"); }
    @Test  void extension_valueset_expression() throws Exception { assertEqual("extension-valueset-expression.json"); }
    @Test  void extension_valueset_extensible() throws Exception { assertEqual("extension-valueset-extensible.json"); }
    @Test  void extension_valueset_keyword() throws Exception { assertEqual("extension-valueset-keyword.json"); }
    @Test  void extension_valueset_label() throws Exception { assertEqual("extension-valueset-label.json"); }
    @Test  void extension_valueset_map() throws Exception { assertEqual("extension-valueset-map.json"); }
    @Test @Tag("multi")  void extension_valueset_othername() throws Exception { assertEqual("extension-valueset-othername.json"); }
    @Test  void extension_valueset_parametersource() throws Exception { assertEqual("extension-valueset-parametersource.json"); }
    @Test  void extension_valueset_reference() throws Exception { assertEqual("extension-valueset-reference.json"); }
    @Test  void extension_valueset_rules_text() throws Exception { assertEqual("extension-valueset-rules-text.json"); }
    @Test  void extension_valueset_sourcereference() throws Exception { assertEqual("extension-valueset-sourcereference.json"); }
    @Test  void extension_valueset_special_status() throws Exception { assertEqual("extension-valueset-special-status.json"); }
    @Test  void extension_valueset_steward() throws Exception { assertEqual("extension-valueset-steward.json"); }
    @Test  void extension_valueset_supplement() throws Exception { assertEqual("extension-valueset-supplement.json"); }
    @Test  void extension_valueset_system() throws Exception { assertEqual("extension-valueset-system.json"); }
    @Test  void extension_valueset_systemname() throws Exception { assertEqual("extension-valueset-systemname.json"); }
    @Test  void extension_valueset_systemref() throws Exception { assertEqual("extension-valueset-systemref.json"); }
    @Test  void extension_valueset_toocostly() throws Exception { assertEqual("extension-valueset-toocostly.json"); }
    @Test  void extension_valueset_trusted_expansion() throws Exception { assertEqual("extension-valueset-trusted-expansion.json"); }
    @Test  void extension_valueset_unclosed() throws Exception { assertEqual("extension-valueset-unclosed.json"); }
    @Test @Tag("multi")  void extension_valueset_usage() throws Exception { assertEqual("extension-valueset-usage.json"); }
    @Test  void extension_valueset_warning() throws Exception { assertEqual("extension-valueset-warning.json"); }
    @Test  void extension_valueset_workflowstatus() throws Exception { assertEqual("extension-valueset-workflowstatus.json"); }
    @Test  void extension_variable() throws Exception { assertEqual("extension-variable.json"); }
    @Test @Tag("multi")  void extension_workflow_episodeofcare() throws Exception { assertEqual("extension-workflow-episodeofcare.json"); }
    @Test  void extension_workflow_instantiatescanonical() throws Exception { assertEqual("extension-workflow-instantiatescanonical.json"); }
    @Test  void extension_workflow_instantiatesuri() throws Exception { assertEqual("extension-workflow-instantiatesuri.json"); }
    @Test  void extension_workflow_reasoncode() throws Exception { assertEqual("extension-workflow-reasoncode.json"); }
    @Test  void extension_workflow_reasonreference() throws Exception { assertEqual("extension-workflow-reasonreference.json"); }
    @Test  void extension_workflow_relatedartifact() throws Exception { assertEqual("extension-workflow-relatedartifact.json"); }
    @Test @Tag("multi")  void extension_workflow_researchstudy() throws Exception { assertEqual("extension-workflow-researchstudy.json"); }
    @Test  void extension_workflow_supportinginfo() throws Exception { assertEqual("extension-workflow-supportinginfo.json"); }
    @Test @Tag("multi")  void extension_profile() throws Exception { assertEqual("extension.profile.json"); }
    @Test
    @Tag("bundle")  void external_resources() throws Exception { assertEqual("external-resources.json"); }

}
