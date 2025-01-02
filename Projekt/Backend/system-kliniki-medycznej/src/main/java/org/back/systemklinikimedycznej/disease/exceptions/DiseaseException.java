package org.back.systemklinikimedycznej.disease.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class DiseaseException extends GlobalAppException {
    public DiseaseException(String message, HttpStatus status) {
        super(message, status);
    }
}
