package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.CategoryAttRecommendationService;
import com.labausegtic.aresvi.domain.CategoryAttRecommendation;
import com.labausegtic.aresvi.repository.CategoryAttRecommendationRepository;
import com.labausegtic.aresvi.service.dto.CategoryAttRecommendationDTO;
import com.labausegtic.aresvi.service.mapper.CategoryAttRecommendationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing CategoryAttRecommendation.
 */
@Service
@Transactional
public class CategoryAttRecommendationServiceImpl implements CategoryAttRecommendationService{

    private final Logger log = LoggerFactory.getLogger(CategoryAttRecommendationServiceImpl.class);

    private final CategoryAttRecommendationRepository categoryAttRecommendationRepository;

    private final CategoryAttRecommendationMapper categoryAttRecommendationMapper;

    public CategoryAttRecommendationServiceImpl(CategoryAttRecommendationRepository categoryAttRecommendationRepository, CategoryAttRecommendationMapper categoryAttRecommendationMapper) {
        this.categoryAttRecommendationRepository = categoryAttRecommendationRepository;
        this.categoryAttRecommendationMapper = categoryAttRecommendationMapper;
    }

    /**
     * Save a categoryAttRecommendation.
     *
     * @param categoryAttRecommendationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CategoryAttRecommendationDTO save(CategoryAttRecommendationDTO categoryAttRecommendationDTO) {
        log.debug("Request to save CategoryAttRecommendation : {}", categoryAttRecommendationDTO);
        CategoryAttRecommendation categoryAttRecommendation = categoryAttRecommendationMapper.toEntity(categoryAttRecommendationDTO);
        categoryAttRecommendation = categoryAttRecommendationRepository.save(categoryAttRecommendation);
        return categoryAttRecommendationMapper.toDto(categoryAttRecommendation);
    }

    /**
     *  Get all the categoryAttRecommendations.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CategoryAttRecommendationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CategoryAttRecommendations");
        return categoryAttRecommendationRepository.findAll(pageable)
            .map(categoryAttRecommendationMapper::toDto);
    }

    /**
     *  Get one categoryAttRecommendation by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CategoryAttRecommendationDTO findOne(Long id) {
        log.debug("Request to get CategoryAttRecommendation : {}", id);
        CategoryAttRecommendation categoryAttRecommendation = categoryAttRecommendationRepository.findOne(id);
        return categoryAttRecommendationMapper.toDto(categoryAttRecommendation);
    }

    /**
     *  Delete the  categoryAttRecommendation by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CategoryAttRecommendation : {}", id);
        categoryAttRecommendationRepository.delete(id);
    }
}
