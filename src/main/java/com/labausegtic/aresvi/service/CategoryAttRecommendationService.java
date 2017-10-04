package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.CategoryAttRecommendationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing CategoryAttRecommendation.
 */
public interface CategoryAttRecommendationService {

    /**
     * Save a categoryAttRecommendation.
     *
     * @param categoryAttRecommendationDTO the entity to save
     * @return the persisted entity
     */
    CategoryAttRecommendationDTO save(CategoryAttRecommendationDTO categoryAttRecommendationDTO);

    /**
     *  Get all the categoryAttRecommendations.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<CategoryAttRecommendationDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" categoryAttRecommendation.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CategoryAttRecommendationDTO findOne(Long id);

    /**
     *  Delete the "id" categoryAttRecommendation.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
