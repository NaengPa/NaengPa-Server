package com.sprint.nangpa.controller;

import com.sprint.nangpa.config.security.oauth.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/oauth")
public class KakaoController {

    private final KakaoService ks;

    /**
     * 카카오 서버에서 유저정보 조회
     * @param code          : 인가 코드
     * @return userInfo : 유저 정보
     * @throws IOException
     */
    @GetMapping("/kakao")
    public Map getToken(@RequestParam String code) throws IOException{
        return ks.kakaoLogin(code);
    }
}

