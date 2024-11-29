package org.back.systemklinikimedycznej.prescription.dto;

import java.time.LocalDate;
import java.util.List;

public record CreatePrescriptionRequest(
        Long doctorId,
        Long patientId,
        List<PrescriptionMedicineDto> prescriptionMedicineList,
        LocalDate expirationDate,
        String description
) {
}
