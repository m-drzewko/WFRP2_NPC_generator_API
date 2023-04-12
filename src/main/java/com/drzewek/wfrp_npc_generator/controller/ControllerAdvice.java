package com.drzewek.wfrp_npc_generator.controller;

import com.drzewek.wfrp_npc_generator.model.response.ErrorResponseObject;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponseObject entityNotFoundException(Exception exception) {
        return new ErrorResponseObject(HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ErrorResponseObject noSuchElementException(Exception exception) {
        return new ErrorResponseObject(HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(EntityExistsException.class)
    public ErrorResponseObject entityExistsException(Exception exception) {
        return new ErrorResponseObject(HttpStatus.CONFLICT, exception.getMessage(), LocalDateTime.now());
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(SecurityException.class)
    public ErrorResponseObject securityException(Exception exception) {
        return new ErrorResponseObject(HttpStatus.UNAUTHORIZED, exception.getMessage(), LocalDateTime.now());
    }

}
