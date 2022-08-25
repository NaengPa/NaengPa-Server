package com.sprint.nangpa.dto.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 커뮤니티 정보 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardInfoDTO implements Serializable {

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
     * 사용자 닉네임
     */
    private String nickname;

    /**
     * 프로필 사진 URL
     */
    private String imgUrl;

    /**
     * 이미지 데이터
     */
    private List<String> imgs;
}
