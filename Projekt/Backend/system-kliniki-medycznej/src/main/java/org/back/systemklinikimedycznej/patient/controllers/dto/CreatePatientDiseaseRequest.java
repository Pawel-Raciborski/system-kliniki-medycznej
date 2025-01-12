package org.back.systemklinikimedycznej.patient.controllers.dto;

import org.back.systemklinikimedycznej.disease.dto.DiseaseDto;

import java.util.List;
import java.util.UUID;

public record CreatePatientDiseaseRequest(
        UUID patientCardId,
        DiseaseDto diseaseDto,
        Long doctorId,
        String description,
        List<CreateHospitalizationRequest> hospitalizations
) {
}
