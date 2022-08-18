package com.sprint.nangpa.controller;

import com.sprint.nangpa.config.security.jwt.JwtTokenProvider;
import com.sprint.nangpa.config.security.oauth.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/oauth")
public class KakaoController {

    private final KakaoService ks;

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 카카오 서버에서 유저정보 조회
     * @param code          : 인가 코드
     * @return accessToken : 엑세스 토큰
     * @throws IOException
     */
    @GetMapping("/kakao")
    public String getToken(@RequestParam String code) throws IOException{
        return ks.KakaoLogin(code);
    }

}

