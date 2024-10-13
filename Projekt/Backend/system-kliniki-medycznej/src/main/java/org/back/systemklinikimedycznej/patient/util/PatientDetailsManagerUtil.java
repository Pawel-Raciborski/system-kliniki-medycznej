package org.back.systemklinikimedycznej.patient.util;

import org.back.systemklinikimedycznej.patient.controllers.dto.CollectedPatientData;
import org.back.systemklinikimedycznej.patient.repositories.entities.PatientDetails;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;

public class PatientDetailsManagerUtil {
    public static PatientDetails buildPatientDetails(PatientCard patientCard, CollectedPatientData collectedPatientData) {
        return PatientDetails.builder()
                .bloodType(collectedPatientData.getEnumBloodType())
                .heightInCm(collectedPatientData.heightInCm())
                .weightInKg(collectedPatientData.weightInKg())
                .patientCard(patientCard)
                .build();
    }
}
