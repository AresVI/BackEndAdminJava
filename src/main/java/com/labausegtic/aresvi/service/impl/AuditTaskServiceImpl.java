package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.AuditTaskService;
import com.labausegtic.aresvi.domain.AuditTask;
import com.labausegtic.aresvi.repository.AuditTaskRepository;
import com.labausegtic.aresvi.service.CategoryAttributeService;
import com.labausegtic.aresvi.service.dto.AuditTaskCompleteDTO;
import com.labausegtic.aresvi.service.dto.AuditTaskDTO;
import com.labausegtic.aresvi.service.mapper.AuditTaskCompleteMapper;
import com.labausegtic.aresvi.service.mapper.AuditTaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


/**
 * Service Implementation for managing AuditTask.
 */
@Service
@Transactional
public class AuditTaskServiceImpl implements AuditTaskService{

    private final Logger log = LoggerFactory.getLogger(AuditTaskServiceImpl.class);

    private final AuditTaskRepository auditTaskRepository;

    private final CategoryAttributeService categoryAttributeService;

    private final AuditTaskMapper auditTaskMapper;

    private final AuditTaskCompleteMapper auditTaskCompleteMapper;

    public AuditTaskServiceImpl(AuditTaskRepository auditTaskRepository, CategoryAttributeService categoryAttributeService, AuditTaskMapper auditTaskMapper,
                                AuditTaskCompleteMapper auditTaskCompleteMapper) {
        this.auditTaskRepository = auditTaskRepository;
        this.categoryAttributeService = categoryAttributeService;
        this.auditTaskMapper = auditTaskMapper;
        this.auditTaskCompleteMapper = auditTaskCompleteMapper;
    }

    /**
     * Save a auditTask.
     *
     * @param auditTaskDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AuditTaskDTO save(AuditTaskDTO auditTaskDTO) {
        log.debug("Request to save AuditTask : {}", auditTaskDTO);
        AuditTask auditTask = auditTaskMapper.toEntity(auditTaskDTO);
        auditTask = auditTaskRepository.save(auditTask);
        return auditTaskMapper.toDto(auditTask);
    }

    /**
     *  Get all the auditTasks.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AuditTaskDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AuditTasks");
        return auditTaskRepository.findAll(pageable)
            .map(auditTaskMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<AuditTaskCompleteDTO> findAllByContainer_Id(Long  containerId){
        Set<AuditTaskCompleteDTO> result = new HashSet<>();

        for (AuditTask at:auditTaskRepository.findAllByContainer_Id(containerId)) {

            AuditTaskCompleteDTO auditTaskCompleteDTO = auditTaskCompleteMapper.toDto(at);

            auditTaskCompleteDTO.setCategoryAttributeDTOSet(
                categoryAttributeService.findAllByAuditTaskId(auditTaskCompleteDTO.getId())
            );

            result.add(auditTaskCompleteDTO);
        }

        return result;
    }

    /**
     *  Get one auditTask by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AuditTaskDTO findOne(Long id) {
        log.debug("Request to get AuditTask : {}", id);
        AuditTask auditTask = auditTaskRepository.findOne(id);
        return auditTaskMapper.toDto(auditTask);
    }

    /**
     *  Delete the  auditTask by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AuditTask : {}", id);
        auditTaskRepository.delete(id);
    }
}
