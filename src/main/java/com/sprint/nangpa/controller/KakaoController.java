package com.sprint.nangpa.controller;

import com.sprint.nangpa.config.security.oauth.KakaoService;
import com.sprint.nangpa.dto.token.AccessTokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@ResponseBody
@RequestMapping("/oauth")
public class KakaoController {

    private final KakaoService ks;

    /**
     * 카카오 서버에서 엑세스 토큰 발급
     * @param code          : 인가 코드
     * @return access_token : 엑세스 토큰
     * @throws IOException
     */
    @GetMapping("/kakao")
    public HashMap getToken(@RequestParam String code) throws IOException{
        HashMap<String, String> access_token = ks.getKakaoToken(code);
        return access_token;
    }


    /**
     * 카카오 서버에서 유저정보 조회
     * @param accessTokenDTO : accessToken
     * @return userInfo : 유저 정보
     * @throws IOException
     */
    @PostMapping("/kakao/user")
    public Map getUserInfo(@RequestBody AccessTokenDTO accessTokenDTO) throws IOException{
        return ks.getKaKaoUserInfo(accessTokenDTO.getAccess_token());
    }
}

