package com.sprint.nangpa.dto.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 커뮤니티 이미지 정보 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardImgDTO {

    /**
     * 게시글 식별 값
     */
    private long boardId;

    /**
     * 이미지 데이터
     */
    private String img;
}
