package com.example.todoo.exceptionHandlers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(value = AppNotFoundExceptionHandler.class)
    public ResponseEntity<Object> exception(AppNotFoundExceptionHandler exception) {
        return new ResponseEntity<>(new Exception("not found", HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }
}
