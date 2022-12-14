package com.sprint.nangpa.service;

import com.sprint.nangpa.dto.board.*;
import com.sprint.nangpa.mapper.BoardMapper;
import com.sprint.nangpa.model.BoardImg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 게시글 목록 조회
     *
     * @return List<BoardInfoDTO> : 게시글 목록
     */
    public List<BoardInfoDTO> getBoardInfoList(String email) {
        // 게시글 목록 조회
        List<BoardInfoDTO> boardInfoDTOS = boardMapper.selectBoardInfoList(email);

        // 게시글 목록이 있는 경우
        if(boardInfoDTOS.size() > 0) {
            Map<Long, Integer> adap = new HashMap<>();

            List<Long> boardIds = new ArrayList<>();

            // 게시글 번호 세팅
            for (int i = 0; i < boardInfoDTOS.size(); i++) {
                boardInfoDTOS.get(i).setImgs(new ArrayList<>());
                boardIds.add(boardInfoDTOS.get(i).getId());
                adap.put(boardInfoDTOS.get(i).getId(), i);
            }

            // 게시글 이미지 조회
            List<BoardImgDTO> imgs = boardMapper.selectBoardImg(boardIds);

            // 게시글 이미지 세팅
            for(BoardImgDTO img : imgs) {
                boardInfoDTOS.get(adap.get(img.getBoardId())).getImgs().add(img.getImg());
            }
        }

        return boardInfoDTOS;
    }

    /**
     * 게시글 수정
     *
     * @param  boardModDTO : 게시글 수정 정보
     * @return boolean     : 수정 결과
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean modifiedBoard(BoardModDTO boardModDTO) {
        return boardMapper.updateBoard(boardModDTO) == 1;
    }

    /**
     * 게시글 수정
     *
     * @param  boardDelDTO : 게시글 삭제 정보
     * @return boolean     : 삭제 결과
     */
    public boolean deleteBoard(BoardDelDTO boardDelDTO) {
        return boardMapper.deleteBoard(boardDelDTO) == 1;
    }

    /**
     * 게시글 좋아요 추가
     *
     * @param  boardLikeDTO : 게시글 좋아요 정보
     * @return int          : 해당 게시글 좋아요 수
     */
    @Transactional(rollbackFor = Exception.class)
    public int changeBoardLike(BoardLikeDTO boardLikeDTO) {
        // 저장된 좋아요 조회
        int cnt = boardMapper.selectBoardLike(boardLikeDTO);

        int res;
        if(cnt == 0){    // 저장된 좋아요가 없으면 추가
            res = boardMapper.insertBoardLike(boardLikeDTO);
        } else {                    // 저장된 좋아요가 있으면 삭제
            res = boardMapper.deleteBoardLike(boardLikeDTO);
        }

        if(res < 1){
            throw new RuntimeException("좋아요 실패!!");
        }

        return boardMapper.selectBoardLikeCnt(boardLikeDTO.getBoardId());
    }
}