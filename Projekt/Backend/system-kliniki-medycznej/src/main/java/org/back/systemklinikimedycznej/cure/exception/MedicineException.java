package org.back.systemklinikimedycznej.cure.exception;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.HttpStatus;

public class MedicineException extends GlobalAppException {
    public MedicineException(String message, HttpStatus httpStatus) {
        super(message,httpStatus);
    }
}
