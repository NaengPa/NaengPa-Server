package com.sprint.nangpa.service;

import com.sprint.nangpa.config.security.jwt.JwtProperties;
import com.sprint.nangpa.dto.user.SignInDto;
import com.sprint.nangpa.mapper.UserMapper;
import com.sprint.nangpa.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 단위 테스트
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class UserServiceTest {


    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 회원 가입 성공 테스트
     */
    @Test
    public void signUp_success() {
        //given
        User user = new User("TestEmail@gmail.com", "닉네임", "비밀번호", "프로필 사진");

        //when
        String signUpResultText = userService.signUp(user);
        //then
        assertThat(signUpResultText).isEqualTo("회원가입에 성공했습니다.");
    }

    /**
     * 회원 가입 실패 테스트 - 이메일 중복된 경우
     */
    @Test
    public void signUp_fail_duplicate_Email(){
        //given
        User user1 = new User("TestEmail@gmail.com", "닉네임", "비밀번호", "프로필 사진");
        User user2 = new User("TestEmail@gmail.com", "닉네임2", "비밀번호", "프로필 사진");

        //when
        userService.signUp(user1);
        try {
            userService.signUp(user2); // 이메일 중복되므로 예외가 발생해야함
        } catch (DuplicateKeyException e) {
            System.out.println("e = " + e);
            return ;
        }

        //then
//        fail("이메일 중복되므로 예외가 발생해야함");
    }

    /**
     * 회원 가입 실패 테스트 - 닉네임 중복된 경우
     */
    @Test
    public void signUp_fail_duplicate_Nickname(){
        //given
        User user1 = new User("TestEmail@gmail.com", "닉네임", "비밀번호", "프로필 사진");
        User user2 = new User("TestEmail2@gmail.com", "닉네임", "비밀번호", "프로필 사진");

        //when
        userService.signUp(user1);
        try {
            userService.signUp(user2); // 이메일 중복되므로 예외가 발생해야함
        } catch (DuplicateKeyException e) {
            System.out.println("e = " + e);
            return ;
        }

        //then
//        fail("이메일 중복되므로 예외가 발생해야함");
    }

    /**
     * 회원 가입 테스트 - 다른 2명의 유저가 가입
     */
    @Test
    public void signUp_two_User(){
        //given
        User user1 = new User("TestEmail1@gmail.com", "닉네임1", "비밀번호", "프로필 사진");
        User user2 = new User("TestEmail2@gmail.com", "닉네임2", "비밀번호", "프로필 사진");

        //when
        String signUpText1 = userService.signUp(user1);
        String signUpText2 = userService.signUp(user2);
        
        //then
        assertThat(signUpText1).isEqualTo("회원가입에 성공했습니다.");
        assertThat(signUpText2).isEqualTo("회원가입에 성공했습니다.");

    }

    /**
     * 로컬 로그인 테스트
     */
    @Test
    public void signInTest() throws Exception {
        //given
        User user = new User("TestEmail@gmail.com", "닉네임", "비밀번호", "프로필 사진");
        userService.signUp(user); //회원가입

        //when
        SignInDto signInDto = new SignInDto();
        signInDto.setEmail(user.getEmail());
        signInDto.setPassword(user.getPassword());

        HashMap<String, String> tokenMap = userService.signIn(signInDto); //로그인 해서 accessToken, RefreshToken 받아오기


        //받아온 엑세스 토큰으로 유저정보 잘 받아와지나 테스트
        Claims claims = Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(tokenMap.get("accessToken"))
                .getBody();

        String email = (String) claims.get("email");
        User resultUser = userService.getUserInfo(email);

        //then
        assertThat(resultUser.getNickname()).isEqualTo(user.getNickname());

    }
}
