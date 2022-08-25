package com.sprint.nangpa.service;

import com.sprint.nangpa.dto.board.BoardInfoDTO;
import com.sprint.nangpa.dto.board.BoardSaveDTO;
import com.sprint.nangpa.dto.board.BoardRegDTO;
import com.sprint.nangpa.mapper.BoardMapper;
import com.sprint.nangpa.model.BoardImg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 커뮤니티 관리 Service
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
@RequiredArgsConstructor
public class BoardService {

    /**
     * 커뮤니티 데이터 관리 Mapper
     */
    private final BoardMapper boardMapper;

    /**
     * 커뮤니티 게시글 저장
     *
     * @param boardSaveDTO : 게시글 정보
     * @return boolean      : 저장 결과
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean saveBoard(BoardSaveDTO boardSaveDTO) {
        BoardRegDTO boardRegDTO = new BoardRegDTO(boardSaveDTO);

        // 게시글 저장
        boardMapper.insertBoard(boardRegDTO);
        // 반환받은 게시글 식별 값 세팅
        long boardId = boardRegDTO.getId();

        if (boardId != 0) {
            int sortNo = 1;
            for (String img : boardSaveDTO.getImgs()) {

                // 이미지 저장 객체 세팅
                BoardImg boardImg = new BoardImg();
                boardImg.setBoardId(boardId);
                boardImg.setSortNo(sortNo++);
                boardImg.setImg(img);

                // 이미지 저장
                int res = boardMapper.insertMoardImg(boardImg);

                if (res != 1) {
                    throw new RuntimeException("이미지 저장에 실패했습니다.");
                }
            }
        } else {
            return false;
        }

        return true;
    }

    public List<BoardInfoDTO> getBoardInfoList() {
        // 게시글 목록 조회
        List<BoardInfoDTO> boardInfoDTOS = boardMapper.selectBoardInfoList();

        // 게시글 이미지 세팅
        for (BoardInfoDTO boardInfoDTO : boardInfoDTOS) {
            long boardId = boardInfoDTO.getId();

            // 이미지 조회
            List<String> imgs = boardMapper.selectBoardImg(boardId);
            // 이미지 세팅
            boardInfoDTO.setImgs(imgs);
        }

        return boardInfoDTOS;
    }
}