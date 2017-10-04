package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.ContainerService;
import com.labausegtic.aresvi.domain.Container;
import com.labausegtic.aresvi.repository.ContainerRepository;
import com.labausegtic.aresvi.service.dto.ContainerDTO;
import com.labausegtic.aresvi.service.mapper.ContainerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Container.
 */
@Service
@Transactional
public class ContainerServiceImpl implements ContainerService{

    private final Logger log = LoggerFactory.getLogger(ContainerServiceImpl.class);

    private final ContainerRepository containerRepository;

    private final ContainerMapper containerMapper;

    public ContainerServiceImpl(ContainerRepository containerRepository, ContainerMapper containerMapper) {
        this.containerRepository = containerRepository;
        this.containerMapper = containerMapper;
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
