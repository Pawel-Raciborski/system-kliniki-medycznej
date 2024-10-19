package org.back.systemklinikimedycznej.account.validators;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.account.dto.AccountDto;
import org.back.systemklinikimedycznej.account.exceptions.AccountEmailAlreadyExistException;
import org.back.systemklinikimedycznej.account.exceptions.UsernameAlreadyExistException;
import org.back.systemklinikimedycznej.account.repositories.AccountRepository;
import org.back.systemklinikimedycznej.account.repositories.entities.Account;
import org.back.systemklinikimedycznej.auth.exceptions.InvalidPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountValidator {
    private final AccountRepository accountRepository;

    @Transactional
    public void validateEmailAndUsername(String email, String username) {
        validateEmail(email);
        validateUsername(username);
    }

    private void validateUsername(String username) {
        Optional<Account> foundUserOpt = accountRepository.findByUsername(username);
        checkUsernameNotExist(foundUserOpt.isPresent());
    }

    private void validateEmail(String email) {
        Optional<Account> foundUserOpt = accountRepository.findByEmail(email);
        checkEmailNotExist(foundUserOpt.isPresent());
    }

    private void checkEmailNotExist(boolean emailExist) {
        if(emailExist){
            throw new AccountEmailAlreadyExistException("Email zajęty!", HttpStatus.CONFLICT);
        }
    }

    private void checkUsernameNotExist(boolean userExist) {
        if(userExist){
            throw new UsernameAlreadyExistException("Nazwa użytkownika już zajęta!", HttpStatus.CONFLICT);
        }
    }

    public void validatePasswordEqual(String currentPassword, String passedPassword) {
        if(!currentPassword.equals(passedPassword)){
            throw new InvalidPasswordException("Nieprawidłowe hasło!",HttpStatus.CONFLICT);
        }
    }
}
