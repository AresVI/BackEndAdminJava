package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.AuditProcessRecommendation;
import com.labausegtic.aresvi.domain.Recommendation;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.util.Set;


/**
 * Spring Data JPA repository for the AuditProcessRecommendation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuditProcessRecommendationRepository extends JpaRepository<AuditProcessRecommendation, Long> {

    Set<AuditProcessRecommendation> findAllByRecommendation_Id(Long recommendation_id);

    AuditProcessRecommendation findByRecommendation_IdAndAuditProcessId(Long recommendation_id, Long process_id);

    List<AuditProcessRecommendation> findByRecommendationInAndAuditProcessId(List<Recommendation> recommendationList, Long process_id);

    AuditProcessRecommendation findByBonitaBpmCaseId(Long bonitaBpmCaseId);

}
