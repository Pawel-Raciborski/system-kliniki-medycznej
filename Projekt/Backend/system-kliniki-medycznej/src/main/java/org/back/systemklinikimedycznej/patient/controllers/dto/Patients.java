package org.back.systemklinikimedycznej.patient.controllers.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record Patients(
        List<PatientData> patients
) {
}
