package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.CategoryAttributeService;
import com.labausegtic.aresvi.domain.CategoryAttribute;
import com.labausegtic.aresvi.repository.CategoryAttributeRepository;
import com.labausegtic.aresvi.service.dto.CategoryAttributeDTO;
import com.labausegtic.aresvi.service.mapper.CategoryAttributeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing CategoryAttribute.
 */
@Service
@Transactional
public class CategoryAttributeServiceImpl implements CategoryAttributeService{

    private final Logger log = LoggerFactory.getLogger(CategoryAttributeServiceImpl.class);

    private final CategoryAttributeRepository categoryAttributeRepository;

    private final CategoryAttributeMapper categoryAttributeMapper;

    public CategoryAttributeServiceImpl(CategoryAttributeRepository categoryAttributeRepository, CategoryAttributeMapper categoryAttributeMapper) {
        this.categoryAttributeRepository = categoryAttributeRepository;
        this.categoryAttributeMapper = categoryAttributeMapper;
    }

    /**
     * Save a categoryAttribute.
     *
     * @param categoryAttributeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CategoryAttributeDTO save(CategoryAttributeDTO categoryAttributeDTO) {
        log.debug("Request to save CategoryAttribute : {}", categoryAttributeDTO);
        CategoryAttribute categoryAttribute = categoryAttributeMapper.toEntity(categoryAttributeDTO);
        categoryAttribute = categoryAttributeRepository.save(categoryAttribute);
        return categoryAttributeMapper.toDto(categoryAttribute);
    }

    /**
     *  Get all the categoryAttributes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CategoryAttributeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CategoryAttributes");
        return categoryAttributeRepository.findAll(pageable)
            .map(categoryAttributeMapper::toDto);
    }

    /**
     *  Get one categoryAttribute by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CategoryAttributeDTO findOne(Long id) {
        log.debug("Request to get CategoryAttribute : {}", id);
        CategoryAttribute categoryAttribute = categoryAttributeRepository.findOne(id);
        return categoryAttributeMapper.toDto(categoryAttribute);
    }

    /**
     *  Delete the  categoryAttribute by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CategoryAttribute : {}", id);
        categoryAttributeRepository.delete(id);
    }
}
