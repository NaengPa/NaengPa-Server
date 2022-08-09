package com.sprint.nangpa.dto.recipe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 최근 조회한 레시피 목록 조회 요청 DTO
 * 래시피 코드 목록을 List로 바인딩
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RecipeSerByRecipeIdDTO {

    /**
     * 레시피 코드
     */
    private List<Long> recipeIds;
}
