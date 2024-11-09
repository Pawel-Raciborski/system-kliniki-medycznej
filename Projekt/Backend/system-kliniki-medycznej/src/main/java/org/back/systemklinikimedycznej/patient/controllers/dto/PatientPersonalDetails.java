package org.back.systemklinikimedycznej.patient.controllers.dto;

import java.time.LocalDate;

public record PatientPersonalDetails(
        String pesel,
        String name,
        String surname,
        LocalDate birthDate,
        String gender,
        String phoneNumber,
        PatientAddress address
) {
}
