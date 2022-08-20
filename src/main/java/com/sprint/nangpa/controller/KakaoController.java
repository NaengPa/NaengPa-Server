package com.sprint.nangpa.controller;

import com.sprint.nangpa.config.security.jwt.JwtAuthenticationFilter;
import com.sprint.nangpa.config.security.oauth.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/oauth")
public class KakaoController {

    private final KakaoService ks;

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

    @GetMapping("/login")
    public String login(){
        return "로그인 되었습니다.";
    }


}

