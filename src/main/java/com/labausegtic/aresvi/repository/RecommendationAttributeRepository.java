package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.RecommendationAttribute;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the RecommendationAttribute entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecommendationAttributeRepository extends JpaRepository<RecommendationAttribute, Long> {

}
