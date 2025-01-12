package org.back.systemklinikimedycznej.patient.util;

import org.back.systemklinikimedycznej.patient.controllers.dto.PatientDetailsDto;
import org.back.systemklinikimedycznej.patient.repositories.entities.PatientDetails;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;

public class PatientDetailsManagerUtil {
    public static PatientDetails buildPatientDetails(PatientCard patientCard, PatientDetailsDto collectedPatientData) {
        return PatientDetails.builder()
                .bloodType(collectedPatientData.bloodType())
                .heightInCm(collectedPatientData.heightInCm())
                .weightInKg(collectedPatientData.weightInKg())
                .patientCard(patientCard)
                .build();
    }

    public static void updatePatientDetails(PatientDetails patientDetailsToUpdate, PatientDetailsDto collectedPatientData) {
        patientDetailsToUpdate.setBloodType(collectedPatientData.bloodType());
        patientDetailsToUpdate.setHeightInCm(collectedPatientData.heightInCm());
        patientDetailsToUpdate.setWeightInKg(collectedPatientData.weightInKg());
    }
}
