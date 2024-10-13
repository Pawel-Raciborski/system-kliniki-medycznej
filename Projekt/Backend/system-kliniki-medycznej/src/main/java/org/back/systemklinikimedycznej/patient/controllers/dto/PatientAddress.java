package org.back.systemklinikimedycznej.patient.controllers.dto;

public record PatientAddress(
        String street,
        String apartmentNumber,
        String postalCode,
        String city
) {
}
