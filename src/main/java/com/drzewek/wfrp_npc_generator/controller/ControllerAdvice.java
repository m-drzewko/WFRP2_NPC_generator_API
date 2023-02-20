package com.drzewek.wfrp_npc_generator.controller;

import com.drzewek.wfrp_npc_generator.model.response.ErrorResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.persistence.EntityNotFoundException;
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
    public ErrorResponseObject noSuchElementExcetion(Exception exception) {
        return new ErrorResponseObject(HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());
    }

}
