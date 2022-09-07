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

        //이메일에 저장된 리프레쉬 토큰이 있으면 그 토큰 수정
        RefreshTokenDTO selectRefreshToken = tokenMapper.selectRefreshToken(email);

        RefreshTokenDTO refreshTokenDTO = new RefreshTokenDTO();

        refreshTokenDTO.setRefreshToken(newRefreshToken);
        refreshTokenDTO.setEmail(email);

        if (selectRefreshToken == null) {
            // 없으면 새로 만들어서 발급
            tokenMapper.saveRefreshToken(refreshTokenDTO);
        }else{
            //기존에 저정된 토큰 수정
            tokenMapper.updateRefreshToken(refreshTokenDTO);
        }

        return newRefreshToken;
    }
}
