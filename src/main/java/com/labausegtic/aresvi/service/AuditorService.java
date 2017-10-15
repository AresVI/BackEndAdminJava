package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.domain.Auditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Auditor.
 */
public interface AuditorService {

    /**
     * Save a auditor.
     *
     * @param auditor the entity to save
     * @return the persisted entity
     */
    Auditor save(Auditor auditor) throws Exception;

    /**
     *  Get all the auditors.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Auditor> findAll(Pageable pageable);

    /**
     *  Get the "id" auditor.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Auditor findOne(Long id);

    /**
     *  Delete the "id" auditor.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
