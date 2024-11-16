package org.back.systemklinikimedycznej.patient.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.patient.repositories.PatientHospitalizationRepository;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.Hospitalization;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.PatientDisease;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientHospitalizationService {
    private final PatientHospitalizationRepository patientHospitalizationRepository;

    public Hospitalization findCurrentHospitalization(PatientDisease patientDisease) {
        return patientHospitalizationRepository.findCurrentHospitalization(patientDisease);
    }
}
