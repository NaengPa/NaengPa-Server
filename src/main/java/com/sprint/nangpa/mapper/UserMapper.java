package com.sprint.nangpa.mapper;

import com.sprint.nangpa.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    /**
     * 사용자 정보 저장
     *
     * @param  user : 사용자 정보
     * @return int  : 저장된 데이터 수
     */
    int insertUserInfo(User user);

    /**
     * 사용자 정보 조회
     *
     * @param  email : 사용자 이메일(PK)
     * @return User  : 사용자 정보
     */
    User selectUserInfo(String email);

    /**
     * 사용자 정보 수정
     *
     * @param  user : 수정될 사용자 정보
     *                변경되지 않은 데이터도 세팅해줘야 함
     * @return int  : 수정된 데이터 수
     */
    int updateUserInfo(User user);
}
