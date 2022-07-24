package com.sprint.nangpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * 레시피 기본정보 Entity
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "RECIPE_INFO")
@ToString
public class RecipeInfo {

    /**
     * 레시피 코드
     */
    @Id
    private Long recipeId;

    /**
     * 레시피 이름(한글)
     */
    @Column(length = 50)
    private String recipeNmKo;

    /**
     * 간략(요약) 소개
     */
    @Column(length = 100)
    private String summary;

    /**
     * 유형코드
     */
    @Column(length = 20)
    private String nationCd;

    /**
     * 유형분류
     */
    @Column(length = 50)
    private String nationNm;

    /**
     * 음식분류코드
     */
    @Column(length = 20)
    private String typeCd;

    /**
     * 음식분류
     */
    @Column(length = 50)
    private String typeNm;

    /**
     * 조리시간
     */
    @Column(length = 20)
    private String cookingTime;

    /**
     * 칼로리
     */
    @Column(length = 20)
    private String calorie;

    /**
     * 분량
     */
    @Column(length = 20)
    private String qnt;

    /**
     * 난이도
     */
    @Column(length = 20)
    private String levelNm;

    /**
     * 재료별 분류명
     */
    @Column(length = 20)
    private String irdntCd;

    /**
     * 가격별 분류
     */
    @Column(length = 50)
    private String pcNm;

    /**
     * 좋아요
     */
    private Long likeCnt;

    /**
     * 연관 관계 설정(주체X)
     */
    @JsonIgnore
    @OneToMany(mappedBy = "recipeInfo")
    private List<RecipeCrse> recipeCrses;

    /**
     * 연관 관계 설정(주체X)
     */
    @JsonIgnore
    @OneToMany(mappedBy = "recipeInfo")
    private List<RecipeIrdnt> recipeIrdnts;

    @Builder
    public RecipeInfo(Long recipeId, String recipeNmKo, String summary, String nationCd, String nationNm, String typeCd, String typeNm, String cookingTime, String calorie, String qnt, String levelNm, String irdntCd, String pcNm, Long likeCnt) {
        this.recipeId = recipeId;
        this.recipeNmKo = recipeNmKo;
        this.summary = summary;
        this.nationCd = nationCd;
        this.nationNm = nationNm;
        this.typeCd = typeCd;
        this.typeNm = typeNm;
        this.cookingTime = cookingTime;
        this.calorie = calorie;
        this.qnt = qnt;
        this.levelNm = levelNm;
        this.irdntCd = irdntCd;
        this.pcNm = pcNm;
        this.likeCnt = likeCnt;
    }
}
