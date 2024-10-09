package org.back.systemklinikimedycznej.receptionist.controller.dto;

import org.back.systemklinikimedycznej.account.dto.AccountDto;
import org.back.systemklinikimedycznej.personal_details.dto.PersonalDetailsDto;

import java.time.LocalDate;

public record RegisterReceptionistForm(
        AccountDto registerAccountData,
        PersonalDetailsDto personalDetails,
        LocalDate dateOfEmployment
) {
}
