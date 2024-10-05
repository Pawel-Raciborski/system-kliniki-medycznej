package org.back.systemklinikimedycznej.account.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class UsernameAlreadyExistException extends GlobalAppException {
    public UsernameAlreadyExistException(String message, HttpStatus httpStatus) {
        super(message,httpStatus);
    }
}
