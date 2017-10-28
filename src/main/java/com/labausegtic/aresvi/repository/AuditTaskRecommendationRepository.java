package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.AuditTaskRecommendation;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.Set;


/**
 * Spring Data JPA repository for the AuditTaskRecommendation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuditTaskRecommendationRepository extends JpaRepository<AuditTaskRecommendation, Long> {

    Set<AuditTaskRecommendation> findAllByAuditProcessRecom_Id(Long auditProcess_Id);

}
