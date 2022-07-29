package com.sprint.nangpa.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 레시피 재료정보
 * (재료 타입이 다른경우 같은 재료의 데이터가 존재)
 * 레시피 내에 중복된 재료는 하나만 조회
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class IrdntInfoDTO implements Serializable {

    /**
     * 레시피 코드
     */
    private long recipeId;

    /**
     * 재료순번
     */
    private String irdntSn;

    /**
     * 재료명
     */
    private String irdntNm;
}
