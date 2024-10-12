package org.back.systemklinikimedycznej.patient.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class PatientCardException extends GlobalAppException {
    public PatientCardException(String message, HttpStatus httpStatus) {
        super(message,httpStatus);
    }
}
