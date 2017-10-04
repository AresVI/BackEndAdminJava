package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.TraceabilityAudit;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TraceabilityAudit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TraceabilityAuditRepository extends JpaRepository<TraceabilityAudit, Long> {

    TraceabilityAudit findLastByCompanyId(long company_id);

}
