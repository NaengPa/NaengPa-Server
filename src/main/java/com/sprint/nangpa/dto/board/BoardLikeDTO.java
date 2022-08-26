package com.sprint.nangpa.dto.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 커뮤니티 글 좋아요 추가/삭제 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardLikeDTO {

    /**
     * 작성자
     */
    private String email;

    /**
     * 게시글 식별 값
     */
    private long boardId;
}
