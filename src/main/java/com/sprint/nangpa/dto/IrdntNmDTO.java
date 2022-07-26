package com.sprint.nangpa.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 재료명 조회 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class IrdntNmDTO implements Serializable {

    /**
     * 행 번호
     */
    private long id;

    /**
     * 재료명
     */
    private String irdntNm;
}
