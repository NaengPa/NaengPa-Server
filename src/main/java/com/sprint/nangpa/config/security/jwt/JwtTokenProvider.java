package com.sprint.nangpa.config.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

//    private final JwtProperties jwtProperties;
//
//    public String makeJwtToken(User user) {
//        Date now = new Date();
//
//        return Jwts.builder()
//                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
//                .setIssuer(jwtProperties.getIssuer())
//                .setIssuedAt(now)
//                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis()))
//                .claim("email", user.getEmail())
//                .signWith(SignatureAlgorithm.ES256, jwtProperties.getSecretKey())
//                .compact();
//    }


}

