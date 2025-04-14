package com.everhealth.integration.zbfspublisher.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<?> handleInvalidRequestException(Exception exception) {
        return new ResponseEntity<>(
                new ErrorResponse(Instant.now().toString(),
                        exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception) {
        return new ResponseEntity<>(
                new ErrorResponse(Instant.now().toString(),
                        exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
