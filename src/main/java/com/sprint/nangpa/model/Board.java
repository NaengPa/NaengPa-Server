package com.sprint.nangpa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 커뮤니티 관리  Model
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Board implements Serializable {

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

    /**
     * 생성 시간
     */
    private LocalDateTime createDate;

    /**
     * 수정 시간
     */
    private LocalDateTime modifiedDate;
}
