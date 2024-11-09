package org.back.systemklinikimedycznej.doctor.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class DoctorNotFoundException extends GlobalAppException {
    public DoctorNotFoundException(String message, HttpStatus httpStatus) {
        super(message,httpStatus);
    }
}
