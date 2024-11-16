package org.back.systemklinikimedycznej.patient.controllers.dto;

import org.back.systemklinikimedycznej.cure.dto.MedicineDto;

import java.time.LocalDate;

public record HospitalizationInfo(
        Long id,
        MedicineDto medicine,
        String cureDosage,
        String notes,
        LocalDate medicineUpdateDate
) {
}
