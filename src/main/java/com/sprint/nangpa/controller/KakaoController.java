package com.sprint.nangpa.controller;

import com.sprint.nangpa.config.security.oauth.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class KakaoController {

    private final KakaoService ks;

    @GetMapping("/kakao")
    public String getCI(@RequestParam String code) throws IOException{
        System.out.println("code = " + code);
        String access_token = ks.getToken(code);
//        Map<String, Object> userInfo = ks.getUserInfo(access_token);
        return access_token;
    }
}
