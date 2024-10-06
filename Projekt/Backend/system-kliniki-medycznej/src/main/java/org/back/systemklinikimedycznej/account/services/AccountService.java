package org.back.systemklinikimedycznej.account.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.account.dto.AccountDto;
import org.back.systemklinikimedycznej.account.exceptions.UserEmailAlreadyExistException;
import org.back.systemklinikimedycznej.account.exceptions.UsernameAlreadyExistException;
import org.back.systemklinikimedycznej.account.repositories.AccountRepository;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.account.util.AccountManagerUtil;
import org.back.systemklinikimedycznej.account.validators.AccountValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountValidator accountValidator;

    @Transactional
    public Account create(AccountDto accountDto) {
        accountValidator.validateEmailAndUsername(accountDto);

        Account accountToRegister = AccountManagerUtil.buildUserFromRegistrationForm(accountDto);

        return accountRepository.save(accountToRegister);
    }
}
