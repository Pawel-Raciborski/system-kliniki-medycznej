package org.back.systemklinikimedycznej.patient.util;

import org.back.systemklinikimedycznej.disease.repository.entities.Disease;
import org.back.systemklinikimedycznej.doctor.repositories.entities.Doctor;
import org.back.systemklinikimedycznej.patient.domain.CureStatus;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.PatientDisease;

import java.time.LocalDate;

public class PatientDiseaseManagerUtil {
    public static PatientDisease buildPatientDisease(PatientCard patientCard, Disease disease, Doctor doctor, String description) {
        return PatientDisease.builder()
                .patientCard(patientCard)
                .disease(disease)
                .detectedDoctor(doctor)
                .description(description)
                .detectionDate(LocalDate.now())
                .cureStatus(CureStatus.CURING)
                .build();
    }
}
