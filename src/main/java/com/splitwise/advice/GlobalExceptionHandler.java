package com.splitwise.advice;

import com.splitwise.exception.GroupNotFoundException;
import com.splitwise.exception.UserNotFoundException;
import com.sun.jdi.InternalException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({InternalException.class})
    public ResponseEntity<Map<String, String>> internalServerErrorExceptionHandle(Exception ex){

        Map<String, String> map = new HashMap<>();
        map.put("ErrorMessage", ex.getMessage());

        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({DataIntegrityViolationException.class, UserNotFoundException.class, GroupNotFoundException.class, Exception.class})
    public ResponseEntity<Map<String, String>> globalExceptionHandle(Exception ex){

        Map<String, String> map = new HashMap<>();
        map.put("ErrorMessage", ex.getMessage());

        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            errors.put(error.getCode(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.PARTIAL_CONTENT);
    }

}
