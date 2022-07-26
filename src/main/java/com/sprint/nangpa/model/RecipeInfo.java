package com.sprint.nangpa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 레시피 기본정보 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RecipeInfo implements Serializable {

    /**
     * 레시피 코드
     */
    private long recipeId;

    /**
     * 레시피 이름(한글)
     */
    private String recipeNmKo;

    /**
     * 간략(요약) 소개
     */
    private String summary;

    /**
     * 유형코드
     */
    private String nationCd;

    /**
     * 유형분류
     */
    private String nationNm;

    /**
     * 음식분류코드
     */
    private String typeCd;

    /**
     * 음식분류
     */
    private String typeNm;

    /**
     * 조리시간
     */
    private String cookingTime;

    /**
     * 칼로리
     */
    private String calorie;

    /**
     * 분량
     */
    private String qnt;

    /**
     *난이도
     */
    private String levelNm;

    /**
     * 재료별 분류명
     */
    private String irdntCd;

    /**
     * 가격별 분류
     */
    private String pcNm;

    /**
     * 좋아요 수
     */
    private String likeCnt;

    /**
     * 음식 사진 url
     */
    private String imgUrl;
}
