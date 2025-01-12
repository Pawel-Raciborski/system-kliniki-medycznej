package org.back.systemklinikimedycznej.patient.controllers.dto;

import org.back.systemklinikimedycznej.account.dto.AccountDto;

public record PatientToRegisterCredentials(
        String pesel,
        AccountDto accountCredentials
) {
}
