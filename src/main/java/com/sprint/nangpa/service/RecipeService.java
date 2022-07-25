package com.sprint.nangpa.service;

import com.sprint.nangpa.dto.RecipeCrseDTO;
import com.sprint.nangpa.dto.RecipeDetailDTO;
import com.sprint.nangpa.dto.RecipeIrdntDTO;
import com.sprint.nangpa.model.RecipeCrse;
import com.sprint.nangpa.model.RecipeInfo;
import com.sprint.nangpa.model.RecipeIrdnt;
import com.sprint.nangpa.repository.RecipeCrseRepository;
import com.sprint.nangpa.repository.RecipeInfoRepository;
import com.sprint.nangpa.repository.RecipeIrdntRepository;
import com.sprint.nangpa.repository.RecipeIrdntClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeIrdntClassRepository recipeIrdntClassRepository;

    /**
     * 레시피 기본정보 데이터 관리 Repository
     */
    private final RecipeInfoRepository recipeInfoRepository;
    /**
     * 레시피 과정정보 데이터 관리 Repository
     */
    private final RecipeCrseRepository recipeCrseRepository;
    /**
     * 레시피 재료정보 데이터 관리 Repository
     */
    private final RecipeIrdntRepository recipeIrdntRepository;

    public ArrayList<Map> getAllNoOverlapIrdnt(){
        return this.recipeIrdntClassRepository.getAllNoOverlapIrdnt();
    }

    /**
     * 검색 재료가 포함된 음식 레시피 조회
     *
     * @param   irdntNms         : 검색 재료 목록
     * @return  List<RecipeInfo> : 재료가 포함된 레시피 목록
     */
    public List<RecipeInfo> findByInIrdntNm(List<java.lang.String> irdntNms){
        List<RecipeIrdnt> findRecipeIrdnt = null;

        if(irdntNms.size() > 0){
            findRecipeIrdnt = recipeIrdntRepository.findByInIrdntNms(irdntNms);
        }else{
            findRecipeIrdnt = recipeIrdntRepository.findAll();
        }

        // 중복된 레시피 목록 제거
        Set<RecipeInfo> recipeInfos = new HashSet<>();
        for(int i=0; i<findRecipeIrdnt.size(); i++){
            recipeInfos.add(findRecipeIrdnt.get(i).getRecipeInfo());
        }

        return new ArrayList<RecipeInfo>(recipeInfos);
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
        RecipeInfo recipeInfo = recipeInfoRepository.findById(Long.valueOf(recipeId)).orElseGet(RecipeInfo::new);
        recipeDetailDTO.setRecipeInfo(recipeInfo);

        // 레시피 과정정보 세팅
        List<RecipeCrse> findRecipeCrses = recipeCrseRepository.findByRecipeInfo(recipeInfo);
        List<RecipeCrseDTO> recipeCrseDTOS = new ArrayList<>();
        for(RecipeCrse recipeCrse : findRecipeCrses){
            recipeCrseDTOS.add(new RecipeCrseDTO().setRecipeCrse(recipeCrse));
        }
        recipeDetailDTO.setRecipeCrses(recipeCrseDTOS);

        // 레시피 재료정보 세팅
        List<RecipeIrdnt> findRecipeIrdnts = recipeIrdntRepository.findByRecipeInfo(recipeInfo);
        List<RecipeIrdntDTO> recipeIrdntDTOS = new ArrayList<>();
        for(RecipeIrdnt recipeIrdnt : findRecipeIrdnts){
            recipeIrdntDTOS.add(new RecipeIrdntDTO().setRecipeCrse(recipeIrdnt));
        }
        recipeDetailDTO.setRecipeIrdnts(recipeIrdntDTOS);

        return recipeDetailDTO;
    }
}
