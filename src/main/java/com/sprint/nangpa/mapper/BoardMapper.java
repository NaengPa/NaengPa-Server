package com.sprint.nangpa.mapper;

import com.sprint.nangpa.dto.board.BoardInfoDTO;
import com.sprint.nangpa.dto.board.BoardModDTO;
import com.sprint.nangpa.dto.board.BoardRegDTO;
import com.sprint.nangpa.model.BoardImg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    /**
     * 게시글 저장
     *
     * @param  boardRegDTO : 게시글 정보
     * @return int         : 저장된 행의 수
     */
    int insertBoard(BoardRegDTO boardRegDTO);

    /**
     * 게시글 이미지 저장
     *
     * @param  boardImg : 게시글 이미지 정보
     * @return int      : 저장된 행의 수
     */
    int insertMoardImg(BoardImg boardImg);

    /**
     * 게시글 정보 조회
     *
     * @return List<BoardInfoDTO> : 게시글 목록
     */
    List<BoardInfoDTO> selectBoardInfoList();

    /**
     * 게시글 이미지 조회
     *
     * @param  boardId      : 게시글 식별 값
     * @return List<String> : 이미지 목록
     */
    List<String> selectBoardImg(long boardId);

    /**
     * 게시글 수정
     *
     * @param  boardModDTO : 게시글 수정 정보
     * @return boolean     : 수정 결과
     */
    int updateBoard(BoardModDTO boardModDTO);
}
