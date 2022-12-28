package com.localpany.reactiveservice.service.impl;

import com.localpany.reactiveservice.service.LoginService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LoginServiceImpl implements LoginService {
    private final String USER_DEFAULT = "john.doe";
    private final String PASSWORD_DEFAULT = "Pas$w0rd";

    @Override
    public Mono<Boolean> verify(String user, String password) {
        return Mono.just(validateUser(user)&&validatePassword(password));
    }

    private boolean validateUser(String user){
        return validateString(user) && user.equals(USER_DEFAULT);
    }

    private boolean validatePassword(String password){
        return validateString(password) && password.equals(PASSWORD_DEFAULT);
    }

    private boolean validateString(String value){
        return value!=null && !value.trim().isEmpty();
    }
}
