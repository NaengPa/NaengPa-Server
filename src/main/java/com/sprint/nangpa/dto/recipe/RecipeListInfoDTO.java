package com.sprint.nangpa.dto.recipe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 레시피 목록 정보
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RecipeListInfoDTO implements Serializable {

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
    private long likeCnt;

    /**
     * 음식 사진 url
     */
    private String imgUrl;

    /**
     * 검색 재료목록이 포함된 수
     */
    private long containCnt;

    /**
     * 사용자 좋아요 여부
     */
    private boolean likeYn;

    /**
     * 재료 목록
     */
    private List<IrdntInfoDTO> irdnts;
}
