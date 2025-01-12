package org.back.systemklinikimedycznej.account.util;

import lombok.experimental.UtilityClass;
import org.back.systemklinikimedycznej.account.dto.AccountDto;
import org.back.systemklinikimedycznej.account.dto.AccountInfo;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;

import java.time.LocalDateTime;

@UtilityClass
public class AccountManagerUtil {

    public Account buildUserFromRegistrationForm(AccountDto accountDto) {
        return Account.builder()
                .username(accountDto.username())
                .email(accountDto.email())
                .dateTimeOfCreation(LocalDateTime.now())
                .build();
    }

    public static void updatePassword(Account accountToChangePassword, String newPassword) {
        accountToChangePassword.setPassword(newPassword);
    }

    public static void updateAccountDetails(Account account, String newUsername, String newEmail) {
        account.setUsername(newUsername);
        account.setEmail(newEmail);

    }
}
