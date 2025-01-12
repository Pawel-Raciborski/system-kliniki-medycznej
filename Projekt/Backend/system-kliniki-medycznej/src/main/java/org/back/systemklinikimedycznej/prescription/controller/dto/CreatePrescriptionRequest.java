package org.back.systemklinikimedycznej.prescription.controller.dto;

import java.time.LocalDate;
import java.util.List;

public record CreatePrescriptionRequest(
        Long doctorId,
        String patientPesel,
        List<PrescriptionMedicineDto> prescriptionMedicineList,
        LocalDate expirationDate,
        String description
) {
}
