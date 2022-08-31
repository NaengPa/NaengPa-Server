package com.sprint.nangpa.service;

import com.sprint.nangpa.config.security.jwt.JwtTokenProvider;
import com.sprint.nangpa.dto.user.SignInDto;
import com.sprint.nangpa.mapper.UserMapper;
import com.sprint.nangpa.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 사용자 정보 저장
     *
     * @param  user    : 저장 정보
     * @return boolean : 저장 결과
     */
    public boolean saveUser(User user){
        int res = userMapper.insertUserInfo(user);

        return res == 1;
    }

    /**
     * 이메일 중복 확인
     *
     * @param  email   : 확인 이메일
     * @return boolean : 중복 결과 (true => 중복 됨)
     */
    public boolean duplCheckEmail(String email) {
        User user = userMapper.selectUserInfo(email);

        return user != null;
    }

    /**
     * 닉네임 중복 확인
     *
     * @param  nickname : 확인 닉네임
     * @return boolean  : 중복 결과 (true => 중복 됨)
     */
    public boolean duplCheckNickname(String nickname) {
        User user = userMapper.selectUserInfoByNickname(nickname);

        return user != null;
    }


    /**
     * 유저 정보 조회
     *
     * @param email : 유저 이메일
     * @return User : 유저 정보
     */
    public User getUserInfo(String email) {
        return userMapper.selectUserInfo(email);
    }


    public String signUp(User submittedUserInfo) throws Exception{

        boolean duplCheckEmail = duplCheckEmail(submittedUserInfo.getEmail());
        boolean duplCheckNickname = duplCheckNickname(submittedUserInfo.getNickname());

        if (duplCheckEmail) {
            throw new Exception("중복된 이메일 입니다.");
        }

        if (duplCheckNickname) {
            throw new Exception("중복된 닉네임 입니다.");
        }

        User user = new User();

        user.setEmail(submittedUserInfo.getEmail());
        user.setImgUrl(submittedUserInfo.getImgUrl());
        user.setNickname(submittedUserInfo.getNickname());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(submittedUserInfo.getPassword()));

        boolean isUserSaved = saveUser(user);


        if (!isUserSaved) {
            throw new Exception("회원가입에 실패했습니다.");
        }
        return "회원가입에 성공했습니다.";
    }

    /**
     * 로그인 함수
     *
     * @Param    signInDto : 아이디, 비밀번호
     * @return accessToken : 로그인시 발급해주는 엑세스 토큰
     */
    public String signIn(SignInDto signInDto) throws UsernameNotFoundException{
        User user = getUserInfo(signInDto.getEmail());

        if (user == null) {
            throw new UsernameNotFoundException("이메일에 등록된 유저가 없습니다. 이메일을 다시 한번 확인해주세요.");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (!passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
            throw new UsernameNotFoundException("비밀번호가 일치 하지 않습니다.");
        }


        return this.jwtTokenProvider.makeJwtToken(signInDto.getEmail(), 30);

    }
}
