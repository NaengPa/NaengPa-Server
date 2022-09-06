package com.sprint.nangpa.controller;

import com.sprint.nangpa.dto.user.SignInDto;
import com.sprint.nangpa.model.User;
import com.sprint.nangpa.service.TokenService;
import com.sprint.nangpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
     * 토큰 발급 Service
     */
    private final TokenService tokenService;

    /**
     * 이메일 중복 확인
     *
     * @param email : 확인 이메일
     * @return boolean : 중복 결과 (true => 중복 됨)
     */
    @GetMapping("/duplEmail")
    public boolean duplEmail(@RequestParam("email") String email) {
        return userService.duplCheckEmail(email);
    }

    /**
     * 닉네임 중복 확인
     *
     * @param nickname : 확인 닉네임
     * @return boolean  : 중복 결과 (true => 중복 됨)
     */
    @GetMapping("/duplNickname")
    public boolean duplNickname(@RequestParam("nickname") String nickname) {
        return userService.duplCheckNickname(nickname);
    }

    /**
     * 회원 가입
     *
     * @param submittedUserInfo : 유저 정보(닉네임, 이메일, 비밀번호)
     * @return String           : 회원 가입 성공했다는 문자열
     */
    @PostMapping("/signUp")
    public String signUp(@RequestBody User submittedUserInfo) {
        return userService.signUp(submittedUserInfo);
    }

    /**
     * 로그인
     *
     * @param    signInDto : 유저가 제출한 아이디
     * @return   String    : 엑세스 토큰
     */
    @PostMapping("/signIn")
    public HashMap<String, String> signIn(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    }
}
