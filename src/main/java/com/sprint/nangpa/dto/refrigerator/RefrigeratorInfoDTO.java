package com.sprint.nangpa.dto.refrigerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 냉장고 재료 Model
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RefrigeratorInfoDTO implements Serializable {

    /**
     * 사용자 이메일
     */
    private String email;

    /**
     * 재료명
     */
    private List<String> irdntNms;
}
