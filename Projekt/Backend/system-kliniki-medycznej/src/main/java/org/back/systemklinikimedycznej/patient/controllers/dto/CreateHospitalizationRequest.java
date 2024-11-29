package org.back.systemklinikimedycznej.patient.controllers.dto;

import org.back.systemklinikimedycznej.cure.dto.MedicineDto;

public record CreateHospitalizationRequest(
        MedicineDto medicine,
        String dosage,
        String notes
) {
}
