package com.sprint.nangpa.mapper;

import com.sprint.nangpa.dto.board.*;
import com.sprint.nangpa.model.BoardImg;
import com.sprint.nangpa.model.BoardLike;
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
     * @param  email              : 사용자 이메일
     * @return List<BoardInfoDTO> : 게시글 목록
     */
    List<BoardInfoDTO> selectBoardInfoList(String email);

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

    /**
     * 게시글 수정
     *
     * @param  boardDelDTO : 게시글 삭제 정보
     * @return boolean     : 삭제 결과
     */
    int deleteBoard(BoardDelDTO boardDelDTO);

    /**
     * 게시글 좋아요 조회
     *
     * @param  boardId : 게시글 식별 값
     * @return int     : 좋아요 수
     */
    int selectBoardLikeCnt(long boardId);

    /**
     * 좋아요 여부 조회
     *
     * @param  boardLikeDTO : 게시글 좋아요 정보
     * @return Integer      : 조회 게시글 좋아요 식별 값
     *                        (null 일 수 있어 wrapper 클래스 사용)
     */
    Integer selectBoardLike(BoardLikeDTO boardLikeDTO);

    /**
     * 게시글 좋아요 추가
     *
     * @param  boardLikeDTO : 게시글 좋아요 정보
     * @return int          : 저장 결과
     */
    int insertBoardLike(BoardLikeDTO boardLikeDTO);

    /**
     * 게시글 좋아요 삭제
     *
     * @param  id  : 게시글 좋아요 식별 값
     * @return int : 삭제 결과
     */
    int deleteBoardLike(long id);
}
