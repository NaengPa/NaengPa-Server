package com.sprint.nangpa.repository;

import com.sprint.nangpa.model.RecipeIrdnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 레시피 재료정보 데이터 관리 Repository
 */
@Repository
public interface RecipIrdntRepository extends JpaRepository<RecipeIrdnt, Long> {
}
