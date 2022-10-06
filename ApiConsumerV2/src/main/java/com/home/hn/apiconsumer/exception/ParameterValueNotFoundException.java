package com.home.hn.apiconsumer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ParameterValueNotFoundException extends ResponseStatusException {

    public ParameterValueNotFoundException(HttpStatus status, String parameterName) {
        super(status, String.format("No value was found to Parameter '%s'",parameterName));
    }
}
