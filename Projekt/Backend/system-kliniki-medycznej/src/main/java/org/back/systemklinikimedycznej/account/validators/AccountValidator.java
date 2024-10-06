package org.back.systemklinikimedycznej.account.validators;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.account.dto.AccountDto;
import org.back.systemklinikimedycznej.account.exceptions.UserEmailAlreadyExistException;
import org.back.systemklinikimedycznej.account.exceptions.UsernameAlreadyExistException;
import org.back.systemklinikimedycznej.account.repositories.AccountRepository;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountValidator {
    private final AccountRepository accountRepository;

    @Transactional
    public void validateEmailAndUsername(AccountDto accountDto) {
        validateEmail(accountDto);
        validateUsername(accountDto);
    }

    private void validateUsername(AccountDto accountDto) {
        Optional<Account> foundUserOpt = accountRepository.findByUsername(accountDto.username());
        checkUsernameNotExist(foundUserOpt.isPresent());
    }

    private void validateEmail(AccountDto accountDto) {
        Optional<Account> foundUserOpt = accountRepository.findByEmail(accountDto.email());
        checkEmailNotExist(foundUserOpt.isPresent());
    }

    private void checkEmailNotExist(boolean emailExist) {
        if(emailExist){
            throw new UserEmailAlreadyExistException("Email zajęty!", HttpStatus.CONFLICT);
        }
    }

    private void checkUsernameNotExist(boolean userExist) {
        if(userExist){
            throw new UsernameAlreadyExistException("Nazwa użytkownika już zajęta!", HttpStatus.CONFLICT);
        }
    }
}
