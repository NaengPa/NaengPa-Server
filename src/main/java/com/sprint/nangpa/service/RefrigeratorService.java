package com.sprint.nangpa.service;

import com.sprint.nangpa.dto.refrigerator.RefrigeratorInfoDTO;
import com.sprint.nangpa.mapper.RefrigeratorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 냉장고 관리 Service
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class RefrigeratorService {

    /**
     * 냉장고 관리 Mapper
     */
    private final RefrigeratorMapper refrigeratorMapper;

    /**
     * 개인 냉장고 재료 저장
     * (사용자 냉장고 데이터 삭제 후 다시 저장)
     *
     * @param  refrigerator : 냉장고 재료 저장 정보
     * @return boolean      : 저장 결과
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRefrigerator(RefrigeratorInfoDTO refrigerator) {
        // 저장된 냉장고 데이터 삭제
        int res = refrigeratorMapper.deleteRefrigeratorByEmail(refrigerator.getEmail());

        // 저장하는 냉장고 재료가 있는 경우
        if(refrigerator.getIrdntNms().size() > 0){
            res = refrigeratorMapper.insertRefrigerator(refrigerator);
        }

        return res > 0;
    }

    /**
     * 사용자 냉장고 재료 목록 조회
     *
     * @param  email        : 사용자 아이디
     * @return List<String> : 사용자 냉장고 재료 목록 조회
     */
    public List<String> selectRefrigeratorByEmail(String email) {
        return refrigeratorMapper.selectRefrigeratorByEmail(email);
    }
}
