package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.Recommendation;
import com.labausegtic.aresvi.domain.TraceabilityAudit;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.util.Set;


/**
 * Spring Data JPA repository for the Recommendation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    Set<Recommendation> findAllByTraceabilityAudit_Id(Long traceabilityAudit_Id);

    List<Recommendation> findAllByTraceabilityAuditIn(List<TraceabilityAudit> traceabilityAuditList);

}
