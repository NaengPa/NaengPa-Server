package com.sprint.nangpa.mapper;

import com.sprint.nangpa.dto.CurRecipeDTO;
import com.sprint.nangpa.dto.IrdntNmDTO;
import com.sprint.nangpa.dto.RecipeListInfoDTO;
import com.sprint.nangpa.model.RecipeCrse;
import com.sprint.nangpa.model.RecipeInfo;
import com.sprint.nangpa.model.RecipeIrdnt;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecipeMapper {
    List<IrdntNmDTO> selectDistinctRecipeIrdntList();

    List<RecipeListInfoDTO> selectRecipeListContainIrdntNm(List<String> irdntNms);

    RecipeInfo selectRecipeInfoByRecipeId(String recipeId);

    List<RecipeCrse> selectRecipeCrseByRecipeId(String recipeId);

    List<RecipeIrdnt> selectRecipeIrdntByRecipeId(String recipeId);

    List<CurRecipeDTO> selectRecipeListContainRecipeId(List<Long> recipeIds);

}
