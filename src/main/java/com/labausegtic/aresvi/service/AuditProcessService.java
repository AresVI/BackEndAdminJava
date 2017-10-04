package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.AuditProcessDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing AuditProcess.
 */
public interface AuditProcessService {

    /**
     * Save a auditProcess.
     *
     * @param auditProcessDTO the entity to save
     * @return the persisted entity
     */
    AuditProcessDTO save(AuditProcessDTO auditProcessDTO);

    /**
     *  Get all the auditProcesses.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<AuditProcessDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" auditProcess.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    AuditProcessDTO findOne(Long id);

    /**
     *  Delete the "id" auditProcess.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
