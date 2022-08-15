package com.sprint.nangpa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 레시피 재료정보 Model
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RecipeIrdnt implements Serializable {

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

    /**
     * 재료용량
     */
    private String irdntCpcty;

    /**
     * 재료타입 코드
     */
    private String irdntYypeCd;

    /**
     * 재료타입명
     */
    private String irdntTypeNm;

    /**
     * 검색 재료목록이 포함된 수
     */
    private long containCnt;
}
