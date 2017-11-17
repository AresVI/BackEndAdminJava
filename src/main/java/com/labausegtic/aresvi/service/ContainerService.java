package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.domain.AuditProcess;
import com.labausegtic.aresvi.service.dto.ContainerCompleteDTO;
import com.labausegtic.aresvi.service.dto.ContainerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * Service Interface for managing Container.
 */
public interface ContainerService {

    /**
     * Save a container.
     *
     * @param containerDTO the entity to save
     * @return the persisted entity
     */
    ContainerDTO save(ContainerDTO containerDTO);

    /**
     *  Get all the containers.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ContainerDTO> findAll(Pageable pageable);

    Set<ContainerCompleteDTO> findAllByAuditProcess_Id(Long auditProcessId);

    /**
     *  Get the "id" container.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ContainerDTO findOne(Long id);

    /**
     *  Delete the "id" container.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
