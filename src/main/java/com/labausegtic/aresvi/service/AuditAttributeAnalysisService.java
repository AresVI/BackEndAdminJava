package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.AuditAttributeAnalysisDTO;
import java.util.List;

/**
 * Service Interface for managing AuditAttributeAnalysis.
 */
public interface AuditAttributeAnalysisService {

    /**
     * Save a auditAttributeAnalysis.
     *
     * @param auditAttributeAnalysisDTO the entity to save
     * @return the persisted entity
     */
    AuditAttributeAnalysisDTO save(AuditAttributeAnalysisDTO auditAttributeAnalysisDTO);

    /**
     *  Get all the auditAttributeAnalyses.
     *
     *  @return the list of entities
     */
    List<AuditAttributeAnalysisDTO> findAll();

    /**
     *  Get the "id" auditAttributeAnalysis.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    AuditAttributeAnalysisDTO findOne(Long id);

    /**
     *  Delete the "id" auditAttributeAnalysis.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    AuditAttributeAnalysisDTO getOneByTraceabilityAuditId(Long traceabilityAuditId);

}
