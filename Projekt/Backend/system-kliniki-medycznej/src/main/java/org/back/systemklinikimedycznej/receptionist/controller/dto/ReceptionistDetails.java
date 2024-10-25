package org.back.systemklinikimedycznej.receptionist.controller.dto;

import org.back.systemklinikimedycznej.personal_details.dto.PersonalDetailsDto;

import java.time.LocalDate;

public record ReceptionistDetails(
        String username,
        String email,
        PersonalDetailsDto personalDetails,
        LocalDate dateOfEmployment
) {
}
