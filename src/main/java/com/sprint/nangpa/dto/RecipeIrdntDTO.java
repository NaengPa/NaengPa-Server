package com.sprint.nangpa.dto;

import com.sprint.nangpa.model.RecipeCrse;
import com.sprint.nangpa.model.RecipeIrdnt;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * 레시피 재료정보 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RecipeIrdntDTO implements Serializable {

    /**
     * 재료순번
     */
    private Long irdntSn;

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
    private String irdntTypeCd;

    /**
     * 재료타입명
     */
    private String irdntTypeNm;

    /**
     * 레시피 과정정보 변환
     *
     * @param   recipeIrdnt   : DB에서 조회한 레시피 재료정보
     * @return  RecipeCrseDTO : 변환 객체
     */
    public RecipeIrdntDTO setRecipeCrse(RecipeIrdnt recipeIrdnt){
        this.irdntSn     = recipeIrdnt.getIrdntSn();
        this.irdntNm     = recipeIrdnt.getIrdntNm();
        this.irdntCpcty  = recipeIrdnt.getIrdntCpcty();
        this.irdntTypeCd = recipeIrdnt.getIrdntTypeCd();
        this.irdntTypeNm = recipeIrdnt.getIrdntTypeNm();

        return this;
    }
}
