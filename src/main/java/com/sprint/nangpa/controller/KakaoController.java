package com.sprint.nangpa.controller;

import com.sprint.nangpa.config.security.oauth.KakaoService;
import com.sprint.nangpa.model.User;
import com.sprint.nangpa.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/oauth")
public class KakaoController {

    private final KakaoService ks;
    private final UserService userService;

    /**
     * 카카오 서버에서 유저정보 조회
     * @param code          : 인가 코드
     * @return accessToken : 엑세스 토큰
     * @throws IOException
     */
    @GetMapping("/kakao")
    public String getToken(@RequestParam String code) throws IOException, ParseException {
        return ks.KakaoLogin(code);
    }

    @GetMapping("/login")
    public User login(@RequestAttribute Claims claims){
        String email = (String) claims.get("email");
        return userService.getUserInfo(email);
    }

}

