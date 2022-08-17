package com.sprint.nangpa.model;

import com.sprint.nangpa.dto.user.UserInfoDTO;
import lombok.*;

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

    @Builder
    public User(String email, String nickname, String password, String imgUrl) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.imgUrl = imgUrl;
    }

    public User(UserInfoDTO userInfo) {
        this.email    = userInfo.getEmail();
        this.nickname = userInfo.getNickname();
        this.imgUrl   = userInfo.getImgUrl();
    }
}
