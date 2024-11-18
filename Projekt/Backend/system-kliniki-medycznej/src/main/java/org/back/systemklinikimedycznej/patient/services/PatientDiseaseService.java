package org.back.systemklinikimedycznej.patient.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.doctor.util.DoctorManagerUtil;
import org.back.systemklinikimedycznej.model.Pagination;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientDiseaseHospitalizationInfo;
import org.back.systemklinikimedycznej.patient.domain.CureStatus;
import org.back.systemklinikimedycznej.patient.exceptions.PatientDiseaseException;
import org.back.systemklinikimedycznej.patient.repositories.PatientDiseaseRepository;
import org.back.systemklinikimedycznej.patient.repositories.entities.Patient;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_card.PatientCard;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.Hospitalization;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.PatientDisease;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
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

    public List<PatientDiseaseHospitalizationInfo> getPagedPatientDiseasesHospitalizationInfo(Patient patient, String cureStatus , Pagination pagination) {
        PatientCard patientCard = patient.getPatientCard();
        List<CureStatus> cureStatusList = CureStatus.findAllByStatus(cureStatus);
        PageRequest pageRequest = PageRequest.of(pagination.page(), pagination.pageSize());

        List<PatientDisease> patientDiseases = patientDiseaseRepository.findByPatientCardAndWithCureStatusPaged(patientCard,cureStatusList,pageRequest).getContent();
        return patientDiseases.stream().map(this::createHospitalizationInfo).toList();
    }

    private PatientDiseaseHospitalizationInfo createHospitalizationInfo(PatientDisease patientDisease) {
        Hospitalization currentHospitalization = patientHospitalizationService.findCurrentHospitalization(patientDisease);
        String fullName = DoctorManagerUtil.createDoctorFullName(patientDisease.getDetectedDoctor());

        return PatientDiseaseHospitalizationInfo.buildPatientDiseaseHospitalization(patientDisease,currentHospitalization,fullName,patientDisease.getDisease());
    }

    public PatientDisease findById(Long id) {
        return patientDiseaseRepository.findById(id).orElseThrow(() -> new PatientDiseaseException(
                "Nie znaleziono choroby pacjenta o podanym identyfikatorze",
                HttpStatus.NOT_FOUND
        ));
    }
}
