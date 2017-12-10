package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.AuditAttributeAnalysis;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AuditAttributeAnalysis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuditAttributeAnalysisRepository extends JpaRepository<AuditAttributeAnalysis, Long> {

    AuditAttributeAnalysis getAuditAttributeAnalysisByTraceabilityAuditId(Long traceabilityAuditId);

}
