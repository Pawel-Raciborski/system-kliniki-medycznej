package org.back.systemklinikimedycznej.user.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class UserEmailAlreadyUsed extends GlobalAppException {
    public UserEmailAlreadyUsed(String message, HttpStatus httpStatus) {
        super(message,httpStatus);
    }
}
