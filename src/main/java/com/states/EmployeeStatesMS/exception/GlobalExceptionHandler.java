package com.states.EmployeeStatesMS.exception;

import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidTypeIdException.class)
    public ResponseEntity<String> handleException(InvalidTypeIdException invalidTypeIdException) {
        return new ResponseEntity<String>("Invalid event trigger entered ! " + invalidTypeIdException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
