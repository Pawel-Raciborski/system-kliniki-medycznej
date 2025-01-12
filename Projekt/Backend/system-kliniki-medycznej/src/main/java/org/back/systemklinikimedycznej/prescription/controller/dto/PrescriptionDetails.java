package org.back.systemklinikimedycznej.prescription.controller.dto;

import lombok.Builder;
import org.back.systemklinikimedycznej.cure.dto.PrescriptionMedicineInfo;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorInfo;
import org.back.systemklinikimedycznej.patient.controllers.dto.PatientData;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record PrescriptionDetails(
        UUID id,
        DoctorInfo doctorInfo,
        String description,
        LocalDate createdAt,
        LocalDate expirationDate,
        PatientData patient,
        List<PrescriptionMedicineInfo> prescriptionMedicineInfoList

) {
}
