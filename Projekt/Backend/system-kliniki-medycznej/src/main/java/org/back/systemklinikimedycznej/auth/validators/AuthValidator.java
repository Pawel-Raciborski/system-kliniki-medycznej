package org.back.systemklinikimedycznej.auth.validators;

import lombok.RequiredArgsConstructor;
import org.back.systemklinikimedycznej.auth.exceptions.InvalidPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthValidator {
    private final PasswordEncoder passwordEncoder;

    public void validatePasswordEquality(String password, String credentialsPassword) {
        if(!passwordEncoder.matches(credentialsPassword,password)){
            throw new InvalidPasswordException("Nieprawidłowe hasło!", HttpStatus.CONFLICT);
        }
    }
}
