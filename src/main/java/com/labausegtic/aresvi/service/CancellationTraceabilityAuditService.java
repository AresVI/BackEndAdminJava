package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.CancellationTraceabilityAuditDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing CancellationTraceabilityAudit.
 */
public interface CancellationTraceabilityAuditService {

    /**
     * Save a CancellationTraceabilityAudit.
     *
     * @param cancellationTraceabilityAuditDTO the entity to save
     * @return the persisted entity
     */
    CancellationTraceabilityAuditDTO save(CancellationTraceabilityAuditDTO cancellationTraceabilityAuditDTO);

    /**
     *  Get all the CancellationTraceabilityAudits.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<CancellationTraceabilityAuditDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" CancellationTraceabilityAudit.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CancellationTraceabilityAuditDTO findOne(Long id);

    /**
     *  Delete the "id" CancellationTraceabilityAudit.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    CancellationTraceabilityAuditDTO cancelTraceabilityAudit(CancellationTraceabilityAuditDTO cancellationTraceabilityAuditDTO);

}
