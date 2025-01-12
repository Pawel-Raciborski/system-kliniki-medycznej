package org.back.systemklinikimedycznej.patient.controllers.dto;

import org.back.systemklinikimedycznej.cure.dto.MedicineDto;

import java.time.LocalDate;

public record CreateHospitalizationRequest(
        Long patientDiseaseId,
        MedicineDto medicine,
        String dosage,
        String notes,
        LocalDate finishDate
) {
}
