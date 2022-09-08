package com.sprint.nangpa.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
@Slf4j
public class ExpiredAccessTokenException extends ExpiredJwtException {
    public ExpiredAccessTokenException(Header header, Claims claims, String message) {
        super(header, claims, message);
        log.error(message);
    }

    public ExpiredAccessTokenException(Header header, Claims claims, String message, Throwable cause) {
        super(header, claims, message, cause);
        log.error(message);
    }
}
