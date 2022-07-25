package com.sprint.nangpa.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 레시피 목록 조회 요청 DTO
 * 재료 목록을 List로 바인딩
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RecipeSerDTO {

    /**
     * 재료 목록
     */
    private List<String> irdntNms;
}
