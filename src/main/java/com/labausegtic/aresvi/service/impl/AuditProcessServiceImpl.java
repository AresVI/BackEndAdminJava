package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.AuditProcessService;
import com.labausegtic.aresvi.domain.AuditProcess;
import com.labausegtic.aresvi.repository.AuditProcessRepository;
import com.labausegtic.aresvi.service.dto.AuditProcessDTO;
import com.labausegtic.aresvi.service.mapper.AuditProcessMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing AuditProcess.
 */
@Service
@Transactional
public class AuditProcessServiceImpl implements AuditProcessService{

    private final Logger log = LoggerFactory.getLogger(AuditProcessServiceImpl.class);

    private final AuditProcessRepository auditProcessRepository;

    private final AuditProcessMapper auditProcessMapper;

    public AuditProcessServiceImpl(AuditProcessRepository auditProcessRepository, AuditProcessMapper auditProcessMapper) {
        this.auditProcessRepository = auditProcessRepository;
        this.auditProcessMapper = auditProcessMapper;
    }

    /**
     * Save a auditProcess.
     *
     * @param auditProcessDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AuditProcessDTO save(AuditProcessDTO auditProcessDTO) {
        log.debug("Request to save AuditProcess : {}", auditProcessDTO);
        AuditProcess auditProcess = auditProcessMapper.toEntity(auditProcessDTO);
        auditProcess = auditProcessRepository.save(auditProcess);
        return auditProcessMapper.toDto(auditProcess);
    }

    /**
     *  Get all the auditProcesses.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AuditProcessDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AuditProcesses");
        return auditProcessRepository.findAll(pageable)
            .map(auditProcessMapper::toDto);
    }

    /**
     *  Get one auditProcess by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AuditProcessDTO findOne(Long id) {
        log.debug("Request to get AuditProcess : {}", id);
        AuditProcess auditProcess = auditProcessRepository.findOne(id);
        return auditProcessMapper.toDto(auditProcess);
    }

    /**
     *  Delete the  auditProcess by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AuditProcess : {}", id);
        auditProcessRepository.delete(id);
    }
}
