package com.sprint.nangpa.service;

import com.sprint.nangpa.dto.recipe.*;
import com.sprint.nangpa.mapper.RecipeMapper;
import com.sprint.nangpa.model.RecipeCrse;
import com.sprint.nangpa.model.RecipeInfo;
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
     * @param   recipeSerData : 조회 정보
     * @return  RecipeListDTO : 재료가 포함된 레시피 목록
     */
    public RecipeListDTO getRecipeListByContainIrdntNm(RecipeSerByIrdntNmDTO recipeSerData){
        RecipeListDTO res = new RecipeListDTO();

        // 필터 정보 조회
        FilterInfoDTO filterInfo = new FilterInfoDTO();
        filterInfo.setLevel_nm(recipeMapper.selectLevelNm());
        filterInfo.setNations_nm(recipeMapper.selectNationNm());

        res.setFilterInfo(filterInfo);

        // 레시피 목록 조회
        List<RecipeInfoDTO> recipeInfos = recipeMapper.selectRecipeListContainIrdntNm(recipeSerData);

        // 레시피 별 재료 목록 조회
        for (RecipeInfoDTO recipeInfo : recipeInfos) {
            recipeInfo.setIrdnts(recipeMapper.selectRecipeIrdntByRecipeId(recipeInfo.getRecipeId()));
        }

        res.setRecipeInfos(recipeInfos);

        return res;
    }

    /**
     * 레시피 상세정보 조회
     *
     * @param   recipeId        : 레시피 아이디
     * @return  RecipeDetailDTO : 레시피(기본정보, 과정정보, 재료정보)
     */
    public RecipeDetailDTO getRecipeDetail(long recipeId) {
        RecipeDetailDTO recipeDetailDTO = new RecipeDetailDTO();

        // 레시피 기본정보 세팅
        RecipeInfo recipeInfo = recipeMapper.selectRecipeInfoByRecipeId(recipeId);
        recipeDetailDTO.setRecipeInfo(recipeInfo);

        // 레시피 과정정보 세팅
        List<RecipeCrse> findRecipeCrses = recipeMapper.selectRecipeCrseByRecipeId(recipeId);
        recipeDetailDTO.setRecipeCrses(findRecipeCrses);

        // 레시피 재료정보 세팅
        List<IrdntInfoDTO> findRecipeIrdnts = recipeMapper.selectRecipeIrdntByRecipeId(recipeId);
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

    /**
     * 레시피 좋아요 추가/삭제
     *
     * @param  recipeLikeDTO : 레시피 좋아요 정보
     * @return int           : 레시피 좋아요 수
     */
    @Transactional(rollbackFor = Exception.class)
    public int changeRecipeLike(RecipeLikeDTO recipeLikeDTO) {

        // 저장된 좋아요 조회
        int cnt = recipeMapper.selectRecipeLike(recipeLikeDTO);

        int res;
        if(cnt == 0){       // 저장된 좋아요가 없으면 추가
            res = recipeMapper.insertRecipeLike(recipeLikeDTO);
        }else{
            res = recipeMapper.deleteRecipeLike(recipeLikeDTO);
        }

        if(res < 1){
            throw new RuntimeException("좋아요 실패!!");
        }

        return recipeMapper.selectRecipeLikeCnt(recipeLikeDTO.getRecipeId());
    }
}
