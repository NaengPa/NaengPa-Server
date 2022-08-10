package com.sprint.nangpa.mapper;

import com.sprint.nangpa.dto.token.RefreshTokenDTO;
import com.sprint.nangpa.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenMapper {
    /**
     * Refresh Token 저장
     *
     * @param  token : 토큰 정보
     *                 (사용자 이메일, 토큰 값)
     * @return int   : 저장된 데이터 수
     */
    int saveRefreshToken(RefreshTokenDTO token);

    /**
     * Refresh Token 조회
     *
     * @param  email           : 사용자 이메일
     * @return RefreshTokenDTO : 토큰 정보(사용자 이메일, 토큰 값)
     */
    RefreshTokenDTO selectRefreshToken(String email);

    /**
     * Refresh Token 수정
     *
     * @param  token : 토큰 정보
     *                 (사용자 이메일, 변경할 토큰 값)
     * @return int   : 수정된 데이터 수
     */
    int updateRefreshToken(RefreshTokenDTO token);

    /**
     * Refresh Token 삭제
     *
     * @param  email : 사용자 이메일
     * @return int   : 삭제된 데이터 수
     */
    int deleteRefreshToken(String email);
}
