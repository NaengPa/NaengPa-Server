package com.sprint.nangpa.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 레시피 과정정보
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "RECIPE_CRSE")
@ToString
public class RecipeCrse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 레시피 코드
     */
    @ManyToOne
    @JoinColumn(name = "recipeId")
    private RecipeInfo recipeInfo;

    /**
     * 요리설명순서
     */
    private Long cookingNo;

    /**
     * 요리설명
     */
    @Column(length = 1000)
    private String cookingDc;

    /**
     * 과정팁
     */
    @Column(length = 1000)
    private String stepTip;

    /**
     * 과정 이미지 URL
     */
    @Column(length = 500)
    private String streStepImgUrl;

    @Builder
    public RecipeCrse(RecipeInfo recipeInfo, Long cookingNo, String cookingDc, String stepTip, String streStepImgUrl) {
        this.recipeInfo = recipeInfo;
        this.cookingNo = cookingNo;
        this.cookingDc = cookingDc;
        this.stepTip = stepTip;
        this.streStepImgUrl = streStepImgUrl;
    }
}