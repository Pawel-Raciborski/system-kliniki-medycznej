package org.back.systemklinikimedycznej.receptionist.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class ReceptionistNotFoundException extends GlobalAppException {
    public ReceptionistNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}
