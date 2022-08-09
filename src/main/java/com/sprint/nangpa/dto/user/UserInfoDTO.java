package com.sprint.nangpa.dto.user;

import java.io.Serializable;

/**
 * 사용자 정보 조회 DTO
 */
public class UserInfoDTO implements Serializable {

    /**
     * 사용자 아이디
     */
    private String email;

    /**
     * 사용자 닉네임
     */
    private String nickname;

    /**
     * 프로필 사진 URL
     */
    private String imgUrl;
}
