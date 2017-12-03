package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.AuditTaskStandardObservationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing AuditTaskStandardObservation.
 */
public interface AuditTaskStandardObservationService {

    /**
     * Save a auditTaskStandardObservation.
     *
     * @param auditTaskStandardObservationDTO the entity to save
     * @return the persisted entity
     */
    AuditTaskStandardObservationDTO save(AuditTaskStandardObservationDTO auditTaskStandardObservationDTO);

    /**
     *  Get all the auditTaskStandardObservations.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<AuditTaskStandardObservationDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" auditTaskStandardObservation.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    AuditTaskStandardObservationDTO findOne(Long id);

    /**
     *  Delete the "id" auditTaskStandardObservation.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
