package org.back.systemklinikimedycznej.doctor.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class DoctorSpecializationNotFoundException extends GlobalAppException {
    public DoctorSpecializationNotFoundException(String message, HttpStatus httpStatus) {
        super(message,httpStatus);
    }
}
