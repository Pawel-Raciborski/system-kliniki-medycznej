package org.back.systemklinikimedycznej.patient.controllers.dto;

import java.time.LocalDate;

public record PatientPersonalDetails(
        String name,
        String surname,
        String phoneNumber,
        String pesel,
        String gender,
        LocalDate birthDate,
        PatientAddress address
) {
}
