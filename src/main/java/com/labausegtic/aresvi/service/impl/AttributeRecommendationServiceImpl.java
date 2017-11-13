package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.domain.AttributeRecommendation;
import com.labausegtic.aresvi.service.AttributeRecommendationService;
import com.labausegtic.aresvi.domain.AttributeRecommendation;
import com.labausegtic.aresvi.repository.AttributeRecommendationRepository;
import com.labausegtic.aresvi.service.dto.AttributeRecommendationDTO;
import com.labausegtic.aresvi.service.dto.AttributeRecommendationDTO;
import com.labausegtic.aresvi.service.mapper.AttributeRecommendationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Service Implementation for managing AttributeRecommendation.
 */
@Service
@Transactional
public class AttributeRecommendationServiceImpl implements AttributeRecommendationService{

    private final Logger log = LoggerFactory.getLogger(AttributeRecommendationServiceImpl.class);

    private final AttributeRecommendationRepository attributeRecommendationRepository;

    private final AttributeRecommendationMapper attributeRecommendationMapper;

    public AttributeRecommendationServiceImpl(AttributeRecommendationRepository attributeRecommendationRepository, AttributeRecommendationMapper attributeRecommendationMapper) {
        this.attributeRecommendationRepository = attributeRecommendationRepository;
        this.attributeRecommendationMapper = attributeRecommendationMapper;
    }

    /**
     * Save a attributeRecommendation.
     *
     * @param attributeRecommendationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AttributeRecommendationDTO save(AttributeRecommendationDTO attributeRecommendationDTO) {
        log.debug("Request to save AttributeRecommendation : {}", attributeRecommendationDTO);
        AttributeRecommendation attributeRecommendation = attributeRecommendationMapper.toEntity(attributeRecommendationDTO);
        attributeRecommendation = attributeRecommendationRepository.save(attributeRecommendation);
        return attributeRecommendationMapper.toDto(attributeRecommendation);
    }

    /**
     *  Get all the attributeRecommendations.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AttributeRecommendationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AttributeRecommendations");
        return attributeRecommendationRepository.findAll(pageable)
            .map(attributeRecommendationMapper::toDto);
    }

    /**
     *  Get one attributeRecommendation by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AttributeRecommendationDTO findOne(Long id) {
        log.debug("Request to get AttributeRecommendation : {}", id);
        AttributeRecommendation attributeRecommendation = attributeRecommendationRepository.findOne(id);
        return attributeRecommendationMapper.toDto(attributeRecommendation);
    }

    @Override
    public Set<AttributeRecommendationDTO> findAllByCategoryAttrRecom_Id(Long categoryAttrRecom_id) {

        Set<AttributeRecommendationDTO> result = new HashSet<>();

        List<AttributeRecommendation> attributeRecommendations;

        attributeRecommendations = attributeRecommendationRepository.findAllByCategoryAttrRecom_Id(categoryAttrRecom_id);

        for (AttributeRecommendation ar : attributeRecommendations) {

            result.add(attributeRecommendationMapper.toDto(ar));

        }

        return result;

    }

    /**
     *  Delete the  attributeRecommendation by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AttributeRecommendation : {}", id);
        attributeRecommendationRepository.delete(id);
    }
}
