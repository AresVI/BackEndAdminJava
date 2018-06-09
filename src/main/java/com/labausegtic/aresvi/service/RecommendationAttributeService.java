package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.RecommendationAttributeDTO;
import java.util.List;

/**
 * Service Interface for managing RecommendationAttribute.
 */
public interface RecommendationAttributeService {

    /**
     * Save a recommendationAttribute.
     *
     * @param recommendationAttributeDTO the entity to save
     * @return the persisted entity
     */
    RecommendationAttributeDTO save(RecommendationAttributeDTO recommendationAttributeDTO);

    /**
     *  Get all the recommendationAttributes.
     *
     *  @return the list of entities
     */
    List<RecommendationAttributeDTO> findAll();

    /**
     *  Get the "id" recommendationAttribute.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    RecommendationAttributeDTO findOne(Long id);

    /**
     *  Delete the "id" recommendationAttribute.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
