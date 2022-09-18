package com.sprint.nangpa.mapper;

import com.sprint.nangpa.dto.recipe.*;
import com.sprint.nangpa.model.RecipeCrse;
import com.sprint.nangpa.model.RecipeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecipeMapper {

    /**
     * 레시피에 포함된 재료 조회(중복 제거)
     *
     * @return List<IrdntNmDTO> : 재료 목록
     */
    List<IrdntNmDTO> selectDistinctRecipeIrdntList();

    /**
     * 재료 목록이 포함된 레시피 목록 조회
     *
     * @param  recipeSerData           : 조회 정보
     * @return List<RecipeListInfoDTO> : 레시피 목록
     */
    List<RecipeInfoDTO> selectRecipeListContainIrdntNm(RecipeSerByIrdntNmDTO recipeSerData);

    /**
     * 레시피 정보 조회
     *
     * @param  recipeId   : 레시피 코드
     * @return RecipeInfo : 레시피 정보
     */
    RecipeInfo selectRecipeInfoByRecipeId(long recipeId);

    /**
     * 레시피 과정 목록 조회
    *
     * @param  recipeId         : 레시피 코드
     * @return List<RecipeCrse> : 레시피 과정 목록
     */
    List<RecipeCrse> selectRecipeCrseByRecipeId(long recipeId);

    /**
     * 레시피에 포함된 재료 목록 조회
     *
     * @param  recipeId           : 레시피 코드
     * @return List<IrdntInfoDTO> : 재료 목록
     */
    List<IrdntInfoDTO> selectRecipeIrdntByRecipeId(long recipeId);

    /**
     * 요청받은 레시피 코드에 포함되는 레시피 목록 조회
     *
     * @param  recipeIds          : 레시피 코드 목록
     * @return List<CurRecipeDTO> : 레시피 목록
    */
    List<CurRecipeDTO> selectRecipeListContainRecipeId(@Param("recipeIds") List<Long> recipeIds);

    /**
     * 음식 종류 조회
     *
     * @return List<String> : 음식 종류 목록
     */
    List<String> selectNationNm();

    /**
     * 음식 난이도 조회
     *
     * @return List<String> : 요리 난이도 목록
     */
    List<String> selectLevelNm();

    /**
     * 레시피 좋아요 조회
     *
     * @param  recipeId : 레시피 식별 값
     * @return int      : 좋아요 수
     */
    int selectRecipeLikeCnt(long recipeId);

    /**
     * 좋아요 여부 조회
     *
     * @param recipeLikeDTO : 레시피 좋아요 정보
     * @return int          : 조회 레시 좋아요 수
     */
    int selectRecipeLike(RecipeLikeDTO recipeLikeDTO);

    /**
     * 레시피 좋아요 추가
     *
     * @param  recipeLikeDTO : 레시피 좋아요 정보
     * @return int           : 저장 결과
     */
    int insertRecipeLike(RecipeLikeDTO recipeLikeDTO);

    /**
     * 레시피 좋아요 삭제
     *
     * @param  recipeLikeDTO : 레시피 좋아요 정보
     * @return int           : 삭제 결과
     */
    int deleteRecipeLike(RecipeLikeDTO recipeLikeDTO);
}
