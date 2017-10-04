package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.CategoryAttributeRecommendation;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CategoryAttributeRecommendation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoryAttributeRecommendationRepository extends JpaRepository<CategoryAttributeRecommendation, Long> {

}
