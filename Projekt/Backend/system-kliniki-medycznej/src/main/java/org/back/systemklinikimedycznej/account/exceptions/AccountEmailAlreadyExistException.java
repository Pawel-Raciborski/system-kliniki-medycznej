package org.back.systemklinikimedycznej.account.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class AccountEmailAlreadyExistException extends GlobalAppException {
    public AccountEmailAlreadyExistException(String message, HttpStatus httpStatus) {
        super(message,httpStatus);
    }
}
