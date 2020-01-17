package com.example.haysademo.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BookNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(Exception exception, WebRequest webRequest) {
        String bodyOfResponse = "Book not found";
        return handleExceptionInternal(exception, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler({BookIdMismatchException.class, ConstraintViolationException.class, DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleBadRequest(Exception exception, WebRequest webRequest) {
        String bodyOfResponse = "Book ID Mismatch";
        return handleExceptionInternal(exception, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }
}
