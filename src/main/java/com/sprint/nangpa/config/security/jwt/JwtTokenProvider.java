package com.sprint.nangpa.config.security.jwt;

<<<<<<< HEAD
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
=======
>>>>>>> 79f5af84cd26da421ba0db854f88b0f69a2f7993
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

<<<<<<< HEAD
    private final JwtProperties jwtProperties;

    public String makeJwtToken(String email) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis()))
                .claim("email", email)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecretKey())
                .compact();
    }
=======
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


>>>>>>> 79f5af84cd26da421ba0db854f88b0f69a2f7993
}

