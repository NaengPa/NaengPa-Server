package com.sprint.nangpa.config.security.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Getter
@ConstructorBinding
@Component
public class JwtProperties {
    @Value("${issuer}")
    private String issuer;

    @Value("${secretKey}")
    private String secretKey;

    @Value("${tokenPrefix}")
    private String tokenPrefix;
}
