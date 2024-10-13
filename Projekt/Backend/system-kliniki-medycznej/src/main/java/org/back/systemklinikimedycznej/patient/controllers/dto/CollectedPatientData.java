package org.back.systemklinikimedycznej.patient.controllers.dto;

import org.back.systemklinikimedycznej.patient.domain.BloodType;

public record CollectedPatientData(
        String patientPesel,
        String weightInKg,
        String heightInCm,
        String bloodType
) {
    public BloodType getEnumBloodType(){
        return BloodType.valueOf(bloodType.toUpperCase());
    }
}
