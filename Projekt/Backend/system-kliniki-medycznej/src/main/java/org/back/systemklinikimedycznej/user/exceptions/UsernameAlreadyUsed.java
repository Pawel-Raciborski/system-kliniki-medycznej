package org.back.systemklinikimedycznej.user.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class UsernameAlreadyUsed extends GlobalAppException {
    public UsernameAlreadyUsed(String message, HttpStatus httpStatus) {
        super(message,httpStatus);
    }
}
