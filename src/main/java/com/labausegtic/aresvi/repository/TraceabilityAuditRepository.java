package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.TraceabilityAudit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.Set;


/**
 * Spring Data JPA repository for the TraceabilityAudit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TraceabilityAuditRepository extends JpaRepository<TraceabilityAudit, Long> {

    Set<TraceabilityAudit> findByCompanyIdOrderByCreationDateDesc(long company_id);

    Page<TraceabilityAudit> findAllByStatus(Pageable pageable, String status);

    Page<TraceabilityAudit> findAllByCompanyId(Pageable pageable, Long company_id);
    Page<TraceabilityAudit> findAllByCategory(Pageable pageable, String category);

    Page<TraceabilityAudit> findAllByCompanyIdAndStatus(Pageable pageable, Long company_id, String status);
    Page<TraceabilityAudit> findAllByCategoryAndStatus(Pageable pageable, String category, String status);

    Page<TraceabilityAudit> findAllByCompanyIdAndCategory(Pageable pageable, Long company_id, String category);
    Page<TraceabilityAudit> findAllByCompanyIdAndCategoryAndStatus(Pageable pageable, Long company_id, String category, String status);

    Set<TraceabilityAudit> findFirst2ByCompanyIdAndStatusOrderByCreationDateDesc(Long company_id, String status);

}
