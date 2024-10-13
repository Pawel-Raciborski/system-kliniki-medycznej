package org.back.systemklinikimedycznej.patient.controllers.dto;

public record PatientData(
        Long id,
        PatientPersonalDetails personalDetails,
        String parentPesel
) {
}
