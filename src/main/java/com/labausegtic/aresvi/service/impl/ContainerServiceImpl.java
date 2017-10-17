package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.repository.AuditTaskRepository;
import com.labausegtic.aresvi.service.AuditTaskService;
import com.labausegtic.aresvi.service.ContainerService;
import com.labausegtic.aresvi.domain.Container;
import com.labausegtic.aresvi.repository.ContainerRepository;
import com.labausegtic.aresvi.service.dto.AuditTaskCompleteDTO;
import com.labausegtic.aresvi.service.dto.ContainerCompleteDTO;
import com.labausegtic.aresvi.service.dto.ContainerDTO;
import com.labausegtic.aresvi.service.mapper.AuditTaskCompleteMapper;
import com.labausegtic.aresvi.service.mapper.ContainerCompleteMapper;
import com.labausegtic.aresvi.service.mapper.ContainerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


/**
 * Service Implementation for managing Container.
 */
@Service
@Transactional
public class ContainerServiceImpl implements ContainerService{

    private final Logger log = LoggerFactory.getLogger(ContainerServiceImpl.class);

    private final AuditTaskService auditTaskService;

    private final ContainerRepository containerRepository;

    private final ContainerMapper containerMapper;

    private final ContainerCompleteMapper containerCompleteMapper;

    public ContainerServiceImpl(AuditTaskService auditTaskService, ContainerRepository containerRepository,
                                ContainerMapper containerMapper, ContainerCompleteMapper containerCompleteMapper) {
        this.auditTaskService = auditTaskService;
        this.containerRepository = containerRepository;
        this.containerMapper = containerMapper;
        this.containerCompleteMapper = containerCompleteMapper;
    }

    /**
     * Save a container.
     *
     * @param containerDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ContainerDTO save(ContainerDTO containerDTO) {
        log.debug("Request to save Container : {}", containerDTO);
        Container container = containerMapper.toEntity(containerDTO);
        container = containerRepository.save(container);
        return containerMapper.toDto(container);
    }

    /**
     *  Get all the containers.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ContainerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Containers");
        return containerRepository.findAll(pageable)
            .map(containerMapper::toDto);
    }

    @Override
    public Set<ContainerCompleteDTO> findAllByAuditProcess_Id(Long auditProcessId) {

        Set<ContainerCompleteDTO> result = new HashSet<>();

        for (Container c:containerRepository.findAllByAuditProcess_Id(auditProcessId)) {

            ContainerCompleteDTO containerCompleteDTO = containerCompleteMapper.toDto(c);

            containerCompleteDTO.setAuditTaskDTOSet(auditTaskService.findAllByContainer_Id(c.getId()));

            result.add(containerCompleteDTO);
        }

        return result;

    }

    /**
     *  Get one container by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ContainerDTO findOne(Long id) {
        log.debug("Request to get Container : {}", id);
        Container container = containerRepository.findOne(id);
        return containerMapper.toDto(container);
    }

    /**
     *  Delete the  container by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Container : {}", id);
        containerRepository.delete(id);
    }
}
