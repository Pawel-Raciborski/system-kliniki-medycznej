package org.back.systemklinikimedycznej.role.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class RolePermissionException extends GlobalAppException {
    public RolePermissionException(String message, HttpStatus httpStatus) {
        super(message,httpStatus);
    }
}
