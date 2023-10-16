package com.avanade.todolist.exceptions;

import com.avanade.todolist.exceptions.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class APIExceptionHandler {
    @ExceptionHandler(UsernameAlreadyExists.class)
    public ResponseEntity<ErrorMessage> usernameAlreadyExistsException(UsernameAlreadyExists ex, HttpServletRequest request) {
        log.error("API Error: ", ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(StartDateIsAfterCurrentDate.class)
    public ResponseEntity<ErrorMessage> startDateMustBeGreaterThanCurrentDate(StartDateIsAfterCurrentDate ex, HttpServletRequest request){
        log.error("API Error: ", ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(EndDateIsBeforeStartDate.class)
    public ResponseEntity<ErrorMessage> endDateIsBeforeStartDate(EndDateIsBeforeStartDate ex, HttpServletRequest request){
        log.error("API Error: ", ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(EntityNotFoundException ex, HttpServletRequest request){
        log.error("API Error: ", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<ErrorMessage> userNotAuthorized(UnauthorizedUser ex, HttpServletRequest request){
        log.error("API Error: ", ex);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.UNAUTHORIZED, ex.getMessage()));
    }

    @ExceptionHandler(SizeLimitExceededException.class)
    public ResponseEntity<ErrorMessage> sizeLimitExceededException(SizeLimitExceededException ex, HttpServletRequest request){
        log.error("API Error: ", ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, ex.getMessage()));

    }
}
