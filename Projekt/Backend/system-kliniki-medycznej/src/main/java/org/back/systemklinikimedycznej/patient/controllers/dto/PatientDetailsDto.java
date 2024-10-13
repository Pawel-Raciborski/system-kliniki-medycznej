package org.back.systemklinikimedycznej.patient.controllers.dto;

public record PatientDetailsDto(
        Long id,
        String weightInKg,
        String heightInCm,
        String bloodType
) {
}
