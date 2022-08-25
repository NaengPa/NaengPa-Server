package com.sprint.nangpa.service;

import com.sprint.nangpa.dto.board.BoardInfoDTO;
import com.sprint.nangpa.dto.board.BoardRegDTO;
import com.sprint.nangpa.mapper.BoardMapper;
import com.sprint.nangpa.model.BoardImg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;

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
     * @param  boardInfoDTO : 게시글 정보
     * @return boolean      : 저장 결과
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean saveBoard(BoardInfoDTO boardInfoDTO) {
        BoardRegDTO boardRegDTO = new BoardRegDTO(boardInfoDTO);

        // 게시글 저장
        boardMapper.insertBoard(boardRegDTO);
        // 반환받은 게시글 식별 값 세팅
        long boardId = boardRegDTO.getId();

        if(boardId != 0) {
            int sortNo = 1;
            for(String img : boardInfoDTO.getImgs()){

                // 이미지 저장 객체 세팅
                BoardImg boardImg = new BoardImg();
                boardImg.setBoardId(boardId);
                boardImg.setSortNo(sortNo++);
                boardImg.setImg(img);

                // 이미지 저장
                int res = boardMapper.insertMoardImg(boardImg);

                if(res != 1){
                    throw new RuntimeException("이미지 저장에 실패했습니다.");
                }
            }
        } else {
            return false;
        }

        return true;
    }
}
