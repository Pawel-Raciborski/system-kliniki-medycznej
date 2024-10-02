package org.back.systemklinikimedycznej.address.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class AddressAlreadyExistsException extends GlobalAppException {
    public AddressAlreadyExistsException(String message, HttpStatus httpStatus) {
        super(message,httpStatus);
    }
}
