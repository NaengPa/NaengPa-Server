package com.sprint.nangpa.repository;

import com.sprint.nangpa.model.RecipeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 레시피 기본정보 데이터 관리 Repository
 */
@Repository
public interface RecipeInfoRepository extends JpaRepository<RecipeInfo, Long> {
}
