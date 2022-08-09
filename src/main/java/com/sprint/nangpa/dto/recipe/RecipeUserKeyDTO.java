package com.sprint.nangpa.dto.recipe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * RecipeInfo 테이블과 User 테이블을 외례키로 사용하는 테이블을 위한 DTO
 * RecipeId와 email이 복합키인 경우 사용
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RecipeUserKeyDTO implements Serializable {

    /**
     * 레시피 코드
     */
    private long recipeId;

    /**
     * 사용자 아이디
     */
    private String email;
}
