package com.sprint.nangpa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 커뮤니티 글 좋아요 Model
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardLike {

    /**
     * 작성자
     */
    private String email;

    /**
     * 게시글 식별 값
     */
    private long boardId;
}
