package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.AttributeService;
import com.labausegtic.aresvi.service.CategoryAttributeService;
import com.labausegtic.aresvi.domain.CategoryAttribute;
import com.labausegtic.aresvi.repository.CategoryAttributeRepository;
import com.labausegtic.aresvi.service.dto.CategoryAttributeCompleteDTO;
import com.labausegtic.aresvi.service.dto.CategoryAttributeDTO;
import com.labausegtic.aresvi.service.mapper.CategoryAttributeCompleteMapper;
import com.labausegtic.aresvi.service.mapper.CategoryAttributeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


/**
 * Service Implementation for managing CategoryAttribute.
 */
@Service
@Transactional
public class CategoryAttributeServiceImpl implements CategoryAttributeService{

    private final Logger log = LoggerFactory.getLogger(CategoryAttributeServiceImpl.class);

    private final CategoryAttributeRepository categoryAttributeRepository;

    private final AttributeService attributeService;

    private final CategoryAttributeMapper categoryAttributeMapper;

    private final CategoryAttributeCompleteMapper categoryAttributeCompleteMapper;

    public CategoryAttributeServiceImpl(CategoryAttributeRepository categoryAttributeRepository,
                                        AttributeService attributeService, CategoryAttributeMapper categoryAttributeMapper,
                                        CategoryAttributeCompleteMapper categoryAttributeCompleteMapper) {
        this.categoryAttributeRepository = categoryAttributeRepository;
        this.attributeService = attributeService;
        this.categoryAttributeMapper = categoryAttributeMapper;
        this.categoryAttributeCompleteMapper = categoryAttributeCompleteMapper;
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

    @Override
    @Transactional(readOnly = true)
    public Set<CategoryAttributeCompleteDTO> findAllByAuditTaskId(Long AuditTaskId){
        Set<CategoryAttributeCompleteDTO> result = new HashSet<>();

        for (CategoryAttribute ca: categoryAttributeRepository.findAllByAuditTask_Id(AuditTaskId)) {

            CategoryAttributeCompleteDTO categoryAttributeCompleteDTO = categoryAttributeCompleteMapper.toDto(ca);

            categoryAttributeCompleteDTO.setAttributeSet(
                attributeService.findAllByCategoryAttribute_Id(categoryAttributeCompleteDTO.getId())
            );

            result.add(categoryAttributeCompleteDTO);
        }

        return result;
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
