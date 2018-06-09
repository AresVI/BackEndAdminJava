package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.RecommendationAttributeService;
import com.labausegtic.aresvi.domain.RecommendationAttribute;
import com.labausegtic.aresvi.repository.RecommendationAttributeRepository;
import com.labausegtic.aresvi.service.dto.RecommendationAttributeDTO;
import com.labausegtic.aresvi.service.mapper.RecommendationAttributeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing RecommendationAttribute.
 */
@Service
@Transactional
public class RecommendationAttributeServiceImpl implements RecommendationAttributeService{

    private final Logger log = LoggerFactory.getLogger(RecommendationAttributeServiceImpl.class);

    private final RecommendationAttributeRepository recommendationAttributeRepository;

    private final RecommendationAttributeMapper recommendationAttributeMapper;

    public RecommendationAttributeServiceImpl(RecommendationAttributeRepository recommendationAttributeRepository, RecommendationAttributeMapper recommendationAttributeMapper) {
        this.recommendationAttributeRepository = recommendationAttributeRepository;
        this.recommendationAttributeMapper = recommendationAttributeMapper;
    }

    /**
     * Save a recommendationAttribute.
     *
     * @param recommendationAttributeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RecommendationAttributeDTO save(RecommendationAttributeDTO recommendationAttributeDTO) {
        log.debug("Request to save RecommendationAttribute : {}", recommendationAttributeDTO);
        RecommendationAttribute recommendationAttribute = recommendationAttributeMapper.toEntity(recommendationAttributeDTO);
        recommendationAttribute = recommendationAttributeRepository.save(recommendationAttribute);
        return recommendationAttributeMapper.toDto(recommendationAttribute);
    }

    /**
     *  Get all the recommendationAttributes.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<RecommendationAttributeDTO> findAll() {
        log.debug("Request to get all RecommendationAttributes");
        return recommendationAttributeRepository.findAll().stream()
            .map(recommendationAttributeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one recommendationAttribute by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public RecommendationAttributeDTO findOne(Long id) {
        log.debug("Request to get RecommendationAttribute : {}", id);
        RecommendationAttribute recommendationAttribute = recommendationAttributeRepository.findOne(id);
        return recommendationAttributeMapper.toDto(recommendationAttribute);
    }

    /**
     *  Delete the  recommendationAttribute by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RecommendationAttribute : {}", id);
        recommendationAttributeRepository.delete(id);
    }
}
