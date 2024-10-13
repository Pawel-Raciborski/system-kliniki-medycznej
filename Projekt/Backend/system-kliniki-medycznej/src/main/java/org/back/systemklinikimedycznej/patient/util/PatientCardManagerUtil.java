package org.back.systemklinikimedycznej.patient.util;

import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;

import java.nio.file.Path;
import java.time.LocalDateTime;

public class PatientCardManagerUtil {
    public static PatientCard buildPatientCard(Patient patient, Path patientFiles) {
        return PatientCard.builder()
                .patient(patient)
                .createdDateTime(LocalDateTime.now())
                .patientDataFilesPath(patientFiles.toString())
                .build();
    }
}
