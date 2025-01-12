package org.back.systemklinikimedycznej.patient.controllers.dto;

import org.back.systemklinikimedycznej.patient.domain.BloodType;

public record PatientDetailsDto(
        Long id,
        String weightInKg,
        String heightInCm,
        BloodType bloodType
) {
}
