package com.sprint.nangpa.config.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
@Slf4j
public class ExpiredAccessTokenException extends RuntimeException{
    public ExpiredAccessTokenException(String message) {
        super(message);
        log.error(message);
    }
}
