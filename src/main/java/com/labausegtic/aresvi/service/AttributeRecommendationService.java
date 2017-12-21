package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.domain.Attribute;
import com.labausegtic.aresvi.domain.AttributeRecommendation;
import com.labausegtic.aresvi.service.dto.AttributeRecommendationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * Service Interface for managing AttributeRecommendation.
 */
public interface AttributeRecommendationService {

    /**
     * Save a attributeRecommendation.
     *
     * @param attributeRecommendationDTO the entity to save
     * @return the persisted entity
     */
    AttributeRecommendationDTO save(AttributeRecommendationDTO attributeRecommendationDTO);

    /**
     *  Get all the attributeRecommendations.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<AttributeRecommendationDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" attributeRecommendation.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    AttributeRecommendationDTO findOne(Long id);

    Set<AttributeRecommendationDTO> findAllByCategoryAttrRecom_Id(Long categoryAttrRecom_id);

    /**
     *  Delete the "id" attributeRecommendation.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    List<AttributeRecommendation> findAllForTraceabilityAuditIdAndAttributeInAndImplementedIsFalse(Long traceability_id, List<Attribute> attributeList);
}
