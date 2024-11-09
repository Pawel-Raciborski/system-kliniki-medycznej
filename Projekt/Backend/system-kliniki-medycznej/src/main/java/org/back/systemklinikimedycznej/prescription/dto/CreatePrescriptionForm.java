package org.back.systemklinikimedycznej.prescription.dto;

import org.back.systemklinikimedycznej.prescription.repositories.entities.PrescriptionMedicine;

import java.time.LocalDate;
import java.util.List;

public record CreatePrescriptionForm(
        Long doctorId,
        Long patientId,
        List<PrescriptionMedicineDto> prescriptionMedicineList,
        LocalDate expirationDate,
        String description
) {
}
