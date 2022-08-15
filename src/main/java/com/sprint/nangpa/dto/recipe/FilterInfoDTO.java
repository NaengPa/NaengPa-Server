package com.sprint.nangpa.dto.recipe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 레시피 목록 필터 관련 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FilterInfoDTO {

    /**
     * 음식 종류
     */
    private List<String> nations_nm;

    /**
     * 요리 난이도
     */
    private List<String> level_nm;
}
