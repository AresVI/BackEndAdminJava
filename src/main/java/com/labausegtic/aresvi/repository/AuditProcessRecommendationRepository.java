package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.AuditProcessRecommendation;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AuditProcessRecommendation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuditProcessRecommendationRepository extends JpaRepository<AuditProcessRecommendation, Long> {

}
