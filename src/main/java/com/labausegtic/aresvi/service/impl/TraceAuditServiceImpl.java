package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.AuditTaskRecommendationService;
import com.labausegtic.aresvi.service.TraceAuditService;
import com.labausegtic.aresvi.domain.TraceAudit;
import com.labausegtic.aresvi.repository.TraceAuditRepository;
import com.labausegtic.aresvi.service.dto.TraceAuditDTO;
import com.labausegtic.aresvi.service.mapper.TraceAuditMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing TraceAudit.
 */
@Service
@Transactional
public class TraceAuditServiceImpl implements TraceAuditService{

    private final Logger log = LoggerFactory.getLogger(TraceAuditServiceImpl.class);

    private final TraceAuditRepository traceAuditRepository;

    private final TraceAuditMapper traceAuditMapper;

    private final AuditTaskRecommendationService auditTaskRecommendationService;

    public TraceAuditServiceImpl(TraceAuditRepository traceAuditRepository, TraceAuditMapper traceAuditMapper, AuditTaskRecommendationService auditTaskRecommendationService) {
        this.traceAuditRepository = traceAuditRepository;
        this.traceAuditMapper = traceAuditMapper;
        this.auditTaskRecommendationService = auditTaskRecommendationService;
    }

    /**
     * Save a traceAudit.
     *
     * @param traceAuditDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TraceAuditDTO save(TraceAuditDTO traceAuditDTO) {
        log.debug("Request to save TraceAudit : {}", traceAuditDTO);
        TraceAudit traceAudit = traceAuditMapper.toEntity(traceAuditDTO);
        traceAudit = traceAuditRepository.save(traceAudit);
        return traceAuditMapper.toDto(traceAudit);
    }

    /**
     *  Get all the traceAudits.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TraceAuditDTO> findAll(Long traceabilityAuditId) {
        log.debug("Request to get all TraceAudits");
        LinkedList<TraceAuditDTO> traceAuditList = traceAuditRepository.findAllByTraceabilityAuditId(traceabilityAuditId).stream()
            .map(traceAuditMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));

        for (TraceAuditDTO ta: traceAuditList) {
            ta.setAuditTaskRecommendation(auditTaskRecommendationService.findOne(ta.getAuditTaskRecommendationId()));
        }

        return traceAuditList;
    }

    /**
     *  Get one traceAudit by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TraceAuditDTO findOne(Long id) {
        log.debug("Request to get TraceAudit : {}", id);
        TraceAudit traceAudit = traceAuditRepository.findOne(id);
        return traceAuditMapper.toDto(traceAudit);
    }

    /**
     *  Delete the  traceAudit by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TraceAudit : {}", id);
        traceAuditRepository.delete(id);
    }
}
