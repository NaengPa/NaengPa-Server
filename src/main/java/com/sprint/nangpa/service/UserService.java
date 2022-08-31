package com.sprint.nangpa.service;

import com.sprint.nangpa.mapper.UserMapper;
import com.sprint.nangpa.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

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
}
