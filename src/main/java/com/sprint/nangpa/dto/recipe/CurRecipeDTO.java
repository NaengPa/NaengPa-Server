package com.sprint.nangpa.dto.recipe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 최근 조회한 레시피 기본정보 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CurRecipeDTO implements Serializable {

    /**
     * 레시피 코드
     */
    private long recipeId;

    /**
     * 음식 사진 url
     */
    private String imgUrl;
}
