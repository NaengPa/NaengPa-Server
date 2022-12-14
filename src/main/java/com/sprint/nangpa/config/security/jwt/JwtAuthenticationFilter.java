package com.sprint.nangpa.config.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


@Component
@Getter
@Slf4j
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

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods","*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        response.setHeader("Content-Type", "*");

        if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK); //option ???????????? ???????????? ??????
        }else { // ?????? ???????????? ?????? ??????

            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            try {
                HashMap<String, Object> parseJwtTokenMap = jwtTokenProvider.parseJwtToken(authorizationHeader);
                Claims claims = (Claims)parseJwtTokenMap.get("claims");
                String token = (String) parseJwtTokenMap.get("token");

                request.setAttribute("claims", claims); // jwt ?????? ?????????????????? ????????? ??? ?????? request??? ??????
                request.setAttribute("token",token);

            } catch (ExpiredJwtException jwtException) {
                log.info("?????? ??????");

                ObjectMapper mapper = new ObjectMapper();

                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("UTF-8");

                ResponseStatusException responseStatusException = new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "????????? ?????????????????????.");

                mapper.writeValue(response.getWriter(), responseStatusException);

            }catch (JwtException | IllegalArgumentException exception) {
                log.info("jwtException : {}", exception);
                throw exception;
            }

            filterChain.doFilter(request, response);
        }
    }

}
