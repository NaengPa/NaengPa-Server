package com.sprint.nangpa.dto.token;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AccessTokenDTO implements Serializable {
    /**
     * 엑세스 토큰
     */

    private String access_token;
}
