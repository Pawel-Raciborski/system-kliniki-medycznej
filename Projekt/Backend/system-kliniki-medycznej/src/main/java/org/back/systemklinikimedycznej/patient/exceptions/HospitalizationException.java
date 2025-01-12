package org.back.systemklinikimedycznej.patient.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class HospitalizationException extends GlobalAppException {
    public HospitalizationException(String message, HttpStatus httpStatus) {
        super(message,httpStatus);
    }
}
