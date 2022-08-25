package com.sprint.nangpa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 커뮤니티 이미지 관리  Model
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardImg {

    /**
     * 게시글 식별 값
     */
    private long boardId;

    /**
     * 정렬 순서
     */
    private int sortNo;

    /**
     * 이미지 데이터
     */
    private String img;
}
