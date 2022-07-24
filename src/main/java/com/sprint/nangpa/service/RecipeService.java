package com.sprint.nangpa.service;

import com.sprint.nangpa.model.RecipeInfo;
import com.sprint.nangpa.model.RecipeIrdnt;
import com.sprint.nangpa.repository.RecipeIrdntRepository;
import com.sprint.nangpa.repository.RecipeIrdntClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeIrdntClassRepository recipeIrdntClassRepository;

    /**
     * 레시피 재료정보 데이터 관리 Repository
     */
    private final RecipeIrdntRepository recipeIrdntRepository;

    public List<String> getAllNoOverlapIrdnt(){
        return this.recipeIrdntClassRepository.getAllNoOverlapIrdnt();
    }

    /**
     * 검색 재료가 포함된 음식 레시피 조회
     *
     * @param   irdntNms         : 검색 재료 목록
     * @return  List<RecipeInfo> : 재료가 포함된 레시피 목록
     */
    public List<RecipeInfo> findByInIrdntNm(List<String> irdntNms){
        List<RecipeIrdnt> byInIrdntNms = recipeIrdntRepository.findByInIrdntNms(irdntNms);

        // 중복된 레시피 목록 제거
        Set<RecipeInfo> recipeInfos = new HashSet<>();
        for(int i=0; i<byInIrdntNms.size(); i++){
            recipeInfos.add(byInIrdntNms.get(i).getRecipeInfo());
        }

        return new ArrayList<RecipeInfo>(recipeInfos);
    }

}
