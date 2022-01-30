package com.cromms.loginsystem.controllers.exceptions;

import com.cromms.loginsystem.services.exceptions.AuthorizationException;
import com.cromms.loginsystem.services.exceptions.EmailExistingexception;
import com.cromms.loginsystem.services.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest req) {

        var error = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(), "Usuário não encontrado",
                e.getMessage(), req.getRequestURI());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest req) {

        var error = new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(), "Error de validação", "Usuário não cadastrado",
                req.getRequestURI());

        for (FieldError x : e.getBindingResult().getFieldErrors()) {
            error.addError(x.getDefaultMessage());
        }

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> authorizationException(AuthorizationException e, HttpServletRequest req) {

        var error = new StandardError(Instant.now(), HttpStatus.FORBIDDEN.value(), "Usuário não autorizado", e.getMessage(),
                req.getRequestURI());

        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(EmailExistingexception.class)
    public ResponseEntity<StandardError> emailExistingexception(EmailExistingexception e, HttpServletRequest req) {

        var error = new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(), "Usuário não cadastrado", e.getMessage(),
                req.getRequestURI());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
