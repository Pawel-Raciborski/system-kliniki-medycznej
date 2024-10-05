package org.back.systemklinikimedycznej.account.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class UserEmailAlreadyExistException extends GlobalAppException {
    public UserEmailAlreadyExistException(String message, HttpStatus httpStatus) {
        super(message,httpStatus);
    }
}
