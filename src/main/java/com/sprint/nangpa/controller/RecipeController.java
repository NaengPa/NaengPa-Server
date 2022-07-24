package com.sprint.nangpa.controller;


import com.sprint.nangpa.model.RecipeInfo;
import com.sprint.nangpa.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    /**
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
     * @param   irdntNms         : 검색 재료 목록
     * @return  List<RecipeInfo> : 재료가 포함된 레시피 목록
     */
    @GetMapping("/getRecipeList")
    public List<RecipeInfo> getRecipeList(@RequestParam(value="irdntNms[]") List<String> irdntNms){
        return recipeService.findByInIrdntNm(irdntNms);
    }
}
