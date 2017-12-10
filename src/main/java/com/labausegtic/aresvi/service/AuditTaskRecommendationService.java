package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.AuditTaskRecommendationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * Service Interface for managing AuditTaskRecommendation.
 */
public interface AuditTaskRecommendationService {

    /**
     * Save a auditTaskRecommendation.
     *
     * @param auditTaskRecommendationDTO the entity to save
     * @return the persisted entity
     */
    AuditTaskRecommendationDTO save(AuditTaskRecommendationDTO auditTaskRecommendationDTO);

    /**
     *  Get all the auditTaskRecommendations.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<AuditTaskRecommendationDTO> findAll(Pageable pageable);

    List<AuditTaskRecommendationDTO> findAllByAuditProcessRecom_Id(Long auditProcessRecom_id);

    /**
     *  Get the "id" auditTaskRecommendation.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    AuditTaskRecommendationDTO findOne(Long id);

    /**
     *  Delete the "id" auditTaskRecommendation.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    AuditTaskRecommendationDTO findOneByBonitaBpmCaseIdAndAuditTaskId(Long bonita_bpm_case_id, Long audit_task_id);
}
