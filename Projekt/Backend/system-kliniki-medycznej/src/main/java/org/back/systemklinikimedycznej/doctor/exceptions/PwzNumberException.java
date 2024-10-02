package org.back.systemklinikimedycznej.doctor.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class PwzNumberException extends GlobalAppException {
    public PwzNumberException(String message, HttpStatus httpStatus) {
        super(message,httpStatus);
    }
}
