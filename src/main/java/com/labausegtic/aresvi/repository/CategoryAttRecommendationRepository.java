package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.CategoryAttRecommendation;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CategoryAttRecommendation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoryAttRecommendationRepository extends JpaRepository<CategoryAttRecommendation, Long> {

}
