package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.TraceAuditDTO;
import java.util.List;

/**
 * Service Interface for managing TraceAudit.
 */
public interface TraceAuditService {

    /**
     * Save a traceAudit.
     *
     * @param traceAuditDTO the entity to save
     * @return the persisted entity
     */
    TraceAuditDTO save(TraceAuditDTO traceAuditDTO);

    /**
     *  Get all the traceAudits.
     *
     *  @return the list of entities
     */
    List<TraceAuditDTO> findAll(Long traceabilityAuditId);

    /**
     *  Get the "id" traceAudit.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    TraceAuditDTO findOne(Long id);

    /**
     *  Delete the "id" traceAudit.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
