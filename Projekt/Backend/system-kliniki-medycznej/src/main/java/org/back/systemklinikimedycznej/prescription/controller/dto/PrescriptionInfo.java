package org.back.systemklinikimedycznej.prescription.controller.dto;

import lombok.Builder;
import org.back.systemklinikimedycznej.doctor.controller.dto.DoctorInfo;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record PrescriptionInfo(
        UUID uuid,
        LocalDate expirationDate,
        LocalDate createdDate,
        DoctorInfo doctor,
        String description
) {
}
