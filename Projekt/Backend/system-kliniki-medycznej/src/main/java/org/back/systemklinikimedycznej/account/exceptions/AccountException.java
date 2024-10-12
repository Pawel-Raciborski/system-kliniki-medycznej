package org.back.systemklinikimedycznej.account.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class AccountException extends GlobalAppException {
    public AccountException(String message, HttpStatus httpStatus) {
        super(message,httpStatus);
    }
}
