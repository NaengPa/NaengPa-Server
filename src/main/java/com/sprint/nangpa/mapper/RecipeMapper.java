package com.sprint.nangpa.mapper;

import com.sprint.nangpa.dto.CurRecipeDTO;
import com.sprint.nangpa.dto.IrdntInfoDTO;
import com.sprint.nangpa.dto.IrdntNmDTO;
import com.sprint.nangpa.dto.RecipeListInfoDTO;
import com.sprint.nangpa.model.RecipeCrse;
import com.sprint.nangpa.model.RecipeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecipeMapper {
    List<IrdntNmDTO> selectDistinctRecipeIrdntList();

    List<RecipeListInfoDTO> selectRecipeListContainIrdntNm(@Param("irdntNms") List<String> irdntNms);

    RecipeInfo selectRecipeInfoByRecipeId(long recipeId);

    List<RecipeCrse> selectRecipeCrseByRecipeId(long recipeId);

    List<IrdntInfoDTO> selectRecipeIrdntByRecipeId(long recipeId);

    List<CurRecipeDTO> selectRecipeListContainRecipeId(@Param("recipeIds") List<Long> recipeIds);
}
