package com.sprint.nangpa.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 로컬 로그인시 유저가 제출하는 데이터 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SignInDto implements Serializable {

    /**
     * 사용자 아이디
     */
    private String email;

    /**
     * 사용자 비밀번호
     */
    private String password;
}
