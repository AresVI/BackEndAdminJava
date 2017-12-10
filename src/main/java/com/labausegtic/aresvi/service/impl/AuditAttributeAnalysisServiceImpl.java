package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.AuditAttributeAnalysisService;
import com.labausegtic.aresvi.domain.AuditAttributeAnalysis;
import com.labausegtic.aresvi.repository.AuditAttributeAnalysisRepository;
import com.labausegtic.aresvi.service.dto.AuditAttributeAnalysisDTO;
import com.labausegtic.aresvi.service.mapper.AuditAttributeAnalysisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing AuditAttributeAnalysis.
 */
@Service
@Transactional
public class AuditAttributeAnalysisServiceImpl implements AuditAttributeAnalysisService {

    private final Logger log = LoggerFactory.getLogger(AuditAttributeAnalysisServiceImpl.class);

    private final AuditAttributeAnalysisRepository auditAttributeAnalysisRepository;

    private final AuditAttributeAnalysisMapper auditAttributeAnalysisMapper;

    public AuditAttributeAnalysisServiceImpl(AuditAttributeAnalysisRepository auditAttributeAnalysisRepository, AuditAttributeAnalysisMapper auditAttributeAnalysisMapper) {
        this.auditAttributeAnalysisRepository = auditAttributeAnalysisRepository;
        this.auditAttributeAnalysisMapper = auditAttributeAnalysisMapper;
    }

    /**
     * Save a auditAttributeAnalysis.
     *
     * @param auditAttributeAnalysisDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AuditAttributeAnalysisDTO save(AuditAttributeAnalysisDTO auditAttributeAnalysisDTO) {
        log.debug("Request to save AuditAttributeAnalysis : {}", auditAttributeAnalysisDTO);
        AuditAttributeAnalysis auditAttributeAnalysis = auditAttributeAnalysisMapper.toEntity(auditAttributeAnalysisDTO);
        auditAttributeAnalysis = auditAttributeAnalysisRepository.save(auditAttributeAnalysis);
        return auditAttributeAnalysisMapper.toDto(auditAttributeAnalysis);
    }

    /**
     *  Get all the auditAttributeAnalyses.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AuditAttributeAnalysisDTO> findAll() {
        log.debug("Request to get all AuditAttributeAnalyses");
        return auditAttributeAnalysisRepository.findAll().stream()
            .map(auditAttributeAnalysisMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one auditAttributeAnalysis by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AuditAttributeAnalysisDTO findOne(Long id) {
        log.debug("Request to get AuditAttributeAnalysis : {}", id);
        AuditAttributeAnalysis auditAttributeAnalysis = auditAttributeAnalysisRepository.findOne(id);
        return auditAttributeAnalysisMapper.toDto(auditAttributeAnalysis);
    }

    /**
     *  Delete the  auditAttributeAnalysis by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AuditAttributeAnalysis : {}", id);
        auditAttributeAnalysisRepository.delete(id);
    }

    @Override
    public AuditAttributeAnalysisDTO getOneByTraceabilityAuditId(Long traceabilityAuditId) {
        AuditAttributeAnalysis auditAttributeAnalysis;
        auditAttributeAnalysis= auditAttributeAnalysisRepository.getAuditAttributeAnalysisByTraceabilityAuditId(traceabilityAuditId);
        return auditAttributeAnalysisMapper.toDto(auditAttributeAnalysis);
    }
}
