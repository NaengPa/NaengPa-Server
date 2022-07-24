package com.sprint.nangpa.repository;

import com.sprint.nangpa.model.RecipeCrse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 레시피 과정정보 데이터 관리 Repository
 */
@Repository
public interface RecipCrseRepository extends JpaRepository<RecipeCrse, Long> {
}
