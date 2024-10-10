package org.back.systemklinikimedycznej.role.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class RoleException extends GlobalAppException {
    public RoleException(String message, HttpStatus httpStatus) {
        super(message,httpStatus);
    }
}
