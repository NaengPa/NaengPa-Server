package com.sprint.nangpa.config.security.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConstructorBinding;

@RequiredArgsConstructor
@Getter
@ConstructorBinding
public class JwtProperties {
    @Value("${issuer}")
    private final String issuer;

    @Value("${secretKey}")
    private final String secretKey;

    @Value("${tokenPrefix}")
    private final String tokenPrefix;
}
