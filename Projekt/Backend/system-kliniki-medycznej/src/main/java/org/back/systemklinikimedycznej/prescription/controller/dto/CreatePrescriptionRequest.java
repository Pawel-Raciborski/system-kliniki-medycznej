package org.back.systemklinikimedycznej.prescription.controller.dto;

import org.back.systemklinikimedycznej.cure.dto.BasicMedicineInfo;
import org.back.systemklinikimedycznej.cure.dto.MedicineDto;

import java.time.LocalDate;
import java.util.List;

public record CreatePrescriptionRequest(
        Long doctorId,
        Long patientId,
        List<BasicMedicineInfo> prescriptionMedicineList,
        LocalDate expirationDate,
        String description
) {
}
