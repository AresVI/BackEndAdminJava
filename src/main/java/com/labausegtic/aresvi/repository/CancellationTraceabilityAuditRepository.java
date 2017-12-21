package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.CancellationTraceabilityAudit;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the CancellationTraceabilityAudit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CancellationTraceabilityAuditRepository extends JpaRepository<CancellationTraceabilityAudit, Long> {

    @Query("select cancellation_traceability_audit from CancellationTraceabilityAudit cancellation_traceability_audit where cancellation_traceability_audit.user.login = ?#{principal.username}")
    List<CancellationTraceabilityAudit> findByUserIsCurrentUser();

}
