package com.splitwise.advice;

import com.splitwise.exception.UserNotFound;
import com.sun.jdi.InternalException;
import jakarta.validation.ConstraintViolationException;
import jdk.jshell.spi.ExecutionControl;
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

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<Map<String, String>> userNotFoundExceptionHandle(UserNotFound ex){

        Map<String, String> map = new HashMap<>();
        map.put("Error Message", ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InternalException.class, DataIntegrityViolationException.class})
    public ResponseEntity<Map<String, String>> internalServerErrorExceptionHandle(Exception ex){

        Map<String, String> map = new HashMap<>();
        map.put("Error Message", ex.getMessage());

        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler({DataIntegrityViolationException.class})
//    public ResponseEntity<Map<String, String>> validationExceptionHandle(DataIntegrityViolationException ex) {
//
//        Map<String, String> map = new HashMap<>();
//        System.out.println(ex);
//        map.put()
//
//    }

}
