package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.CancelationTraceabilityAudit;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the CancelationTraceabilityAudit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CancelationTraceabilityAuditRepository extends JpaRepository<CancelationTraceabilityAudit, Long> {

    @Query("select cancelation_traceability_audit from CancelationTraceabilityAudit cancelation_traceability_audit where cancelation_traceability_audit.user.login = ?#{principal.username}")
    List<CancelationTraceabilityAudit> findByUserIsCurrentUser();

}
