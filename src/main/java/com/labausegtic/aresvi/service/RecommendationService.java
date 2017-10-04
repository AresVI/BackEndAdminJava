package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.RecommendationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Recommendation.
 */
public interface RecommendationService {

    /**
     * Save a recommendation.
     *
     * @param recommendationDTO the entity to save
     * @return the persisted entity
     */
    RecommendationDTO save(RecommendationDTO recommendationDTO);

    /**
     *  Get all the recommendations.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<RecommendationDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" recommendation.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    RecommendationDTO findOne(Long id);

    /**
     *  Delete the "id" recommendation.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
