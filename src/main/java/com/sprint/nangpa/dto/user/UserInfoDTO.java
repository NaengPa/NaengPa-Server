package com.sprint.nangpa.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 사용자 정보 조회 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
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
