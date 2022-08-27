package com.sprint.nangpa.config.security.jwt;

import io.jsonwebtoken.Claims;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Getter
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;


    @Builder
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public static JwtAuthenticationFilter of(JwtTokenProvider jwtTokenProvider) {
        return JwtAuthenticationFilter.builder()
                .jwtTokenProvider(jwtTokenProvider)
                .build();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        Claims claims = jwtTokenProvider.parseJwtToken(authorizationHeader);

        request.setAttribute("claims",claims); // jwt 정보 컨트롤러에서 사용할 수 있게 request에 담기
        filterChain.doFilter(request, response);
    }

}
