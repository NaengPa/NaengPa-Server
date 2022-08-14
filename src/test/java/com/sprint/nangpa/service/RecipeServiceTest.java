package com.sprint.nangpa.service;

import com.sprint.nangpa.dto.recipe.CurRecipeDTO;
import com.sprint.nangpa.dto.recipe.IrdntNmDTO;
import com.sprint.nangpa.dto.recipe.RecipeDetailDTO;
import com.sprint.nangpa.dto.recipe.RecipeListInfoDTO;
import com.sprint.nangpa.mapper.RecipeMapper;
import com.sprint.nangpa.model.RecipeInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * 단위 테스트
 */
@SpringBootTest
public class RecipeServiceTest {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeMapper recipeMapper;

    /**
     * 재료명 목록 조회
     */
    @Test
    public void given_whenGetIrdntNmList_thenEqualsSizeSuccess() {
        List<IrdntNmDTO> findData = recipeService.getIrdntNmList();

        assertThat(findData.size()).isEqualTo(217);
    }

    /**
     * 검색 재료가 포함된 음식 레시피 조회
     */
    @Test
    public void givenStringList_whengetRecipeListByContainIrdntNm_thenRecipeListInfoListSuccess()  {
        List<String> irdntNms = new ArrayList<>();
        irdntNms.add("밥");
        irdntNms.add("계란");
        irdntNms.add("간장");
        irdntNms.add("참기름");

        List<RecipeListInfoDTO> findData = recipeService.getRecipeListByContainIrdntNm(irdntNms);

        assertThat(findData.size()).isEqualTo(32);
    }

    /**
     * 레시피 상세정보 조회
     */
    @Test
    public void givenRecipeId_whenGetRecipeDetail_thenRecipeDetailInfoSuccess()  {
        long recipeId = 1;

        RecipeDetailDTO findData = recipeService.getRecipeDetail(recipeId);

        assertThat(findData.getRecipeInfo().getRecipeId()).isEqualTo(1);
        assertThat(findData.getRecipeCrses().size()).isEqualTo(5);
        assertThat(findData.getRecipeIrdnts().size()).isEqualTo(17);
    }

    /**
     * 최근 레시피 기본정보 조회
     */
    @Test
    public void givenRecipeIdArray_whenGetRecipeListByContainRecipeId_thenCurrentRecipeInfoListSuccess()  {
        Long[] recipeIds = {1L, 11L, 49L};
        List<Long> recipeIdList = new ArrayList<>();
        Collections.addAll(recipeIdList, recipeIds);

        List<CurRecipeDTO> findData = recipeService.getRecipeListByContainRecipeId(recipeIdList);

        for(int i=0; i<3; i++) {
            RecipeInfo recipeInfo = recipeMapper.selectRecipeInfoByRecipeId(recipeIds[i]);

            assertThat(findData.get(i).getRecipeId()).isEqualTo(recipeInfo.getRecipeId());
        }
    }
}
