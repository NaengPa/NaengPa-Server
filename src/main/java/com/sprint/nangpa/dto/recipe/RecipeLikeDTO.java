package com.sprint.nangpa.dto.recipe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 레시피 좋아요 추가/삭제 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RecipeLikeDTO implements Serializable {

    /**
     * 작성자
     */
    private String email;

    /**
     * 레시피 식별 값
     */
    private long recipeId;
}
