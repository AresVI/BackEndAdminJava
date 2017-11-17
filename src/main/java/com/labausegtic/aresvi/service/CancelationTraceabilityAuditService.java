package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.CancelationTraceabilityAuditDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing CancelationTraceabilityAudit.
 */
public interface CancelationTraceabilityAuditService {

    /**
     * Save a cancelationTraceabilityAudit.
     *
     * @param cancelationTraceabilityAuditDTO the entity to save
     * @return the persisted entity
     */
    CancelationTraceabilityAuditDTO save(CancelationTraceabilityAuditDTO cancelationTraceabilityAuditDTO);

    /**
     *  Get all the cancelationTraceabilityAudits.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<CancelationTraceabilityAuditDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" cancelationTraceabilityAudit.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CancelationTraceabilityAuditDTO findOne(Long id);

    /**
     *  Delete the "id" cancelationTraceabilityAudit.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
