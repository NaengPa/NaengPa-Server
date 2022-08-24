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

        int boardId = boardMapper.inserBoard(boardRegDTO);

        if(boardId != 0) {
            // 이미지 저장
            final String base64Prefix = "data:image/png;base64";

            for(String img : boardInfoDTO.getImgs()){
                String base64Url = String.valueOf(img);

                if(base64Url.startsWith(base64Prefix)){
                    byte[] imgArr = Base64.getDecoder().decode(base64Url.substring(base64Prefix.length()));

                    BoardImg boardImg = new BoardImg();
                    boardImg.setBoardId(boardId);
                    boardImg.setImg(imgArr);

                    int res = boardMapper.insertMoardImg(boardImg);

                    if(res != 1){
                        throw new RuntimeException("이미지 저장에 실패했습니다.");
                    }
                } else {
                    throw new RuntimeException("잘못 된 이미지 형식입니다.");
                }
            }
        } else {
            return false;
        }

        return true;
    }
}
