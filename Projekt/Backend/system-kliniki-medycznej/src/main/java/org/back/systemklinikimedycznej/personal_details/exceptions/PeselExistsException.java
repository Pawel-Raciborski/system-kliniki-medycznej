package org.back.systemklinikimedycznej.personal_details.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class PeselExistsException extends GlobalAppException {
    public PeselExistsException(String message, HttpStatus httpStatus) {
        super(message,httpStatus);

    }
}
