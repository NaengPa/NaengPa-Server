package com.sprint.nangpa.repository;

import com.sprint.nangpa.model.RecipeIrdnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 레시피 재료정보 데이터 관리 Repository
 */
@Repository
public interface RecipeIrdntRepository extends JpaRepository<RecipeIrdnt, Long> {

    // 재료 목록으로 레시피 목록 검색
    @Query(nativeQuery = true, value = "SELECT * FROM recipe_irdnt as r WHERE r.irdnt_nm IN (:irdntNms)")
    List<RecipeIrdnt> findByInIrdntNms(@Param ("irdntNms") List<String> irdntNms);
}
