package org.back.systemklinikimedycznej.account.util;

import lombok.experimental.UtilityClass;
import org.back.systemklinikimedycznej.account.dto.AccountDto;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;

import java.time.LocalDateTime;

@UtilityClass
public class AccountManagerUtil {

    public Account buildUserFromRegistrationForm(AccountDto accountDto) {
        return Account.builder()
                .username(accountDto.username())
                .password(accountDto.password())
                .email(accountDto.email())
                .dateTimeOfCreation(LocalDateTime.now())
                .build();
    }
}
