package org.back.systemklinikimedycznej.patient.controllers.dto;

import org.back.systemklinikimedycznej.personal_details.dto.PersonalDetailsDto;

public record PatientToRegisterData(

        String parentPesel,
        PersonalDetailsDto personalDetails
) {
}
