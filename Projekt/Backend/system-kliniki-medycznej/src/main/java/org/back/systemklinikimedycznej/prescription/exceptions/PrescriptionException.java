package org.back.systemklinikimedycznej.prescription.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class PrescriptionException extends GlobalAppException {
    public PrescriptionException(String message, HttpStatus status) {
        super(message, status);
    }
}
