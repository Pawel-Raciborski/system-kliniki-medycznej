package org.back.systemklinikimedycznej.patient.controllers.dto;

public record RegisteredPatientData(
        PatientPersonalDetails personalDetails,
        String parentPesel
) {
}
