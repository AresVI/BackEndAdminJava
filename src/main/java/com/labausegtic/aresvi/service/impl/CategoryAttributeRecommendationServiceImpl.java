package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.CategoryAttributeRecommendationService;
import com.labausegtic.aresvi.domain.CategoryAttributeRecommendation;
import com.labausegtic.aresvi.repository.CategoryAttributeRecommendationRepository;
import com.labausegtic.aresvi.service.dto.CategoryAttributeRecommendationDTO;
import com.labausegtic.aresvi.service.mapper.CategoryAttributeRecommendationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing CategoryAttributeRecommendation.
 */
@Service
@Transactional
public class CategoryAttributeRecommendationServiceImpl implements CategoryAttributeRecommendationService{

    private final Logger log = LoggerFactory.getLogger(CategoryAttributeRecommendationServiceImpl.class);

    private final CategoryAttributeRecommendationRepository categoryAttributeRecommendationRepository;

    private final CategoryAttributeRecommendationMapper categoryAttributeRecommendationMapper;

    public CategoryAttributeRecommendationServiceImpl(CategoryAttributeRecommendationRepository categoryAttributeRecommendationRepository, CategoryAttributeRecommendationMapper categoryAttributeRecommendationMapper) {
        this.categoryAttributeRecommendationRepository = categoryAttributeRecommendationRepository;
        this.categoryAttributeRecommendationMapper = categoryAttributeRecommendationMapper;
    }

    /**
     * Save a categoryAttributeRecommendation.
     *
     * @param categoryAttributeRecommendationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CategoryAttributeRecommendationDTO save(CategoryAttributeRecommendationDTO categoryAttributeRecommendationDTO) {
        log.debug("Request to save CategoryAttributeRecommendation : {}", categoryAttributeRecommendationDTO);
        CategoryAttributeRecommendation categoryAttributeRecommendation = categoryAttributeRecommendationMapper.toEntity(categoryAttributeRecommendationDTO);
        categoryAttributeRecommendation = categoryAttributeRecommendationRepository.save(categoryAttributeRecommendation);
        return categoryAttributeRecommendationMapper.toDto(categoryAttributeRecommendation);
    }

    /**
     *  Get all the categoryAttributeRecommendations.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CategoryAttributeRecommendationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CategoryAttributeRecommendations");
        return categoryAttributeRecommendationRepository.findAll(pageable)
            .map(categoryAttributeRecommendationMapper::toDto);
    }

    /**
     *  Get one categoryAttributeRecommendation by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CategoryAttributeRecommendationDTO findOne(Long id) {
        log.debug("Request to get CategoryAttributeRecommendation : {}", id);
        CategoryAttributeRecommendation categoryAttributeRecommendation = categoryAttributeRecommendationRepository.findOne(id);
        return categoryAttributeRecommendationMapper.toDto(categoryAttributeRecommendation);
    }

    /**
     *  Delete the  categoryAttributeRecommendation by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CategoryAttributeRecommendation : {}", id);
        categoryAttributeRecommendationRepository.delete(id);
    }
}
