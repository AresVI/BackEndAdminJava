package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.CategoryAttrRecommendation;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the CategoryAttrRecommendation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoryAttrRecommendationRepository extends JpaRepository<CategoryAttrRecommendation, Long> {

    List<CategoryAttrRecommendation> findAllByAuditTaskRecom_Id(Long auditTaskRecom_id);

}
