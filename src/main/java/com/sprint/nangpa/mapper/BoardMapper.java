package com.sprint.nangpa.mapper;

import com.sprint.nangpa.dto.board.BoardRegDTO;
import com.sprint.nangpa.model.BoardImg;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

    /**
     * 게시글 저장
     *
     * @param  boardRegDTO : 게시글 정보
     * @return int         : 저장된 행의 수
     */
    int inserBoard(BoardRegDTO boardRegDTO);

    /**
     * 게시글 이미지 저장
     *
     * @param  boardImg : 게시글 이미지 정보
     * @return int      : 저장된 행의 수
     */
    int insertMoardImg(BoardImg boardImg);
}
