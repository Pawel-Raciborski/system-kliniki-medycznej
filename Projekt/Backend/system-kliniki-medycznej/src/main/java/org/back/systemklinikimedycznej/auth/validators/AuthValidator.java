package org.back.systemklinikimedycznej.auth.validators;

import org.back.systemklinikimedycznej.auth.exceptions.InvalidPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AuthValidator {


    public void validatePasswordEquality(String password, String credentialsPassword) {
        if(!password.equals(credentialsPassword)){
            throw new InvalidPasswordException("Nieprawidłowe hasło!", HttpStatus.CONFLICT);
        }
    }
}
