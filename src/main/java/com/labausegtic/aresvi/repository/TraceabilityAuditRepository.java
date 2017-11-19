package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.Company;
import com.labausegtic.aresvi.domain.TraceabilityAudit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.util.Set;


/**
 * Spring Data JPA repository for the TraceabilityAudit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TraceabilityAuditRepository extends JpaRepository<TraceabilityAudit, Long> {

    Set<TraceabilityAudit> findByCompanyIdOrderByFinishedDateDesc(long company_id);

    Page<TraceabilityAudit> findAllByStatus(Pageable pageable, String status);
    List<TraceabilityAudit> findAllByStatus(String status);

    Page<TraceabilityAudit> findAllByStatusAndCompanyIn(Pageable pageable, String status, Set<Company> companySet);

    Page<TraceabilityAudit> findAllByCompanyId(Pageable pageable, Long company_id);
    Page<TraceabilityAudit> findAllByCategory(Pageable pageable, String category);

    Page<TraceabilityAudit> findAllByCompanyIdAndStatus(Pageable pageable, Long company_id, String status);
    Page<TraceabilityAudit> findAllByCategoryAndStatus(Pageable pageable, String category, String status);
    Page<TraceabilityAudit> findAllByCategoryAndStatusAndCompanyIn(Pageable pageable, String category, String status, Set<Company> companySet);

    Page<TraceabilityAudit> findAllByCompanyIdAndCategory(Pageable pageable, Long company_id, String category);
    Page<TraceabilityAudit> findAllByCompanyIdAndCategoryAndStatus(Pageable pageable, Long company_id, String category, String status);

    Set<TraceabilityAudit> findFirst2ByCompanyIdAndStatusOrderByFinishedDateDesc(Long company_id, String status);

}
