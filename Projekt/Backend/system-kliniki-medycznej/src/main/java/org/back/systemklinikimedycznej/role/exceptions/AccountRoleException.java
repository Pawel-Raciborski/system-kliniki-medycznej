package org.back.systemklinikimedycznej.role.exceptions;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class AccountRoleException extends GlobalAppException {
    public AccountRoleException(String message, HttpStatus httpStatus) {
        super(message,httpStatus);
    }
}
