package org.back.systemklinikimedycznej.address.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class CityNotFoundException extends GlobalAppException {
    public CityNotFoundException(String message, HttpStatus httpStatus) {
        super(message,httpStatus);
    }
}
