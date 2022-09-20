package com.sprint.nangpa.controller;

import com.sprint.nangpa.dto.recipe.*;
import com.sprint.nangpa.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 레시피 관리 Controller
 */
@RestController
@RequestMapping(value = "/recipe")
@RequiredArgsConstructor
public class RecipeController {

    /**
     * 레시피 관리 Service
     */
    private final RecipeService recipeService;

    /**
     * 검색하는데 필요한 모든 재료 조회
     *
     * @return List<String> : 디비에 저장된 모든 재료 목록(중복 제거)
     */
    @GetMapping("/getIrdnt")
    public List<IrdntNmDTO> getIrdnt(){
        return recipeService.getIrdntNmList();
    }

    /**
     * 검색 재료가 포함된 음식 레시피 조회
     *
     * @param  recipeSerData : 조회 정보
     * @return RecipeListDTO : 재료가 포함된 레시피 목록
     */
    @PostMapping("/getRecipeList")
    public RecipeListDTO getRecipeList(@RequestBody RecipeSerByIrdntNmDTO recipeSerData){
        return recipeService.getRecipeListByContainIrdntNm(recipeSerData);
    }

    /**
     * 레시피 상세보기 페이지 정보 조회
     *
     * @param   recipeUserKey   : 레시피 조회 정보
     * @return  RecipeDetailDTO : 레시피 정보
     */
    @GetMapping("/getRecipeDetail")
    public RecipeDetailDTO getRecipeDetail(@ModelAttribute RecipeUserKeyDTO recipeUserKey) {
        return recipeService.getRecipeDetail(recipeUserKey);
    }

    /**
     * 최근 조회한 레시피 기본정보 목록 조회
     *
     * @param   recipeIds          : 조회할 레시피 코드 목록
     * @return  List<CurRecipeDTO> : 목록에 포함된 레시피 기본정보 목록
     */
    @PostMapping("/getCurRecipeList")
    public List<CurRecipeDTO> getCurRecipeList(@RequestBody RecipeSerByRecipeIdDTO recipeIds) {
        return recipeService.getRecipeListByContainRecipeId(recipeIds.getRecipeIds());
    }

    /**
     * 게시글 좋아요 추가/삭제
     *
     * @param  recipeLikeDTO : 레시피 좋아요 정보
     * @return int           : 해당 레시피 좋아요 수
     */
    @PostMapping("/like")
    public int changeBoardLike(@RequestBody RecipeLikeDTO recipeLikeDTO){
        return recipeService.changeRecipeLike(recipeLikeDTO);
    }
}
