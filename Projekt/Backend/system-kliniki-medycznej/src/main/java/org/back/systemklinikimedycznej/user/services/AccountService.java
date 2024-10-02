package org.back.systemklinikimedycznej.user.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.user.dto.RegisterAccountForm;
import org.back.systemklinikimedycznej.user.exceptions.UserEmailAlreadyUsed;
import org.back.systemklinikimedycznej.user.exceptions.UsernameAlreadyUsed;
import org.back.systemklinikimedycznej.user.repositories.AccountRepository;
import org.back.systemklinikimedycznej.user.repositories.entities.Account;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional
    public Account create(RegisterAccountForm registerAccountForm) {
        Optional<Account> foundUserOpt = accountRepository.findByEmail(registerAccountForm.email());

        if(foundUserOpt.isPresent()){
            throw new UserEmailAlreadyUsed("Niepoprawny email!", HttpStatus.CONFLICT);
        }

        foundUserOpt = accountRepository.findByUsername(registerAccountForm.username());

        if(foundUserOpt.isPresent()){
            throw new UsernameAlreadyUsed("Nazwa użytkownika już zajęta!", HttpStatus.CONFLICT);
        }

        Account accountToRegister = buildUserFromRegistrationForm(registerAccountForm);

        return accountRepository.save(accountToRegister);
    }

    private Account buildUserFromRegistrationForm(RegisterAccountForm registerAccountForm) {
        return Account.builder()
                .username(registerAccountForm.username())
                .password(registerAccountForm.password())
                .email(registerAccountForm.email())
                .dateTimeOfCreation(LocalDateTime.now())
                .build();
    }
}
