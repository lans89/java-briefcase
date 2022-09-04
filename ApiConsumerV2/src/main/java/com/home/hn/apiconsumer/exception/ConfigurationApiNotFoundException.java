package com.home.hn.apiconsumer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ConfigurationApiNotFoundException extends ResponseStatusException {

    public ConfigurationApiNotFoundException(HttpStatus status, String nameApi) {
        super(status, String.format("Api Configuration '%s' isn't found in db",nameApi));
    }
}
