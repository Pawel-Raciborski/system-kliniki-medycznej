package org.back.systemklinikimedycznej.patient.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class PatientDiseaseException extends GlobalAppException {
    public PatientDiseaseException(String message, HttpStatus status) {
        super(message, status);
    }
}
