package org.back.systemklinikimedycznej.auth.util;

import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.auth.domain.LoginData;
import org.back.systemklinikimedycznej.role.repository.entities.Role;

import java.util.List;
import java.util.UUID;

public class AuthManagerUtil {
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";


    public static LoginData buildLoginData(Account account, List<Role> roles) {
        return LoginData.builder()
                .sessionId(UUID.randomUUID())
                .username(account.getUsername())
                .email(account.getEmail())
                .roles(roles)
                .build();
    }
}
