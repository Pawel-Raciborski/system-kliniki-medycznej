package org.back.systemklinikimedycznej.patient.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.model.Pagination;
import org.back.systemklinikimedycznej.patient.domain.CureStatus;
import org.back.systemklinikimedycznej.patient.repositories.PatientDiseaseRepository;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.PatientDisease;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientDiseaseService {
    private final PatientDiseaseRepository patientDiseaseRepository;
    private final PatientHospitalizationService patientHospitalizationService;

    public Long countPatientDiseases(PatientCard patientCard){
        return patientDiseaseRepository.countPatientDiseases(patientCard);
    };


}
