package org.back.systemklinikimedycznej.role.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class PermissionException extends GlobalAppException {
    public PermissionException(String message, HttpStatus status) {
        super(message, status);
    }
}
