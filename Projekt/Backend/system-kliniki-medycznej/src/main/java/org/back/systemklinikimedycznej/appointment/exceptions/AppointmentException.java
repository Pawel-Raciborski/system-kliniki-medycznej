package org.back.systemklinikimedycznej.appointment.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class AppointmentException extends GlobalAppException {
    public AppointmentException(String message, HttpStatus status) {
        super(message, status);
    }
}
