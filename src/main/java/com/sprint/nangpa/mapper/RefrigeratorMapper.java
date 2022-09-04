package com.sprint.nangpa.mapper;

import com.sprint.nangpa.dto.refrigerator.RefrigeratorInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RefrigeratorMapper {

    /**
     * 사용자 냉장고 데이터 삭제
     *
     * @param  email : 사용자 이메일
     * @return int   : 삭제 데이터 수
     */
    int deleteRefrigeratorByEmail(String email);

    /**
     * 냉장고 데이터 추가
     *
     * @param  refrigerator : 냉장고 재료 저장 정보
     * @return int          : 저장 데이터 수
     */
    int insertRefrigerator(RefrigeratorInfoDTO refrigerator);

    /**
     * 사용자 냉장고 재료 조회
     *
     * @param  email        : 사용자 아이디
     * @return List<String> : 재료 목록
     */
    List<String> selectRefrigeratorByEmail(String email);

}
