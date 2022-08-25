package com.sprint.nangpa.dto.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 커뮤니티 글 저장하기 위한 DTO
 */
@Getter
@Setter
@ToString
public class BoardLikeAddDTO {

    /**
     * 게시글 식별 값
     */
    private long id;

    /**
     * 레시피 코드
     */
    private long recipeId;

    /**
     * 작성자
     */
    private String email;

    /**
     * 게시글 내용
     */
    private String content;

    public BoardLikeAddDTO(BoardSaveDTO boardInfo) {
        this.recipeId = boardInfo.getRecipeId();
        this.email    = boardInfo.getEmail();
        this.content  = boardInfo.getContent();
    }
}
