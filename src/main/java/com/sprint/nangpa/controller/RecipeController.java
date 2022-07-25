package com.sprint.nangpa.controller;


import com.sprint.nangpa.dto.RecipeDetailDTO;
import com.sprint.nangpa.dto.RecipeSerDTO;
import com.sprint.nangpa.model.RecipeInfo;
import com.sprint.nangpa.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    /**
     * 검색하는데 필요한 모든 재료 조회
     *
     * @return List<String> : 디비에 저장된 모든 재료 목록
     */
    @GetMapping("/getIrdnt")
    public List<String> getIrdnt(){
        return recipeService.getAllNoOverlapIrdnt();
    }

    /**
     * 검색 재료가 포함된 음식 레시피 조회
     *
     * @param   dto              : 재료 목록
     * @return  List<RecipeInfo> : 재료가 포함된 레시피 목록
     */
    @PostMapping("/getRecipeList")
    public List<RecipeInfo> getRecipeList(@RequestBody RecipeSerDTO dto){
        return recipeService.findByInIrdntNm(dto.getIrdntNms());
    }

    /**
     * 레시피 상세보기 페이지 정보 조회
     *
     * @param   recipeId        : 조회 레시피 아이디
     * @return  RecipeDetailDTO : 레시피 정보
     */
    @GetMapping("/getRecipeDetail/{id}")
    public RecipeDetailDTO getRecipeDetail(@PathVariable(value = "id") String recipeId) {
        return recipeService.getRecipeDetail(recipeId);
    }
}
