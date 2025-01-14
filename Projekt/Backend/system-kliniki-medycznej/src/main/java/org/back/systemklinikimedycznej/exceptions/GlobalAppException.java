package org.back.systemklinikimedycznej.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GlobalAppException extends RuntimeException {
    private final String message;
    private final HttpStatus status;

    public GlobalAppException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }
}
