package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.TraceAudit;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the TraceAudit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TraceAuditRepository extends JpaRepository<TraceAudit, Long> {
    List<TraceAudit> findAllByTraceabilityAuditId(Long traceabilityAuditId);
}
