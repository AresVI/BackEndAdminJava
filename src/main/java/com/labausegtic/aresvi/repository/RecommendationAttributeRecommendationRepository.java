package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.RecommendationAttributeRecommendation;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the RecommendationAttributeRecommendation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecommendationAttributeRecommendationRepository extends JpaRepository<RecommendationAttributeRecommendation, Long> {

}
