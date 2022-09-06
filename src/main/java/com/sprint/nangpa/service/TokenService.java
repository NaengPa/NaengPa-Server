package com.sprint.nangpa.service;

import com.sprint.nangpa.config.security.jwt.JwtTokenProvider;
import com.sprint.nangpa.dto.token.RefreshTokenDTO;
import com.sprint.nangpa.mapper.TokenMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtTokenProvider jwtTokenProvider;

    private final TokenMapper tokenMapper;

    public String issueRefreshToken(String email) {
        String newRefreshToken = jwtTokenProvider.makeJwtToken(email, 43800);//리프레쉬 토큰은 한달 기간

        RefreshTokenDTO refreshTokenDTO = new RefreshTokenDTO();

        refreshTokenDTO.setRefreshToken(newRefreshToken);
        refreshTokenDTO.setEmail(email);

        tokenMapper.saveRefreshToken(refreshTokenDTO);

        return newRefreshToken;
    }
}
