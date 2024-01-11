package org.bayisehat.rdb2fhir.core.example.r4;

import org.bayisehat.rdb2fhir.core.example.BaseExampleTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ParameterToV3Group extends BaseExampleTest {

    @Test @Tag("multi")  void parameterdefinition_profile() throws Exception { assertEqual("parameterdefinition.profile.json"); }
    @Test  void parameters_example() throws Exception { assertEqual("parameters-example.json"); }
    @Test  void parameters_questionnaire() throws Exception { assertEqual("parameters-questionnaire.json"); }
    @Test @Tag("multi")  void parameters_profile() throws Exception { assertEqual("parameters.profile.json"); }
    @Test  void patient_example_a() throws Exception { assertEqual("patient-example-a.json"); }
    @Test  void patient_example_animal() throws Exception { assertEqual("patient-example-animal.json"); }
    @Test  void patient_example_b() throws Exception { assertEqual("patient-example-b.json"); }
    @Test  void patient_example_c() throws Exception { assertEqual("patient-example-c.json"); }
    @Test  void patient_example_chinese() throws Exception { assertEqual("patient-example-chinese.json"); }
    @Test  void patient_example_d() throws Exception { assertEqual("patient-example-d.json"); }
    @Test  void patient_example_dicom() throws Exception { assertEqual("patient-example-dicom.json"); }
    @Test  void patient_example_f001_pieter() throws Exception { assertEqual("patient-example-f001-pieter.json"); }
    @Test  void patient_example_f201_roel() throws Exception { assertEqual("patient-example-f201-roel.json"); }
    @Test  void patient_example_ihe_pcd() throws Exception { assertEqual("patient-example-ihe-pcd.json"); }
    @Test  void patient_example_infant_fetal() throws Exception { assertEqual("patient-example-infant-fetal.json"); }
    @Test  void patient_example_infant_mom() throws Exception { assertEqual("patient-example-infant-mom.json"); }
    @Test  void patient_example_infant_twin_1() throws Exception { assertEqual("patient-example-infant-twin-1.json"); }
    @Test  void patient_example_infant_twin_2() throws Exception { assertEqual("patient-example-infant-twin-2.json"); }
    @Test  void patient_example_mom() throws Exception { assertEqual("patient-example-mom.json"); }
    @Test  void patient_example_newborn() throws Exception { assertEqual("patient-example-newborn.json"); }
    @Test  void patient_example_proband() throws Exception { assertEqual("patient-example-proband.json"); }
    @Test  void patient_example_xcda() throws Exception { assertEqual("patient-example-xcda.json"); }
    @Test  void patient_example_xds() throws Exception { assertEqual("patient-example-xds.json"); }
    @Test  void patient_example() throws Exception { assertEqual("patient-example.json"); }
    @Test @Tag("bundle")  void patient_examples_cypress_template() throws Exception { assertEqual("patient-examples-cypress-template.json"); }
    @Test @Tag("bundle")  void patient_examples_general() throws Exception { assertEqual("patient-examples-general.json"); }
    @Test  void patient_extensions_Patient_age() throws Exception { assertEqual("patient-extensions-Patient-age.json"); }
    @Test  void patient_extensions_Patient_birthOrderBoolean() throws Exception { assertEqual("patient-extensions-Patient-birthOrderBoolean.json"); }
    @Test  void patient_extensions_Patient_mothersMaidenName() throws Exception { assertEqual("patient-extensions-Patient-mothersMaidenName.json"); }
    @Test  void patient_genetics_example1() throws Exception { assertEqual("patient-genetics-example1.json"); }
    @Test  void patient_glossy_example() throws Exception { assertEqual("patient-glossy-example.json"); }
    @Test  void patient_questionnaire() throws Exception { assertEqual("patient-questionnaire.json"); }
    @Test  void patient_profile() throws Exception { assertEqual("patient.profile.json"); }
    @Test  void paymentnotice_example() throws Exception { assertEqual("paymentnotice-example.json"); }
    @Test  void paymentnotice_questionnaire() throws Exception { assertEqual("paymentnotice-questionnaire.json"); }
    @Test  void paymentnotice_profile() throws Exception { assertEqual("paymentnotice.profile.json"); }
    @Test  void paymentreconciliation_example() throws Exception { assertEqual("paymentreconciliation-example.json"); }
    @Test  void paymentreconciliation_questionnaire() throws Exception { assertEqual("paymentreconciliation-questionnaire.json"); }
    @Test  void paymentreconciliation_profile() throws Exception { assertEqual("paymentreconciliation.profile.json"); }
    @Test  void pcd_example_notAuthor() throws Exception { assertEqual("pcd-example-notAuthor.json"); }
    @Test  void pcd_example_notLabs() throws Exception { assertEqual("pcd-example-notLabs.json"); }
    @Test  void pcd_example_notOrg() throws Exception { assertEqual("pcd-example-notOrg.json"); }
    @Test  void pcd_example_notThem() throws Exception { assertEqual("pcd-example-notThem.json"); }
    @Test  void pcd_example_notThis() throws Exception { assertEqual("pcd-example-notThis.json"); }
    @Test  void period_profile() throws Exception { assertEqual("period.profile.json"); }
    @Test  void person_example_f002_ariadne() throws Exception { assertEqual("person-example-f002-ariadne.json"); }
    @Test  void person_example() throws Exception { assertEqual("person-example.json"); }
    @Test  void person_grahame() throws Exception { assertEqual("person-grahame.json"); }
    @Test  void person_patient_portal() throws Exception { assertEqual("person-patient-portal.json"); }
    @Test  void person_provider_directory() throws Exception { assertEqual("person-provider-directory.json"); }
    @Test  void person_questionnaire() throws Exception { assertEqual("person-questionnaire.json"); }
    @Test  void person_profile() throws Exception { assertEqual("person.profile.json"); }
    @Test  void picoelement_questionnaire() throws Exception { assertEqual("picoelement-questionnaire.json"); }
    @Test  void picoelement_profile() throws Exception { assertEqual("picoelement.profile.json"); }
    @Test  void plandefinition_chlamydia_screening_intervention() throws Exception { assertEqual("plandefinition-chlamydia-screening-intervention.json"); }
    @Test @Tag("multi")  void plandefinition_example_cardiology_os() throws Exception { assertEqual("plandefinition-example-cardiology-os.json"); }
    @Test  void plandefinition_example_kdn5_simplified() throws Exception { assertEqual("plandefinition-example-kdn5-simplified.json"); }
    @Test  void plandefinition_example() throws Exception { assertEqual("plandefinition-example.json"); }
    @Test  void plandefinition_exclusive_breastfeeding_intervention_01() throws Exception { assertEqual("plandefinition-exclusive-breastfeeding-intervention-01.json"); }
    @Test  void plandefinition_exclusive_breastfeeding_intervention_02() throws Exception { assertEqual("plandefinition-exclusive-breastfeeding-intervention-02.json"); }
    @Test  void plandefinition_exclusive_breastfeeding_intervention_03() throws Exception { assertEqual("plandefinition-exclusive-breastfeeding-intervention-03.json"); }
    @Test  void plandefinition_exclusive_breastfeeding_intervention_04() throws Exception { assertEqual("plandefinition-exclusive-breastfeeding-intervention-04.json"); }
    @Test  void plandefinition_opioidcds_04() throws Exception { assertEqual("plandefinition-opioidcds-04.json"); }
    @Test  void plandefinition_opioidcds_05() throws Exception { assertEqual("plandefinition-opioidcds-05.json"); }
    @Test  void plandefinition_opioidcds_07() throws Exception { assertEqual("plandefinition-opioidcds-07.json"); }
    @Test  void plandefinition_opioidcds_08() throws Exception { assertEqual("plandefinition-opioidcds-08.json"); }
    @Test  void plandefinition_opioidcds_10() throws Exception { assertEqual("plandefinition-opioidcds-10.json"); }
    @Test  void plandefinition_opioidcds_11() throws Exception { assertEqual("plandefinition-opioidcds-11.json"); }
    @Test  void plandefinition_options_example() throws Exception { assertEqual("plandefinition-options-example.json"); }
    @Test  void plandefinition_predecessor_example() throws Exception { assertEqual("plandefinition-predecessor-example.json"); }
    @Test  void plandefinition_protocol_example() throws Exception { assertEqual("plandefinition-protocol-example.json"); }
    @Test  void plandefinition_questionnaire() throws Exception { assertEqual("plandefinition-questionnaire.json"); }
    @Test  void plandefinition_zika_virus_intervention() throws Exception { assertEqual("plandefinition-zika-virus-intervention.json"); }
    @Test  void plandefinition_profile() throws Exception { assertEqual("plandefinition.profile.json"); }
    @Test  void population_profile() throws Exception { assertEqual("population.profile.json"); }
    @Test  void positiveint_profile() throws Exception { assertEqual("positiveint.profile.json"); }
    @Test  void practitioner_example_f001_evdb() throws Exception { assertEqual("practitioner-example-f001-evdb.json"); }
    @Test  void practitioner_example_f002_pv() throws Exception { assertEqual("practitioner-example-f002-pv.json"); }
    @Test  void practitioner_example_f003_mv() throws Exception { assertEqual("practitioner-example-f003-mv.json"); }
    @Test  void practitioner_example_f004_rb() throws Exception { assertEqual("practitioner-example-f004-rb.json"); }
    @Test  void practitioner_example_f005_al() throws Exception { assertEqual("practitioner-example-f005-al.json"); }
    @Test  void practitioner_example_f006_rvdb() throws Exception { assertEqual("practitioner-example-f006-rvdb.json"); }
    @Test  void practitioner_example_f007_sh() throws Exception { assertEqual("practitioner-example-f007-sh.json"); }
    @Test  void practitioner_example_f201_ab() throws Exception { assertEqual("practitioner-example-f201-ab.json"); }
    @Test  void practitioner_example_f202_lm() throws Exception { assertEqual("practitioner-example-f202-lm.json"); }
    @Test  void practitioner_example_f203_jvg() throws Exception { assertEqual("practitioner-example-f203-jvg.json"); }
    @Test  void practitioner_example_f204_ce() throws Exception { assertEqual("practitioner-example-f204-ce.json"); }
    @Test  void practitioner_example_xcda_author() throws Exception { assertEqual("practitioner-example-xcda-author.json"); }
    @Test  void practitioner_example_xcda1() throws Exception { assertEqual("practitioner-example-xcda1.json"); }
    @Test  void practitioner_example() throws Exception { assertEqual("practitioner-example.json"); }
    @Test @Tag("bundle")  void practitioner_examples_general() throws Exception { assertEqual("practitioner-examples-general.json"); }
    @Test  void practitioner_questionnaire() throws Exception { assertEqual("practitioner-questionnaire.json"); }
    @Test  void practitioner_profile() throws Exception { assertEqual("practitioner.profile.json"); }
    @Test  void practitionerrole_example() throws Exception { assertEqual("practitionerrole-example.json"); }
    @Test @Tag("bundle")  void practitionerrole_examples_general() throws Exception { assertEqual("practitionerrole-examples-general.json"); }
    @Test  void practitionerrole_questionnaire() throws Exception { assertEqual("practitionerrole-questionnaire.json"); }
    @Test  void practitionerrole_profile() throws Exception { assertEqual("practitionerrole.profile.json"); }
    @Test  void procedure_example_HCBS() throws Exception { assertEqual("procedure-example-HCBS.json"); }
    @Test  void procedure_example_ambulation() throws Exception { assertEqual("procedure-example-ambulation.json"); }
    @Test  void procedure_example_appendectomy_narrative() throws Exception { assertEqual("procedure-example-appendectomy-narrative.json"); }
    @Test  void procedure_example_biopsy() throws Exception { assertEqual("procedure-example-biopsy.json"); }
    @Test  void procedure_example_colon_biopsy() throws Exception { assertEqual("procedure-example-colon-biopsy.json"); }
    @Test  void procedure_example_colonoscopy() throws Exception { assertEqual("procedure-example-colonoscopy.json"); }
    @Test  void procedure_example_education() throws Exception { assertEqual("procedure-example-education.json"); }
    @Test  void procedure_example_f001_heart() throws Exception { assertEqual("procedure-example-f001-heart.json"); }
    @Test  void procedure_example_f002_lung() throws Exception { assertEqual("procedure-example-f002-lung.json"); }
    @Test  void procedure_example_f003_abscess() throws Exception { assertEqual("procedure-example-f003-abscess.json"); }
    @Test  void procedure_example_f004_tracheotomy() throws Exception { assertEqual("procedure-example-f004-tracheotomy.json"); }
    @Test  void procedure_example_f201_tpf() throws Exception { assertEqual("procedure-example-f201-tpf.json"); }
    @Test  void procedure_example_implant() throws Exception { assertEqual("procedure-example-implant.json"); }
    @Test  void procedure_example_ob() throws Exception { assertEqual("procedure-example-ob.json"); }
    @Test  void procedure_example_physical_therapy() throws Exception { assertEqual("procedure-example-physical-therapy.json"); }
    @Test  void procedure_example() throws Exception { assertEqual("procedure-example.json"); }
    @Test  void procedure_questionnaire() throws Exception { assertEqual("procedure-questionnaire.json"); }
    @Test  void procedure_profile() throws Exception { assertEqual("procedure.profile.json"); }
    @Test @Tag("multi")  void prodcharacteristic_profile() throws Exception { assertEqual("prodcharacteristic.profile.json"); }
    @Test  void productshelflife_profile() throws Exception { assertEqual("productshelflife.profile.json"); }
    @Test @Tag("bundle")  void profiles_others() throws Exception { assertEqual("profiles-others.json"); }
    @Test @Tag("bundle")  void profiles_resources() throws Exception { assertEqual("profiles-resources.json"); }
    @Test @Tag("bundle")  void profiles_types() throws Exception { assertEqual("profiles-types.json"); }
    @Test  void provenance_consent_signature() throws Exception { assertEqual("provenance-consent-signature.json"); }
    @Test  void provenance_example_biocompute_object() throws Exception { assertEqual("provenance-example-biocompute-object.json"); }
    @Test  void provenance_example_cwl() throws Exception { assertEqual("provenance-example-cwl.json"); }
    @Test  void provenance_example_sig() throws Exception { assertEqual("provenance-example-sig.json"); }
    @Test  void provenance_example() throws Exception { assertEqual("provenance-example.json"); }
    @Test  void provenance_questionnaire() throws Exception { assertEqual("provenance-questionnaire.json"); }
    @Test  void provenance_relevant_history_questionnaire() throws Exception { assertEqual("provenance-relevant-history-questionnaire.json"); }
    @Test  void provenance_relevant_history_profile() throws Exception { assertEqual("provenance-relevant-history.profile.json"); }
    @Test  void provenance_profile() throws Exception { assertEqual("provenance.profile.json"); }
    @Test  void quantity_profile() throws Exception { assertEqual("quantity.profile.json"); }
    @Test  void questionnaire_cqf_example() throws Exception { assertEqual("questionnaire-cqf-example.json"); }
    @Test  void questionnaire_example_bluebook() throws Exception { assertEqual("questionnaire-example-bluebook.json"); }
    @Test  void questionnaire_example_f201_lifelines() throws Exception { assertEqual("questionnaire-example-f201-lifelines.json"); }
    @Test  void questionnaire_example_gcs() throws Exception { assertEqual("questionnaire-example-gcs.json"); }
    @Test  void questionnaire_example() throws Exception { assertEqual("questionnaire-example.json"); }
    @Test @Tag("bundle")  void questionnaire_profile_example_ussg_fht() throws Exception { assertEqual("questionnaire-profile-example-ussg-fht.json"); }
    @Test  void questionnaire_questionnaire() throws Exception { assertEqual("questionnaire-questionnaire.json"); }
    @Test  void questionnaire_zika_virus_exposure_assessment() throws Exception { assertEqual("questionnaire-zika-virus-exposure-assessment.json"); }
    @Test  void questionnaire_profile() throws Exception { assertEqual("questionnaire.profile.json"); }
    @Test  void questionnaireresponse_example_bluebook() throws Exception { assertEqual("questionnaireresponse-example-bluebook.json"); }
    @Test  void questionnaireresponse_example_f201_lifelines() throws Exception { assertEqual("questionnaireresponse-example-f201-lifelines.json"); }
    @Test  void questionnaireresponse_example_gcs() throws Exception { assertEqual("questionnaireresponse-example-gcs.json"); }
    @Test  void questionnaireresponse_example_ussg_fht_answers() throws Exception { assertEqual("questionnaireresponse-example-ussg-fht-answers.json"); }
    @Test  void questionnaireresponse_example() throws Exception { assertEqual("questionnaireresponse-example.json"); }
    @Test  void questionnaireresponse_extensions_QuestionnaireResponse_item_subject() throws Exception { assertEqual("questionnaireresponse-extensions-QuestionnaireResponse-item-subject.json"); }
    @Test  void questionnaireresponse_questionnaire() throws Exception { assertEqual("questionnaireresponse-questionnaire.json"); }
    @Test  void questionnaireresponse_profile() throws Exception { assertEqual("questionnaireresponse.profile.json"); }
    @Test  void range_profile() throws Exception { assertEqual("range.profile.json"); }
    @Test  void ratio_profile() throws Exception { assertEqual("ratio.profile.json"); }
    @Test  void reference_profile() throws Exception { assertEqual("reference.profile.json"); }
    @Test @Tag("multi")  void relatedartifact_profile() throws Exception { assertEqual("relatedartifact.profile.json"); }
    @Test  void relatedperson_example_f001_sarah() throws Exception { assertEqual("relatedperson-example-f001-sarah.json"); }
    @Test  void relatedperson_example_f002_ariadne() throws Exception { assertEqual("relatedperson-example-f002-ariadne.json"); }
    @Test  void relatedperson_example_newborn_mom() throws Exception { assertEqual("relatedperson-example-newborn-mom.json"); }
    @Test  void relatedperson_example_peter() throws Exception { assertEqual("relatedperson-example-peter.json"); }
    @Test  void relatedperson_example() throws Exception { assertEqual("relatedperson-example.json"); }
    @Test  void relatedperson_questionnaire() throws Exception { assertEqual("relatedperson-questionnaire.json"); }
    @Test  void relatedperson_profile() throws Exception { assertEqual("relatedperson.profile.json"); }
    @Test @Tag("multi")  void request() throws Exception { assertEqual("request.json"); }
    @Test  void requestgroup_example() throws Exception { assertEqual("requestgroup-example.json"); }
    @Test  void requestgroup_kdn5_example() throws Exception { assertEqual("requestgroup-kdn5-example.json"); }
    @Test  void requestgroup_questionnaire() throws Exception { assertEqual("requestgroup-questionnaire.json"); }
    @Test  void requestgroup_profile() throws Exception { assertEqual("requestgroup.profile.json"); }
    @Test  void researchdefinition_example() throws Exception { assertEqual("researchdefinition-example.json"); }
    @Test  void researchdefinition_questionnaire() throws Exception { assertEqual("researchdefinition-questionnaire.json"); }
    @Test  void researchdefinition_profile() throws Exception { assertEqual("researchdefinition.profile.json"); }
    @Test  void researchelementdefinition_example() throws Exception { assertEqual("researchelementdefinition-example.json"); }
    @Test  void researchelementdefinition_questionnaire() throws Exception { assertEqual("researchelementdefinition-questionnaire.json"); }
    @Test  void researchelementdefinition_profile() throws Exception { assertEqual("researchelementdefinition.profile.json"); }
    @Test  void researchstudy_example() throws Exception { assertEqual("researchstudy-example.json"); }
    @Test  void researchstudy_questionnaire() throws Exception { assertEqual("researchstudy-questionnaire.json"); }
    @Test  void researchstudy_profile() throws Exception { assertEqual("researchstudy.profile.json"); }
    @Test  void researchsubject_example() throws Exception { assertEqual("researchsubject-example.json"); }
    @Test  void researchsubject_questionnaire() throws Exception { assertEqual("researchsubject-questionnaire.json"); }
    @Test  void researchsubject_profile() throws Exception { assertEqual("researchsubject.profile.json"); }
    @Test  void resource_profile() throws Exception { assertEqual("resource.profile.json"); }
    @Test  void resprate_questionnaire() throws Exception { assertEqual("resprate-questionnaire.json"); }
    @Test  void resprate_profile() throws Exception { assertEqual("resprate.profile.json"); }
    @Test  void riskassessment_example_breastcancer() throws Exception { assertEqual("riskassessment-example-breastcancer.json"); }
    @Test  void riskassessment_example_cardiac() throws Exception { assertEqual("riskassessment-example-cardiac.json"); }
    @Test  void riskassessment_example_population() throws Exception { assertEqual("riskassessment-example-population.json"); }
    @Test  void riskassessment_example_prognosis() throws Exception { assertEqual("riskassessment-example-prognosis.json"); }
    @Test  void riskassessment_example() throws Exception { assertEqual("riskassessment-example.json"); }
    @Test  void riskassessment_questionnaire() throws Exception { assertEqual("riskassessment-questionnaire.json"); }
    @Test  void riskassessment_riskexample() throws Exception { assertEqual("riskassessment-riskexample.json"); }
    @Test  void riskassessment_profile() throws Exception { assertEqual("riskassessment.profile.json"); }
    @Test  void riskevidencesynthesis_example() throws Exception { assertEqual("riskevidencesynthesis-example.json"); }
    @Test  void riskevidencesynthesis_questionnaire() throws Exception { assertEqual("riskevidencesynthesis-questionnaire.json"); }
    @Test  void riskevidencesynthesis_profile() throws Exception { assertEqual("riskevidencesynthesis.profile.json"); }
    @Test @Tag("multi")  void sampleddata_profile() throws Exception { assertEqual("sampleddata.profile.json"); }
    @Test  void sc_valueset_account_status() throws Exception { assertEqual("sc-valueset-account-status.json"); }
    @Test  void sc_valueset_allergyintolerance_clinical() throws Exception { assertEqual("sc-valueset-allergyintolerance-clinical.json"); }
    @Test  void sc_valueset_allergyintolerance_verification() throws Exception { assertEqual("sc-valueset-allergyintolerance-verification.json"); }
    @Test @Tag("multi")  void sc_valueset_appointmentstatus() throws Exception { assertEqual("sc-valueset-appointmentstatus.json"); }
    @Test @Tag("multi")  void sc_valueset_care_plan_activity_status() throws Exception { assertEqual("sc-valueset-care-plan-activity-status.json"); }
    @Test  void sc_valueset_care_team_status() throws Exception { assertEqual("sc-valueset-care-team-status.json"); }
    @Test  void sc_valueset_chargeitem_status() throws Exception { assertEqual("sc-valueset-chargeitem-status.json"); }
    @Test  void sc_valueset_clinicalimpression_status() throws Exception { assertEqual("sc-valueset-clinicalimpression-status.json"); }
    @Test  void sc_valueset_composition_status() throws Exception { assertEqual("sc-valueset-composition-status.json"); }
    @Test  void sc_valueset_condition_ver_status() throws Exception { assertEqual("sc-valueset-condition-ver-status.json"); }
    @Test  void sc_valueset_consent_state_codes() throws Exception { assertEqual("sc-valueset-consent-state-codes.json"); }
    @Test @Tag("multi")  void sc_valueset_contract_publicationstatus() throws Exception { assertEqual("sc-valueset-contract-publicationstatus.json"); }
    @Test @Tag("multi")  void sc_valueset_contract_status() throws Exception { assertEqual("sc-valueset-contract-status.json"); }
    @Test  void sc_valueset_device_statement_status() throws Exception { assertEqual("sc-valueset-device-statement-status.json"); }
    @Test  void sc_valueset_device_status() throws Exception { assertEqual("sc-valueset-device-status.json"); }
    @Test  void sc_valueset_diagnostic_report_status() throws Exception { assertEqual("sc-valueset-diagnostic-report-status.json"); }
    @Test  void sc_valueset_document_reference_status() throws Exception { assertEqual("sc-valueset-document-reference-status.json"); }
    @Test  void sc_valueset_encounter_location_status() throws Exception { assertEqual("sc-valueset-encounter-location-status.json"); }
    @Test @Tag("multi")  void sc_valueset_encounter_status() throws Exception { assertEqual("sc-valueset-encounter-status.json"); }
    @Test  void sc_valueset_endpoint_status() throws Exception { assertEqual("sc-valueset-endpoint-status.json"); }
    @Test  void sc_valueset_episode_of_care_status() throws Exception { assertEqual("sc-valueset-episode-of-care-status.json"); }
    @Test  void sc_valueset_event_status() throws Exception { assertEqual("sc-valueset-event-status.json"); }
    @Test  void sc_valueset_explanationofbenefit_status() throws Exception { assertEqual("sc-valueset-explanationofbenefit-status.json"); }
    @Test  void sc_valueset_flag_status() throws Exception { assertEqual("sc-valueset-flag-status.json"); }
    @Test  void sc_valueset_fm_status() throws Exception { assertEqual("sc-valueset-fm-status.json"); }
    @Test  void sc_valueset_goal_achievement() throws Exception { assertEqual("sc-valueset-goal-achievement.json"); }
    @Test @Tag("multi")  void sc_valueset_goal_status() throws Exception { assertEqual("sc-valueset-goal-status.json"); }
    @Test  void sc_valueset_guidance_response_status() throws Exception { assertEqual("sc-valueset-guidance-response-status.json"); }
    @Test  void sc_valueset_history_status() throws Exception { assertEqual("sc-valueset-history-status.json"); }
    @Test  void sc_valueset_imagingstudy_status() throws Exception { assertEqual("sc-valueset-imagingstudy-status.json"); }
    @Test  void sc_valueset_immunization_evaluation_status() throws Exception { assertEqual("sc-valueset-immunization-evaluation-status.json"); }
    @Test  void sc_valueset_immunization_status() throws Exception { assertEqual("sc-valueset-immunization-status.json"); }
    @Test  void sc_valueset_invoice_status() throws Exception { assertEqual("sc-valueset-invoice-status.json"); }
    @Test  void sc_valueset_list_status() throws Exception { assertEqual("sc-valueset-list-status.json"); }
    @Test  void sc_valueset_location_status() throws Exception { assertEqual("sc-valueset-location-status.json"); }
    @Test  void sc_valueset_measure_report_status() throws Exception { assertEqual("sc-valueset-measure-report-status.json"); }
    @Test  void sc_valueset_medication_admin_status() throws Exception { assertEqual("sc-valueset-medication-admin-status.json"); }
    @Test  void sc_valueset_medication_statement_status() throws Exception { assertEqual("sc-valueset-medication-statement-status.json"); }
    @Test  void sc_valueset_medication_status() throws Exception { assertEqual("sc-valueset-medication-status.json"); }
    @Test  void sc_valueset_medicationdispense_status() throws Exception { assertEqual("sc-valueset-medicationdispense-status.json"); }
    @Test  void sc_valueset_medicationknowledge_status() throws Exception { assertEqual("sc-valueset-medicationknowledge-status.json"); }
    @Test  void sc_valueset_medicationrequest_status() throws Exception { assertEqual("sc-valueset-medicationrequest-status.json"); }
    @Test  void sc_valueset_metric_operational_status() throws Exception { assertEqual("sc-valueset-metric-operational-status.json"); }
    @Test  void sc_valueset_observation_status() throws Exception { assertEqual("sc-valueset-observation-status.json"); }
    @Test  void sc_valueset_participationstatus() throws Exception { assertEqual("sc-valueset-participationstatus.json"); }
    @Test  void sc_valueset_product_status() throws Exception { assertEqual("sc-valueset-product-status.json"); }
    @Test  void sc_valueset_publication_status() throws Exception { assertEqual("sc-valueset-publication-status.json"); }
    @Test  void sc_valueset_questionnaire_answers_status() throws Exception { assertEqual("sc-valueset-questionnaire-answers-status.json"); }
    @Test  void sc_valueset_report_status_codes() throws Exception { assertEqual("sc-valueset-report-status-codes.json"); }
    @Test  void sc_valueset_request_status() throws Exception { assertEqual("sc-valueset-request-status.json"); }
    @Test  void sc_valueset_research_study_status() throws Exception { assertEqual("sc-valueset-research-study-status.json"); }
    @Test  void sc_valueset_research_subject_status() throws Exception { assertEqual("sc-valueset-research-subject-status.json"); }
    @Test  void sc_valueset_slotstatus() throws Exception { assertEqual("sc-valueset-slotstatus.json"); }
    @Test  void sc_valueset_specimen_status() throws Exception { assertEqual("sc-valueset-specimen-status.json"); }
    @Test  void sc_valueset_subscription_status() throws Exception { assertEqual("sc-valueset-subscription-status.json"); }
    @Test  void sc_valueset_substance_status() throws Exception { assertEqual("sc-valueset-substance-status.json"); }
    @Test  void sc_valueset_supplydelivery_status() throws Exception { assertEqual("sc-valueset-supplydelivery-status.json"); }
    @Test  void sc_valueset_supplyrequest_status() throws Exception { assertEqual("sc-valueset-supplyrequest-status.json"); }
    @Test @Tag("multi")  void sc_valueset_task_status() throws Exception { assertEqual("sc-valueset-task-status.json"); }
    @Test  void sc_valueset_verificationresult_status() throws Exception { assertEqual("sc-valueset-verificationresult-status.json"); }
    @Test  void sc_valueset_verificationresult_validation_status() throws Exception { assertEqual("sc-valueset-verificationresult-validation-status.json"); }
    @Test  void schedule_example() throws Exception { assertEqual("schedule-example.json"); }
    @Test  void schedule_provider_location1_example() throws Exception { assertEqual("schedule-provider-location1-example.json"); }
    @Test  void schedule_provider_location2_example() throws Exception { assertEqual("schedule-provider-location2-example.json"); }
    @Test  void schedule_questionnaire() throws Exception { assertEqual("schedule-questionnaire.json"); }
    @Test  void schedule_profile() throws Exception { assertEqual("schedule.profile.json"); }
    @Test @Tag("bundle")  void search_parameters() throws Exception { assertEqual("search-parameters.json"); }
    @Test  void searchparameter_example_extension() throws Exception { assertEqual("searchparameter-example-extension.json"); }
    @Test  void searchparameter_example_reference() throws Exception { assertEqual("searchparameter-example-reference.json"); }
    @Test  void searchparameter_example() throws Exception { assertEqual("searchparameter-example.json"); }
    @Test  void searchparameter_filter() throws Exception { assertEqual("searchparameter-filter.json"); }
    @Test  void searchparameter_questionnaire() throws Exception { assertEqual("searchparameter-questionnaire.json"); }
    @Test  void searchparameter_profile() throws Exception { assertEqual("searchparameter.profile.json"); }
    @Test  void sequence_complex_variant() throws Exception { assertEqual("sequence-complex-variant.json"); }
    @Test  void sequence_example_TPMT_one() throws Exception { assertEqual("sequence-example-TPMT-one.json"); }
    @Test  void sequence_example_TPMT_two() throws Exception { assertEqual("sequence-example-TPMT-two.json"); }
    @Test  void sequence_example_fda_comparisons() throws Exception { assertEqual("sequence-example-fda-comparisons.json"); }
    @Test  void sequence_example_fda_vcfeval() throws Exception { assertEqual("sequence-example-fda-vcfeval.json"); }
    @Test  void sequence_example_fda() throws Exception { assertEqual("sequence-example-fda.json"); }
    @Test  void sequence_example_pgx_1() throws Exception { assertEqual("sequence-example-pgx-1.json"); }
    @Test  void sequence_example_pgx_2() throws Exception { assertEqual("sequence-example-pgx-2.json"); }
    @Test  void sequence_genetics_example_breastcancer() throws Exception { assertEqual("sequence-genetics-example-breastcancer.json"); }
    @Test  void sequence_graphic_example_1() throws Exception { assertEqual("sequence-graphic-example-1.json"); }
    @Test  void sequence_graphic_example_2() throws Exception { assertEqual("sequence-graphic-example-2.json"); }
    @Test  void sequence_graphic_example_3() throws Exception { assertEqual("sequence-graphic-example-3.json"); }
    @Test  void sequence_graphic_example_4() throws Exception { assertEqual("sequence-graphic-example-4.json"); }
    @Test  void sequence_graphic_example_5() throws Exception { assertEqual("sequence-graphic-example-5.json"); }
    @Test  void servicerequest_example_ambulation() throws Exception { assertEqual("servicerequest-example-ambulation.json"); }
    @Test  void servicerequest_example_appendectomy() throws Exception { assertEqual("servicerequest-example-appendectomy.json"); }
    @Test  void servicerequest_example_colonoscopy_bx() throws Exception { assertEqual("servicerequest-example-colonoscopy-bx.json"); }
    @Test  void servicerequest_example_colonoscopy() throws Exception { assertEqual("servicerequest-example-colonoscopy.json"); }
    @Test  void servicerequest_example_di() throws Exception { assertEqual("servicerequest-example-di.json"); }
    @Test  void servicerequest_example_edu() throws Exception { assertEqual("servicerequest-example-edu.json"); }
    @Test  void servicerequest_example_ft4() throws Exception { assertEqual("servicerequest-example-ft4.json"); }
    @Test  void servicerequest_example_implant() throws Exception { assertEqual("servicerequest-example-implant.json"); }
    @Test  void servicerequest_example_lipid() throws Exception { assertEqual("servicerequest-example-lipid.json"); }
    @Test  void servicerequest_example_myringotomy() throws Exception { assertEqual("servicerequest-example-myringotomy.json"); }
    @Test  void servicerequest_example_ob() throws Exception { assertEqual("servicerequest-example-ob.json"); }
    @Test  void servicerequest_example_pgx() throws Exception { assertEqual("servicerequest-example-pgx.json"); }
    @Test  void servicerequest_example_pt() throws Exception { assertEqual("servicerequest-example-pt.json"); }
    @Test  void servicerequest_example_subrequest() throws Exception { assertEqual("servicerequest-example-subrequest.json"); }
    @Test  void servicerequest_example_ventilation() throws Exception { assertEqual("servicerequest-example-ventilation.json"); }
    @Test  void servicerequest_example() throws Exception { assertEqual("servicerequest-example.json"); }
    @Test  void servicerequest_example2() throws Exception { assertEqual("servicerequest-example2.json"); }
    @Test  void servicerequest_example3() throws Exception { assertEqual("servicerequest-example3.json"); }
    @Test  void servicerequest_example4() throws Exception { assertEqual("servicerequest-example4.json"); }
    @Test  void servicerequest_genetics_example_1() throws Exception { assertEqual("servicerequest-genetics-example-1.json"); }
    @Test  void servicerequest_genetics_questionnaire() throws Exception { assertEqual("servicerequest-genetics-questionnaire.json"); }
    @Test  void servicerequest_genetics_profile() throws Exception { assertEqual("servicerequest-genetics.profile.json"); }
    @Test  void servicerequest_questionnaire() throws Exception { assertEqual("servicerequest-questionnaire.json"); }
    @Test  void servicerequest_profile() throws Exception { assertEqual("servicerequest.profile.json"); }
    @Test  void shareableactivitydefinition_questionnaire() throws Exception { assertEqual("shareableactivitydefinition-questionnaire.json"); }
    @Test  void shareableactivitydefinition_profile() throws Exception { assertEqual("shareableactivitydefinition.profile.json"); }
    @Test  void shareablecodesystem_questionnaire() throws Exception { assertEqual("shareablecodesystem-questionnaire.json"); }
    @Test  void shareablecodesystem_profile() throws Exception { assertEqual("shareablecodesystem.profile.json"); }
    @Test  void shareablelibrary_questionnaire() throws Exception { assertEqual("shareablelibrary-questionnaire.json"); }
    @Test  void shareablelibrary_profile() throws Exception { assertEqual("shareablelibrary.profile.json"); }
    @Test  void shareablemeasure_questionnaire() throws Exception { assertEqual("shareablemeasure-questionnaire.json"); }
    @Test  void shareablemeasure_profile() throws Exception { assertEqual("shareablemeasure.profile.json"); }
    @Test  void shareableplandefinition_questionnaire() throws Exception { assertEqual("shareableplandefinition-questionnaire.json"); }
    @Test  void shareableplandefinition_profile() throws Exception { assertEqual("shareableplandefinition.profile.json"); }
    @Test  void shareablevalueset_questionnaire() throws Exception { assertEqual("shareablevalueset-questionnaire.json"); }
    @Test  void shareablevalueset_profile() throws Exception { assertEqual("shareablevalueset.profile.json"); }
    @Test @Tag("multi")  void signature_profile() throws Exception { assertEqual("signature.profile.json"); }
    @Test  void simplequantity_profile() throws Exception { assertEqual("simplequantity.profile.json"); }
    @Test  void slot_example_busy() throws Exception { assertEqual("slot-example-busy.json"); }
    @Test  void slot_example_tentative() throws Exception { assertEqual("slot-example-tentative.json"); }
    @Test  void slot_example_unavailable() throws Exception { assertEqual("slot-example-unavailable.json"); }
    @Test  void slot_example() throws Exception { assertEqual("slot-example.json"); }
    @Test  void slot_questionnaire() throws Exception { assertEqual("slot-questionnaire.json"); }
    @Test  void slot_profile() throws Exception { assertEqual("slot.profile.json"); }
    @Test  void specimen_example_isolate() throws Exception { assertEqual("specimen-example-isolate.json"); }
    @Test  void specimen_example_pooled_serum() throws Exception { assertEqual("specimen-example-pooled-serum.json"); }
    @Test  void specimen_example_serum() throws Exception { assertEqual("specimen-example-serum.json"); }
    @Test  void specimen_example_urine() throws Exception { assertEqual("specimen-example-urine.json"); }
    @Test  void specimen_example() throws Exception { assertEqual("specimen-example.json"); }
    @Test  void specimen_questionnaire() throws Exception { assertEqual("specimen-questionnaire.json"); }
    @Test  void specimen_profile() throws Exception { assertEqual("specimen.profile.json"); }
    @Test  void specimendefinition_example_serum_plasma() throws Exception { assertEqual("specimendefinition-example-serum-plasma.json"); }
    @Test  void specimendefinition_questionnaire() throws Exception { assertEqual("specimendefinition-questionnaire.json"); }
    @Test  void specimendefinition_profile() throws Exception { assertEqual("specimendefinition.profile.json"); }
    @Test  void string_profile() throws Exception { assertEqual("string.profile.json"); }
    @Test  void structuredefinition_example_composition() throws Exception { assertEqual("structuredefinition-example-composition.json"); }
    @Test @Tag("multi")  void structuredefinition_example_section_library() throws Exception { assertEqual("structuredefinition-example-section-library.json"); }
    @Test  void structuredefinition_questionnaire() throws Exception { assertEqual("structuredefinition-questionnaire.json"); }
    @Test  void structuredefinition_profile() throws Exception { assertEqual("structuredefinition.profile.json"); }
    @Test  void structuremap_example() throws Exception { assertEqual("structuremap-example.json"); }
    @Test  void structuremap_questionnaire() throws Exception { assertEqual("structuremap-questionnaire.json"); }
    @Test  void structuremap_supplyrequest_transform() throws Exception { assertEqual("structuremap-supplyrequest-transform.json"); }
    @Test  void structuremap_profile() throws Exception { assertEqual("structuremap.profile.json"); }
    @Test  void subscription_example_error() throws Exception { assertEqual("subscription-example-error.json"); }
    @Test  void subscription_example() throws Exception { assertEqual("subscription-example.json"); }
    @Test  void subscription_questionnaire() throws Exception { assertEqual("subscription-questionnaire.json"); }
    @Test  void subscription_profile() throws Exception { assertEqual("subscription.profile.json"); }
    @Test  void substance_example_amoxicillin_clavulanate() throws Exception { assertEqual("substance-example-amoxicillin-clavulanate.json"); }
    @Test  void substance_example_f201_dust() throws Exception { assertEqual("substance-example-f201-dust.json"); }
    @Test  void substance_example_f202_staphylococcus() throws Exception { assertEqual("substance-example-f202-staphylococcus.json"); }
    @Test  void substance_example_f203_potassium() throws Exception { assertEqual("substance-example-f203-potassium.json"); }
    @Test  void substance_example_silver_nitrate_product() throws Exception { assertEqual("substance-example-silver-nitrate-product.json"); }
    @Test  void substance_example() throws Exception { assertEqual("substance-example.json"); }
    @Test  void substance_questionnaire() throws Exception { assertEqual("substance-questionnaire.json"); }
    @Test  void substance_profile() throws Exception { assertEqual("substance.profile.json"); }
    @Test @Tag("multi")  void substanceamount_profile() throws Exception { assertEqual("substanceamount.profile.json"); }
    @Test  void substancenucleicacid_example() throws Exception { assertEqual("substancenucleicacid-example.json"); }
    @Test  void substancenucleicacid_questionnaire() throws Exception { assertEqual("substancenucleicacid-questionnaire.json"); }
    @Test @Tag("multi")  void substancenucleicacid_profile() throws Exception { assertEqual("substancenucleicacid.profile.json"); }
    @Test  void substancepolymer_example() throws Exception { assertEqual("substancepolymer-example.json"); }
    @Test  void substancepolymer_questionnaire() throws Exception { assertEqual("substancepolymer-questionnaire.json"); }
    @Test @Tag("multi")  void substancepolymer_profile() throws Exception { assertEqual("substancepolymer.profile.json"); }
    @Test  void substanceprotein_example() throws Exception { assertEqual("substanceprotein-example.json"); }
    @Test  void substanceprotein_questionnaire() throws Exception { assertEqual("substanceprotein-questionnaire.json"); }
    @Test @Tag("multi")  void substanceprotein_profile() throws Exception { assertEqual("substanceprotein.profile.json"); }
    @Test  void substancereferenceinformation_example() throws Exception { assertEqual("substancereferenceinformation-example.json"); }
    @Test  void substancereferenceinformation_questionnaire() throws Exception { assertEqual("substancereferenceinformation-questionnaire.json"); }
    @Test @Tag("multi")  void substancereferenceinformation_profile() throws Exception { assertEqual("substancereferenceinformation.profile.json"); }
    @Test  void substancesourcematerial_example() throws Exception { assertEqual("substancesourcematerial-example.json"); }
    @Test  void substancesourcematerial_questionnaire() throws Exception { assertEqual("substancesourcematerial-questionnaire.json"); }
    @Test @Tag("multi")  void substancesourcematerial_profile() throws Exception { assertEqual("substancesourcematerial.profile.json"); }
    @Test  void substancespecification_example() throws Exception { assertEqual("substancespecification-example.json"); }
    @Test  void substancespecification_questionnaire() throws Exception { assertEqual("substancespecification-questionnaire.json"); }
    @Test @Tag("multi")  void substancespecification_profile() throws Exception { assertEqual("substancespecification.profile.json"); }
    @Test  void supplydelivery_example_pumpdelivery() throws Exception { assertEqual("supplydelivery-example-pumpdelivery.json"); }
    @Test  void supplydelivery_example() throws Exception { assertEqual("supplydelivery-example.json"); }
    @Test  void supplydelivery_questionnaire() throws Exception { assertEqual("supplydelivery-questionnaire.json"); }
    @Test  void supplydelivery_profile() throws Exception { assertEqual("supplydelivery.profile.json"); }
    @Test  void supplyrequest_example_simpleorder() throws Exception { assertEqual("supplyrequest-example-simpleorder.json"); }
    @Test  void supplyrequest_questionnaire() throws Exception { assertEqual("supplyrequest-questionnaire.json"); }
    @Test  void supplyrequest_profile() throws Exception { assertEqual("supplyrequest.profile.json"); }
    @Test  void synthesis_questionnaire() throws Exception { assertEqual("synthesis-questionnaire.json"); }
    @Test  void synthesis_profile() throws Exception { assertEqual("synthesis.profile.json"); }
    @Test  void task_example_fm_cancel() throws Exception { assertEqual("task-example-fm-cancel.json"); }
    @Test  void task_example_fm_poll() throws Exception { assertEqual("task-example-fm-poll.json"); }
    @Test  void task_example_fm_release() throws Exception { assertEqual("task-example-fm-release.json"); }
    @Test  void task_example_fm_reprocess() throws Exception { assertEqual("task-example-fm-reprocess.json"); }
    @Test  void task_example_fm_status_resp() throws Exception { assertEqual("task-example-fm-status-resp.json"); }
    @Test  void task_example_fm_status() throws Exception { assertEqual("task-example-fm-status.json"); }
    @Test  void task_example1() throws Exception { assertEqual("task-example1.json"); }
    @Test  void task_example2() throws Exception { assertEqual("task-example2.json"); }
    @Test  void task_example3() throws Exception { assertEqual("task-example3.json"); }
    @Test  void task_example4() throws Exception { assertEqual("task-example4.json"); }
    @Test  void task_example5() throws Exception { assertEqual("task-example5.json"); }
    @Test  void task_example6() throws Exception { assertEqual("task-example6.json"); }
    @Test  void task_questionnaire() throws Exception { assertEqual("task-questionnaire.json"); }
    @Test  void task_profile() throws Exception { assertEqual("task.profile.json"); }
    @Test  void terminologycapabilities_example() throws Exception { assertEqual("terminologycapabilities-example.json"); }
    @Test  void terminologycapabilities_questionnaire() throws Exception { assertEqual("terminologycapabilities-questionnaire.json"); }
    @Test  void terminologycapabilities_profile() throws Exception { assertEqual("terminologycapabilities.profile.json"); }
    @Test @Tag("multi")  void testreport_example() throws Exception { assertEqual("testreport-example.json"); }
    @Test  void testreport_questionnaire() throws Exception { assertEqual("testreport-questionnaire.json"); }
    @Test  void testreport_profile() throws Exception { assertEqual("testreport.profile.json"); }
    @Test  void testscript_example_history() throws Exception { assertEqual("testscript-example-history.json"); }
    @Test  void testscript_example_multisystem() throws Exception { assertEqual("testscript-example-multisystem.json"); }
    @Test  void testscript_example_readtest() throws Exception { assertEqual("testscript-example-readtest.json"); }
    @Test  void testscript_example_search() throws Exception { assertEqual("testscript-example-search.json"); }
    @Test  void testscript_example_update() throws Exception { assertEqual("testscript-example-update.json"); }
    @Test  void testscript_example() throws Exception { assertEqual("testscript-example.json"); }
    @Test  void testscript_questionnaire() throws Exception { assertEqual("testscript-questionnaire.json"); }
    @Test  void testscript_profile() throws Exception { assertEqual("testscript.profile.json"); }
    @Test  void time_profile() throws Exception { assertEqual("time.profile.json"); }
    @Test @Tag("multi")  void timing_profile() throws Exception { assertEqual("timing.profile.json"); }
    @Test  void triggerdefinition_profile() throws Exception { assertEqual("triggerdefinition.profile.json"); }
    @Test  void triglyceride_questionnaire() throws Exception { assertEqual("triglyceride-questionnaire.json"); }
    @Test  void triglyceride_profile() throws Exception { assertEqual("triglyceride.profile.json"); }
    @Test  void unsignedint_profile() throws Exception { assertEqual("unsignedint.profile.json"); }
    @Test  void uri_profile() throws Exception { assertEqual("uri.profile.json"); }
    @Test  void url_profile() throws Exception { assertEqual("url.profile.json"); }
    @Test  void usagecontext_profile() throws Exception { assertEqual("usagecontext.profile.json"); }
    @Test  void uuid_profile() throws Exception { assertEqual("uuid.profile.json"); }
    @Test @Tag("bundle")  void v2_tables() throws Exception { assertEqual("v2-tables.json"); }
    @Test
    @Tag("bundle")  void v3_codesystems() throws Exception { assertEqual("v3-codesystems.json"); }
}
