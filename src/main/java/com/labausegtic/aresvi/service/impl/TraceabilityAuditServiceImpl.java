package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.TraceabilityAuditService;
import com.labausegtic.aresvi.domain.TraceabilityAudit;
import com.labausegtic.aresvi.repository.TraceabilityAuditRepository;
import com.labausegtic.aresvi.service.dto.TraceabilityAuditDTO;
import com.labausegtic.aresvi.service.mapper.TraceabilityAuditMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing TraceabilityAudit.
 */
@Service
@Transactional
public class TraceabilityAuditServiceImpl implements TraceabilityAuditService{

    private final Logger log = LoggerFactory.getLogger(TraceabilityAuditServiceImpl.class);

    private final TraceabilityAuditRepository traceabilityAuditRepository;

    private final TraceabilityAuditMapper traceabilityAuditMapper;

    public TraceabilityAuditServiceImpl(TraceabilityAuditRepository traceabilityAuditRepository, TraceabilityAuditMapper traceabilityAuditMapper) {
        this.traceabilityAuditRepository = traceabilityAuditRepository;
        this.traceabilityAuditMapper = traceabilityAuditMapper;
    }

    /**
     * Save a traceabilityAudit.
     *
     * @param traceabilityAuditDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TraceabilityAuditDTO save(TraceabilityAuditDTO traceabilityAuditDTO) {
        log.debug("Request to save TraceabilityAudit : {}", traceabilityAuditDTO);
        TraceabilityAudit traceabilityAudit = traceabilityAuditMapper.toEntity(traceabilityAuditDTO);
        log.debug(traceabilityAudit.toString());
        traceabilityAudit = traceabilityAuditRepository.save(traceabilityAudit);
        return traceabilityAuditMapper.toDto(traceabilityAudit);
    }

    /**
     *  Get all the traceabilityAudits.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TraceabilityAuditDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TraceabilityAudits");
        return traceabilityAuditRepository.findAll(pageable)
            .map(traceabilityAuditMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TraceabilityAuditDTO> findAllByStatus(Pageable pageable, String status) {
        log.debug("Request to get all TraceabilityAudits");
        return traceabilityAuditRepository.findAllByStatus(pageable, status)
            .map(traceabilityAuditMapper::toDto);
    }

    /**
     *  Get one traceabilityAudit by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TraceabilityAuditDTO findOne(Long id) {
        log.debug("Request to get TraceabilityAudit : {}", id);
        TraceabilityAudit traceabilityAudit = traceabilityAuditRepository.findOne(id);
        return traceabilityAuditMapper.toDto(traceabilityAudit);
    }

    @Override
    @Transactional(readOnly = true)
    public TraceabilityAuditDTO findLastByCompanyId(Long company_id) {
        TraceabilityAudit traceabilityAudit = traceabilityAuditRepository.findLastByCompanyId(company_id);
        return traceabilityAuditMapper.toDto(traceabilityAudit);
    }

    /**
     *  Delete the  traceabilityAudit by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TraceabilityAudit : {}", id);
        traceabilityAuditRepository.delete(id);
    }
}
