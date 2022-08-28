package com.sprint.nangpa.service;

import com.sprint.nangpa.mapper.UserMapper;
import com.sprint.nangpa.model.User;
import lombok.RequiredArgsConstructor;
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
}
