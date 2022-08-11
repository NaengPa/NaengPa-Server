package com.sprint.nangpa.controller;

import com.sprint.nangpa.config.security.oauth.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/oauth")
public class KakaoController {

    private final KakaoService ks;

    @GetMapping("/kakao")
    public HashMap getCI(@RequestParam String code) throws IOException{
        System.out.println("code = " + code);
        HashMap<String, String> access_token = ks.getToken(code);
        return access_token;
    }
}
