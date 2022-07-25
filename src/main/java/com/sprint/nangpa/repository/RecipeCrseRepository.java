package com.sprint.nangpa.repository;

import com.sprint.nangpa.model.RecipeCrse;
import com.sprint.nangpa.model.RecipeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 레시피 과정정보 데이터 관리 Repository
 */
@Repository
public interface RecipeCrseRepository extends JpaRepository<RecipeCrse, Long> {

    // 레시피 아이디로 레시피 과정정보 조회
    List<RecipeCrse> findByRecipeInfo(RecipeInfo recipeInfo);
}
