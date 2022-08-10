package com.sprint.nangpa.dto.token;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * JWT 사용 시, Refresh Token 저장을 위한 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RefreshTokenDTO {

    /**
     * 사용자 아이디
     */
    private String email;

    /**
     * Refresh Token 값
     */
    private String refreshToken;
}
