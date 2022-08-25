package com.sprint.nangpa.dto.board;

import lombok.*;

import java.io.Serializable;

/**
 * 커뮤니티 글 수정하기 위한 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardDelDTO implements Serializable {

    /**
     * 게시글 식별 값
     */
    private long id;

    /**
     * 사용자 이메일
     */
    private String email;

    @Builder
    public BoardDelDTO(long id, String email) {
        this.id = id;
        this.email = email;
    }
}
