package org.back.systemklinikimedycznej.doctor.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class DoctorSpecializationExistException extends GlobalAppException {
    public DoctorSpecializationExistException(String message, HttpStatus httpStatus) {
        super(message,httpStatus);
    }
}
