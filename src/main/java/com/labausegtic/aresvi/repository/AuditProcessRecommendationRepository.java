package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.AuditProcessRecommendation;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.Set;


/**
 * Spring Data JPA repository for the AuditProcessRecommendation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuditProcessRecommendationRepository extends JpaRepository<AuditProcessRecommendation, Long> {

    Set<AuditProcessRecommendation> findAllByRecommendation_Id(Long recommendation_id);

    AuditProcessRecommendation findByRecommendation_IdAndAuditProcessId(Long recommendation_id, Long process_id);
}
