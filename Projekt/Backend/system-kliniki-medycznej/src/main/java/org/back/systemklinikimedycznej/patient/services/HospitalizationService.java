package org.back.systemklinikimedycznej.patient.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.model.Pagination;
import org.back.systemklinikimedycznej.patient.controllers.dto.HospitalizationInfo;
import org.back.systemklinikimedycznej.patient.mapper.HospitalizationMapper;
import org.back.systemklinikimedycznej.patient.repositories.HospitalizationRepository;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.Hospitalization;
import org.back.systemklinikimedycznej.patient.repositories.entities.patient_disease.PatientDisease;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientHospitalizationService {
    private final HospitalizationRepository hospitalizationRepository;

    public Hospitalization findCurrentHospitalization(PatientDisease patientDisease) {
        return hospitalizationRepository.findCurrentHospitalization(patientDisease);
    }
    public List<HospitalizationInfo> getPagedHospitalizationHistoryForPatientDisease(PatientDisease patientDisease, Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.page(),pagination.pageSize());

        return hospitalizationRepository.findHospitalizations(patientDisease,pageable).getContent().stream()
                .map(HospitalizationMapper.INSTANCE::mapFromEntity)
                .toList();
    }
}