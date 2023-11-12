package com.example.zenikameetingplanner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class businessExp extends Exception {
    @ExceptionHandler(InputExp.class)
    public ResponseEntity<Object> handleBusinessException(InputExp exception){
        return ResponseEntity.status (HttpStatus.BAD_REQUEST).body (exception.getMessage ());
    }
}
