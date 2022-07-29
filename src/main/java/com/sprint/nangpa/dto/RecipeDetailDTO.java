package com.sprint.nangpa.dto;

import com.sprint.nangpa.model.RecipeCrse;
import com.sprint.nangpa.model.RecipeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 레시피 상세페이지 정보 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RecipeDetailDTO implements Serializable {
    /**
     * 레시피 기본정보
     */
    private RecipeInfo recipeInfo;

    /**
     * 레시피 과정정보
     */
    private List<RecipeCrse> recipeCrses;

    /**
     * 레시피 재료정보
     */
    private List<IrdntInfoDTO> irdntInfos;
}
