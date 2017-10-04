package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.WeightingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Weighting.
 */
public interface WeightingService {

    /**
     * Save a weighting.
     *
     * @param weightingDTO the entity to save
     * @return the persisted entity
     */
    WeightingDTO save(WeightingDTO weightingDTO);

    /**
     *  Get all the weightings.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<WeightingDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" weighting.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    WeightingDTO findOne(Long id);

    /**
     *  Delete the "id" weighting.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
