package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.domain.TraceabilityAudit;
import com.labausegtic.aresvi.service.dto.TraceabilityAuditDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

/**
 * Service Interface for managing TraceabilityAudit.
 */
public interface TraceabilityAuditService {

    /**
     * Save a traceabilityAudit.
     *
     * @param traceabilityAuditDTO the entity to save
     * @return the persisted entity
     */
    TraceabilityAuditDTO save(TraceabilityAuditDTO traceabilityAuditDTO);

    /**
     *  Get all the traceabilityAudits.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<TraceabilityAuditDTO> findAll(Pageable pageable);

    Page<TraceabilityAuditDTO> findAllByStatus(Pageable pageable, String status);

    Page<TraceabilityAuditDTO> findAllFinishedByCategoryAndCompany(Pageable pageable, String category, Long company_id);

    /**
     *  Get the "id" traceabilityAudit.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    TraceabilityAuditDTO findOne(Long id);

    TraceabilityAuditDTO findAllLastByCompanyId(Long company_id);

    /**
     *  Delete the "id" traceabilityAudit.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    TraceabilityAuditDTO startTraceabilityAudit(Long id);

    TraceabilityAuditDTO finishTraceabilityAudit(Long id);

    Set<TraceabilityAuditDTO> findLastTwoTraceabilityAuditsFinished(Long id);
}
