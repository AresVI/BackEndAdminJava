package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.AttributeRecommendation;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.Set;


/**
 * Spring Data JPA repository for the AttributeRecommendation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttributeRecommendationRepository extends JpaRepository<AttributeRecommendation, Long> {

    Set<AttributeRecommendation> findAllByCategoryAttrRecom_Id(Long categoryAttrRecom_id);

}
