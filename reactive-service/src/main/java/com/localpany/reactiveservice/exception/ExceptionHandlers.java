package com.localpany.reactiveservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.PersistenceException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlers {

    @ExceptionHandler(PersistenceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleSQLGrammarException(PersistenceException ex) {
        log.error(ex.getMessage(), ex);
        return generate(HttpStatus.INSUFFICIENT_STORAGE, ex);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleNoSuchElementException(NoSuchElementException ex) {
        log.error(ex.getMessage(), ex);
        return generate(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleRuntimeException(RuntimeException ex) {
        log.error(ex.getMessage(), ex);
        return generate(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return generate(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    public Map<String, Object> generate(HttpStatus httpStatus, Exception ex){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", httpStatus.value());
        body.put("date", LocalDateTime.now().toString());
        body.put("exception", ex.getClass().getSimpleName());
        body.put("message", ex.getMessage());
        return body;
    }
}