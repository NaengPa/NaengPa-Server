package com.sprint.nangpa.controller;

import com.sprint.nangpa.dto.board.BoardInfoDTO;
import com.sprint.nangpa.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public boolean saveBoard(@RequestBody BoardInfoDTO boardInfoDTO) {
        return boardService.saveBoard(boardInfoDTO);
    }
}
