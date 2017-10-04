package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.CategoryAttributeRecommendationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing CategoryAttributeRecommendation.
 */
public interface CategoryAttributeRecommendationService {

    /**
     * Save a categoryAttributeRecommendation.
     *
     * @param categoryAttributeRecommendationDTO the entity to save
     * @return the persisted entity
     */
    CategoryAttributeRecommendationDTO save(CategoryAttributeRecommendationDTO categoryAttributeRecommendationDTO);

    /**
     *  Get all the categoryAttributeRecommendations.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<CategoryAttributeRecommendationDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" categoryAttributeRecommendation.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CategoryAttributeRecommendationDTO findOne(Long id);

    /**
     *  Delete the "id" categoryAttributeRecommendation.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
