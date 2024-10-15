package org.back.systemklinikimedycznej.auth.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends GlobalAppException {
    public InvalidPasswordException(String message, HttpStatus httpStatus) {
        super(message,httpStatus);
    }
}
