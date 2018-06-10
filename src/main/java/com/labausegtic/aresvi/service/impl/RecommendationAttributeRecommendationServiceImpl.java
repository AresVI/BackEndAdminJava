package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.RecommendationAttributeRecommendationService;
import com.labausegtic.aresvi.domain.RecommendationAttributeRecommendation;
import com.labausegtic.aresvi.repository.RecommendationAttributeRecommendationRepository;
import com.labausegtic.aresvi.service.dto.RecommendationAttributeRecommendationDTO;
import com.labausegtic.aresvi.service.mapper.RecommendationAttributeRecommendationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing RecommendationAttributeRecommendation.
 */
@Service
@Transactional
public class RecommendationAttributeRecommendationServiceImpl implements RecommendationAttributeRecommendationService{

    private final Logger log = LoggerFactory.getLogger(RecommendationAttributeRecommendationServiceImpl.class);

    private final RecommendationAttributeRecommendationRepository recommendationAttributeRecommendationRepository;

    private final RecommendationAttributeRecommendationMapper recommendationAttributeRecommendationMapper;

    public RecommendationAttributeRecommendationServiceImpl(RecommendationAttributeRecommendationRepository recommendationAttributeRecommendationRepository, RecommendationAttributeRecommendationMapper recommendationAttributeRecommendationMapper) {
        this.recommendationAttributeRecommendationRepository = recommendationAttributeRecommendationRepository;
        this.recommendationAttributeRecommendationMapper = recommendationAttributeRecommendationMapper;
    }

    /**
     * Save a recommendationAttributeRecommendation.
     *
     * @param recommendationAttributeRecommendationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RecommendationAttributeRecommendationDTO save(RecommendationAttributeRecommendationDTO recommendationAttributeRecommendationDTO) {
        log.debug("Request to save RecommendationAttributeRecommendation : {}", recommendationAttributeRecommendationDTO);
        RecommendationAttributeRecommendation recommendationAttributeRecommendation = recommendationAttributeRecommendationMapper.toEntity(recommendationAttributeRecommendationDTO);
        recommendationAttributeRecommendation = recommendationAttributeRecommendationRepository.save(recommendationAttributeRecommendation);
        return recommendationAttributeRecommendationMapper.toDto(recommendationAttributeRecommendation);
    }

    /**
     *  Get all the recommendationAttributeRecommendations.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<RecommendationAttributeRecommendationDTO> findAll() {
        log.debug("Request to get all RecommendationAttributeRecommendations");
        return recommendationAttributeRecommendationRepository.findAll().stream()
            .map(recommendationAttributeRecommendationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecommendationAttributeRecommendationDTO> findAll(Long recommendation_id) {
        log.debug("Request to get all RecommendationAttributeRecommendations");
        return recommendationAttributeRecommendationRepository.findAllByRecommendationId(recommendation_id).stream()
            .map(recommendationAttributeRecommendationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one recommendationAttributeRecommendation by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public RecommendationAttributeRecommendationDTO findOne(Long id) {
        log.debug("Request to get RecommendationAttributeRecommendation : {}", id);
        RecommendationAttributeRecommendation recommendationAttributeRecommendation = recommendationAttributeRecommendationRepository.findOne(id);
        return recommendationAttributeRecommendationMapper.toDto(recommendationAttributeRecommendation);
    }

    /**
     *  Delete the  recommendationAttributeRecommendation by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RecommendationAttributeRecommendation : {}", id);
        recommendationAttributeRecommendationRepository.delete(id);
    }
}
