package org.back.systemklinikimedycznej.doctor.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class DoctorNotExistException extends GlobalAppException {
    public DoctorNotExistException(String message, HttpStatus httpStatus) {
        super(message,httpStatus);
    }
}
