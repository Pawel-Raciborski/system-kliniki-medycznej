package org.back.systemklinikimedycznej.account.services;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.account.dto.AccountDto;
import org.back.systemklinikimedycznej.account.exceptions.UserEmailAlreadyExistException;
import org.back.systemklinikimedycznej.account.exceptions.UsernameAlreadyExistException;
import org.back.systemklinikimedycznej.account.repositories.AccountRepository;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
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
    public Account create(AccountDto accountDto) {
        Optional<Account> foundUserOpt = accountRepository.findByEmail(accountDto.email());

        if(foundUserOpt.isPresent()){
            throw new UserEmailAlreadyExistException("Niepoprawny email!", HttpStatus.CONFLICT);
        }

        foundUserOpt = accountRepository.findByUsername(accountDto.username());

        if(foundUserOpt.isPresent()){
            throw new UsernameAlreadyExistException("Nazwa użytkownika już zajęta!", HttpStatus.CONFLICT);
        }

        Account accountToRegister = buildUserFromRegistrationForm(accountDto);

        return accountRepository.save(accountToRegister);
    }

    private Account buildUserFromRegistrationForm(AccountDto accountDto) {
        return Account.builder()
                .username(accountDto.username())
                .password(accountDto.password())
                .email(accountDto.email())
                .dateTimeOfCreation(LocalDateTime.now())
                .build();
    }
}