package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.domain.CategoryAttrRecommendation;
import com.labausegtic.aresvi.service.dto.CategoryAttrRecommendationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * Service Interface for managing CategoryAttrRecommendation.
 */
public interface CategoryAttrRecommendationService {

    /**
     * Save a categoryAttRecommendation.
     *
     * @param categoryAttrRecommendationDTO the entity to save
     * @return the persisted entity
     */
    CategoryAttrRecommendationDTO save(CategoryAttrRecommendationDTO categoryAttrRecommendationDTO);

    /**
     *  Get all the categoryAttRecommendations.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<CategoryAttrRecommendationDTO> findAll(Pageable pageable);

    List<CategoryAttrRecommendationDTO> findAllByAuditTaskRecom_Id(Long auditTaskRecom_id);

    /**
     *  Get the "id" categoryAttRecommendation.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CategoryAttrRecommendationDTO findOne(Long id);

    /**
     *  Delete the "id" categoryAttRecommendation.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
