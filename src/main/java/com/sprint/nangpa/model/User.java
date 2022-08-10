package com.sprint.nangpa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 사용자 관리  Model
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User implements Serializable {

    /**
     * 사용자 아이디
     */
    private String email;

    /**
     * 사용자 닉네임
     */
    private String nickname;

    /**
     * 비밀번호
     */
    private String password;

    /**
     * 프로필 사진 URL
     */
    private String imgUrl;
}
