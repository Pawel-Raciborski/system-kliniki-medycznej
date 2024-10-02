package org.back.systemklinikimedycznej.doctor.controller.dto;

import java.time.LocalDate;

public record DoctorSpecializationDto(
        String name,
        String description,
        LocalDate realizedDate
) {
}
