package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.AuditTaskCompleteDTO;
import com.labausegtic.aresvi.service.dto.AuditTaskDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

/**
 * Service Interface for managing AuditTask.
 */
public interface AuditTaskService {

    /**
     * Save a auditTask.
     *
     * @param auditTaskDTO the entity to save
     * @return the persisted entity
     */
    AuditTaskDTO save(AuditTaskDTO auditTaskDTO);

    /**
     *  Get all the auditTasks.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<AuditTaskDTO> findAll(Pageable pageable);

    Set<AuditTaskCompleteDTO> findAllByContainer_Id(Long  containerId);

    /**
     *  Get the "id" auditTask.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    AuditTaskDTO findOne(Long id);

    /**
     *  Delete the "id" auditTask.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
