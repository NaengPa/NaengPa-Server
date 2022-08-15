package com.sprint.nangpa.dto.recipe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 레시피 목록 페이지 관려 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RecipeListDTO {

    /**
     * 필터 정보
     */
    private FilterInfoDTO filterInfo;

    /**
     * 레시피 목록 정보
     */
    private List<RecipeInfoDTO> recipeInfos;
}
