package com.sprint.nangpa.controller;

import com.sprint.nangpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 사용자 관리 Controller
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    /**
     * 사용자 관리 Service
     */
    private final UserService userService;

    /**
     * 이메일 중복 확인
     *
     * @param  email   : 확인 이메일
     * @return boolean : 중복 결과 (true => 중복 됨)
     */
    @GetMapping("/duplEmail")
    public boolean duplEmail(@RequestParam("email") String email){
        return userService.duplCheckEmail(email);
    }

    /**
     * 닉네임 중복 확인
     *
     * @param  nickname : 확인 닉네임
     * @return boolean  : 중복 결과 (true => 중복 됨)
     */
    @GetMapping("/duplNickname")
    public boolean duplNickname(@RequestParam("nickname") String nickname){
        return userService.duplCheckNickname(nickname);
    }
}