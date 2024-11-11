package org.back.systemklinikimedycznej.auth.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.account.services.AccountService;
import org.back.systemklinikimedycznej.auth.controller.dto.Credentials;
import org.back.systemklinikimedycznej.auth.domain.LoginData;
import org.back.systemklinikimedycznej.auth.util.AuthManagerUtil;
import org.back.systemklinikimedycznej.auth.validators.AuthValidator;
import org.back.systemklinikimedycznej.role.repository.entities.Role;
import org.back.systemklinikimedycznej.role.services.AccountRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AccountService accountService;
    private final AuthValidator authValidator;
    private final AccountRoleService accountRoleService;
    @Transactional
    public LoginData processLogin(Credentials credentials){
        String login = credentials.login();

        Account account = findAccount(login);

        authValidator.validatePasswordEquality(account.getPassword(),credentials.password());

        List<Role> roles = accountRoleService.findAllAccountRoles(account);

        return AuthManagerUtil.buildLoginData(account, roles);

    }


    private Account findAccount(String login) {
        if(login.matches(AuthManagerUtil.EMAIL_REGEX)){
            return accountService.findByEmail(login);
        }
        return accountService.findByUsername(login);
    }
}
