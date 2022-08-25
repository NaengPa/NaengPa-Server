package com.sprint.nangpa.dto.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 커뮤니티 글 수정하기 위한 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardModDTO implements Serializable {

    /**
     * 게시글 식별 값
     */
    private long id;

    /**
     * 게시글 내용
     */
    private String content;
}
