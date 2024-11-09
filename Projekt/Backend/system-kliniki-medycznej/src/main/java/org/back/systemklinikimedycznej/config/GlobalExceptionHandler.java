package org.back.systemklinikimedycznej.config;

import org.back.systemklinikimedycznej.exceptions.GlobalAppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalAppException.class)
    public ResponseEntity<String> throwException(GlobalAppException e){
        return ResponseEntity
                .status(e.getStatus())
                .body(e.getMessage());
    }
}
