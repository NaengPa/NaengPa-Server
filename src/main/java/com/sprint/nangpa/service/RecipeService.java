package com.sprint.nangpa.service;

import com.sprint.nangpa.dto.CurRecipeDTO;
import com.sprint.nangpa.dto.IrdntNmDTO;
import com.sprint.nangpa.dto.RecipeDetailDTO;
import com.sprint.nangpa.mapper.RecipeMapper;
import com.sprint.nangpa.model.RecipeCrse;
import com.sprint.nangpa.model.RecipeInfo;
import com.sprint.nangpa.model.RecipeIrdnt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 레시피 관리 Service
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
@RequiredArgsConstructor
public class RecipeService {

    /**
     * 레시피 데이터 관리 Mapper
     */
    private final RecipeMapper recipeMapper;

    /**
     * 음식 재료명 조회
     *
     * @return  List<IrdntNmDTO> : 재료명 목록
     */
    public List<IrdntNmDTO> getIrdntNmList(){
        return recipeMapper.selectDistinctRecipeIrdntList();
    }

    /**
     * 검색 재료가 포함된 음식 레시피 조회
     *
     * @param   irdntNms         : 검색 재료 목록
     * @return  List<RecipeInfo> : 재료가 포함된 레시피 목록
     */
    public List<RecipeInfo> getRecipeListByContainIrdntNm(List<String> irdntNms){
        return recipeMapper.selectRecipeListContainIrdntNm(irdntNms);
    }

    /**
     * 레시피 상세정보 조회
     *
     * @param   recipeId        : 레시피 아이디
     * @return  RecipeDetailDTO : 레시피(기본정보, 과정정보, 재료정보)
     */
    public RecipeDetailDTO getRecipeDetail(String recipeId) {
        RecipeDetailDTO recipeDetailDTO = new RecipeDetailDTO();

        // 레시피 기본정보 세팅
        RecipeInfo recipeInfo = recipeMapper.selectRecipeInfoByRecipeId(recipeId);
        recipeDetailDTO.setRecipeInfo(recipeInfo);

        // 레시피 과정정보 세팅
        List<RecipeCrse> findRecipeCrses = recipeMapper.selectRecipeCrseByRecipeId(recipeId);
        recipeDetailDTO.setRecipeCrses(findRecipeCrses);

        // 레시피 재료정보 세팅
        List<RecipeIrdnt> findRecipeIrdnts = recipeMapper.selectRecipeIrdntByRecipeId(recipeId);
        recipeDetailDTO.setRecipeIrdnts(findRecipeIrdnts);

        return recipeDetailDTO;
    }

    /**
     * 레시피 아이디 목록에 포함된 레시피 기본정보 조회
     *
     * @param   recipeIds          : 레시피 아이디 목록
     * @return  List<CurRecipeDTO> : 목록에 포함된 레시피 기본정보
     */
    public List<CurRecipeDTO> getRecipeListByContainRecipeId(List<Long> recipeIds) {
        return recipeMapper.selectRecipeListContainRecipeId(recipeIds);
    }
}
