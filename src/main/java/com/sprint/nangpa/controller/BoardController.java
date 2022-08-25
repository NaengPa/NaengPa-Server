package com.sprint.nangpa.controller;

import com.sprint.nangpa.dto.board.BoardInfoDTO;
import com.sprint.nangpa.dto.board.BoardModDTO;
import com.sprint.nangpa.dto.board.BoardSaveDTO;
import com.sprint.nangpa.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
     * @return List<BoardInfoDTO> : 게시글 목록
     */
    @GetMapping
    public List<BoardInfoDTO> getBoardList() {
        return boardService.getBoardInfoList();
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
}
