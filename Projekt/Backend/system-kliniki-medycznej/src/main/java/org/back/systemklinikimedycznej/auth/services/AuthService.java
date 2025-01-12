package org.back.systemklinikimedycznej.auth.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.account.services.AccountService;
import org.back.systemklinikimedycznej.auth.controller.dto.Credentials;
import org.back.systemklinikimedycznej.auth.domain.ApplicationUser;
import org.back.systemklinikimedycznej.auth.util.AuthManagerUtil;
import org.back.systemklinikimedycznej.auth.validators.AuthValidator;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final AccountService accountService;
    private final AuthValidator authValidator;
    @Transactional
    public ApplicationUser processLogin(Credentials credentials){
        String login = credentials.login();

        ApplicationUser account = findAccount(login);
        authValidator.validatePasswordEquality(account.getPassword(),credentials.password());
        return account;

    }

    private ApplicationUser findAccount(String login) {
        if(login.matches(AuthManagerUtil.EMAIL_REGEX)){
            return loadUserByEmail(login);
        }
        return loadUserByUsername(login);
    }

    private ApplicationUser loadUserByEmail(String login) {
        Account account = accountService.findByEmail(login);
        return new ApplicationUser(account);
    }

    @Override
    public ApplicationUser loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findByUsername(username);
        return new ApplicationUser(account);
    }
}
