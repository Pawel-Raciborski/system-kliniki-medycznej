package org.back.systemklinikimedycznej.patient.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class PatientException extends GlobalAppException {
    public PatientException(String message, HttpStatus status) {
        super(message, status);
    }
}
