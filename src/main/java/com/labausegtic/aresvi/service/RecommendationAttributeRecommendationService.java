package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.RecommendationAttributeRecommendationDTO;
import java.util.List;

/**
 * Service Interface for managing RecommendationAttributeRecommendation.
 */
public interface RecommendationAttributeRecommendationService {

    /**
     * Save a recommendationAttributeRecommendation.
     *
     * @param recommendationAttributeRecommendationDTO the entity to save
     * @return the persisted entity
     */
    RecommendationAttributeRecommendationDTO save(RecommendationAttributeRecommendationDTO recommendationAttributeRecommendationDTO);

    /**
     *  Get all the recommendationAttributeRecommendations.
     *
     *  @return the list of entities
     */
    List<RecommendationAttributeRecommendationDTO> findAll();
    List<RecommendationAttributeRecommendationDTO> findAll(Long recommendation_id);

    /**
     *  Get the "id" recommendationAttributeRecommendation.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    RecommendationAttributeRecommendationDTO findOne(Long id);

    /**
     *  Delete the "id" recommendationAttributeRecommendation.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
