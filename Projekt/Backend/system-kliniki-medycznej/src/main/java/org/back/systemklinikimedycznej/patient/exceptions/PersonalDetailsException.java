package org.back.systemklinikimedycznej.patient.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class PersonalDetailsException extends GlobalAppException {
    public PersonalDetailsException(String message, HttpStatus status) {
        super(message, status);
    }
}
