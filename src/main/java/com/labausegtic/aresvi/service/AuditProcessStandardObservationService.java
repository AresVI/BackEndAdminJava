package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.AuditProcessStandardObservationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing AuditProcessStandardObservation.
 */
public interface AuditProcessStandardObservationService {

    /**
     * Save a auditProcessStandardObservation.
     *
     * @param auditProcessStandardObservationDTO the entity to save
     * @return the persisted entity
     */
    AuditProcessStandardObservationDTO save(AuditProcessStandardObservationDTO auditProcessStandardObservationDTO);

    /**
     *  Get all the auditProcessStandardObservations.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<AuditProcessStandardObservationDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" auditProcessStandardObservation.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    AuditProcessStandardObservationDTO findOne(Long id);

    /**
     *  Delete the "id" auditProcessStandardObservation.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
