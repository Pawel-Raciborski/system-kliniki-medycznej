package org.back.systemklinikimedycznej.patient.controllers.dto;

import lombok.Builder;
import org.back.systemklinikimedycznej.disease.dto.BasicDiseaseInfo;
import org.back.systemklinikimedycznej.disease.mapper.DiseaseMapper;
import org.back.systemklinikimedycznej.disease.repository.entities.Disease;
import org.back.systemklinikimedycznej.patient.mapper.HospitalizationMapper;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.Hospitalization;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.PatientDisease;

import java.time.LocalDate;

@Builder
public record PatientDiseaseHospitalizationInfo(
        Long diseaseId,
        String description,
        String doctorFullName,
        LocalDate detectionDate,
        String cureStatus,
        LocalDate finishCureDate,
        HospitalizationInfo currentHospitalization,
        BasicDiseaseInfo diseaseInfo
        ) {
        public static PatientDiseaseHospitalizationInfo buildPatientDiseaseHospitalization(PatientDisease patientDisease, Hospitalization currentHospitalization, String fullName, Disease disease) {
                return builder()
                        .diseaseId(patientDisease.getId())
                        .description(patientDisease.getDescription())
                        .doctorFullName(fullName)
                        .detectionDate(patientDisease.getDetectionDate())
                        .cureStatus(patientDisease.getCureStatus().getValue())
                        .finishCureDate(patientDisease.getFinishCureDate())
                        .currentHospitalization(HospitalizationMapper.INSTANCE.mapFromEntity(currentHospitalization))
                        .diseaseInfo(DiseaseMapper.INSTANCE.mapToBasicDiseaseInfo(disease))
                        .build();
        }
}
