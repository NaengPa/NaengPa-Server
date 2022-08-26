package com.sprint.nangpa.controller;

import com.sprint.nangpa.dto.board.*;
import com.sprint.nangpa.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 커뮤니티 관리 Controller
 */
@RestController
@RequestMapping(value = "/board")
@RequiredArgsConstructor
public class BoardController {

    /**
     * 커뮤니티 관리 Service
     */
    private final BoardService boardService;

    /**
     * 게시글 저장
     *
     * @param  boardInfoDTO : 게시글 저장 정보
     * @return boolean      : 저장 결과
     */
    @PostMapping
    public boolean saveBoard(@RequestBody BoardSaveDTO boardInfoDTO) {
        return boardService.saveBoard(boardInfoDTO);
    }

    /**
     * 게시글 목록 조회
     *
     * @param  email              : 사용자 아이디
     * @return List<BoardInfoDTO> : 게시글 목록
     */
    @GetMapping
    public List<BoardInfoDTO> getBoardList(@RequestParam(required = false) String email) {
        return boardService.getBoardInfoList(email);
    }

    /**
     * 게시글 수정
     *
     * @param  boardModDTO : 게시글 수정 내용
     * @return boolean     : 수정 결과
     */
    @PutMapping
    public boolean modifiedBoard(@RequestBody BoardModDTO boardModDTO){
        return boardService.modifiedBoard(boardModDTO);
    }

    /**
     * 게시글 삭제
     *
     * @param  id      : 게시글 식별 값
     * @param  email   : 사용자 이메일
     * @return boolean : 삭제 결과
     */
    @DeleteMapping
    public boolean deleteBoard(@RequestParam(value = "id") long id,
                               @RequestParam(value = "email") String email){
        BoardDelDTO boardDelDTO = BoardDelDTO.builder()
                                            .id(id)
                                            .email(email)
                                            .build();

        return boardService.deleteBoard(boardDelDTO);
    }

    /**
     * 게시글 좋아요 추가/삭제
     *
     * @param  boardLikeDTO : 게시글 좋아요 정보
     * @return int          : 해당 게시글 좋아요 수
     */
    @PostMapping("/like")
    public int changeBoardLike(@RequestBody BoardLikeDTO boardLikeDTO){
        return boardService.changeBoardLike(boardLikeDTO);
    }
}
