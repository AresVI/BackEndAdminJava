package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.AuditProcessRecommendationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing AuditProcessRecommendation.
 */
public interface AuditProcessRecommendationService {

    /**
     * Save a auditProcessRecommendation.
     *
     * @param auditProcessRecommendationDTO the entity to save
     * @return the persisted entity
     */
    AuditProcessRecommendationDTO save(AuditProcessRecommendationDTO auditProcessRecommendationDTO);

    /**
     *  Get all the auditProcessRecommendations.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<AuditProcessRecommendationDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" auditProcessRecommendation.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    AuditProcessRecommendationDTO findOne(Long id);

    /**
     *  Delete the "id" auditProcessRecommendation.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
