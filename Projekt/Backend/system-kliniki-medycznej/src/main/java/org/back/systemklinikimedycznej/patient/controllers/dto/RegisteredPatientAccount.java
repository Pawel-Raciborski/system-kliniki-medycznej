package org.back.systemklinikimedycznej.patient.controllers.dto;

import lombok.Builder;
import org.back.systemklinikimedycznej.account.dto.AccountDto;
import org.back.systemklinikimedycznej.personal_details.dto.PersonalDetailsDto;

@Builder
public record RegisteredPatientAccount(
        AccountDto accountDto,
        PersonalDetailsDto personalDetailsDto
) {
}
