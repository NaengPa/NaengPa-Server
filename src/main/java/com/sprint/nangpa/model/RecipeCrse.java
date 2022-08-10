package com.sprint.nangpa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 레시피 과정정보 Model
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RecipeCrse implements Serializable {

    /**
     * 레시피 코드
     */
    private long recipeId;

    /**
     * 요리설명순서
     */
    private String cookingNo;

    /**
     * 요리설명
     */
    private String cookingDc;

    /**
     * 과정팁
     */
    private String stepTip;

    /**
     * 과정 이미지 URL
     */
    private String streStepImgUrl;
}
