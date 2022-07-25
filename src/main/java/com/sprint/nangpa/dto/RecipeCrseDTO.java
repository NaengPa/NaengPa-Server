package com.sprint.nangpa.dto;

import com.sprint.nangpa.model.RecipeCrse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 레시피 과정정보 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RecipeCrseDTO implements Serializable {

    /**
     * 요리설명순서
     */
    private Long cookingNo;

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

    /**
     * 레시피 과정정보 변환
     *
     * @param   recipeCres    : DB에서 조회한 레시피 과정정보
     * @return  RecipeCrseDTO : 변환 객체
     */
    public RecipeCrseDTO setRecipeCrse(RecipeCrse recipeCres){
        this.cookingNo      = recipeCres.getCookingNo();
        this.cookingDc      = recipeCres.getCookingDc();
        this.stepTip        = recipeCres.getStepTip();
        this.streStepImgUrl = recipeCres.getStreStepImgUrl();

        return this;
    }
}
