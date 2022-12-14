package com.sprint.nangpa.controller;

import com.sprint.nangpa.dto.refrigerator.RefrigeratorInfoDTO;
import com.sprint.nangpa.service.RefrigeratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 냉장고 관리 Controller
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/refrigerator")
public class RefrigeratorController {

    /**
     * 냉장고 관리 Service
     */
    private final RefrigeratorService refrigeratorService;

    /**
     * 개인 냉장고 재료 저장
     *
     * @param  refrigerator : 냉장고 재료 저장 정보
     * @return boolean      : 저장 결과
     */
    @PostMapping
    public boolean saveRefrigerator(@RequestBody RefrigeratorInfoDTO refrigerator) {
        return refrigeratorService.saveRefrigerator(refrigerator);
    }

    /**
     * 사용자 냉장고 재료 목록 조회
     *
     * @param  email        : 사용자 아이디
     * @return List<String> : 사용자 냉장고 재료 목록 조회
     */
    @GetMapping("/{email}")
    public List<String> findRefrigeratorByEmail(@PathVariable String email) {
        return refrigeratorService.selectRefrigeratorByEmail(email);
    }
}
