package com.ms.cartoes.mscartoes.resources.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionsHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidateStandardError> methodNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        final ValidateStandardError err = new ValidateStandardError();

        err.setStatus(status.value());
        err.setTimestamp(Instant.now());
        err.setPath(request.getRequestURI());
        err.setMessage("Validation error");
        err.setErrors(e.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList())
        );

        return ResponseEntity.status(status).body(err);
    }

}
