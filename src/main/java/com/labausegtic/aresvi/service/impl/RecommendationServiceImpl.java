package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.AuditProcessRecommendationService;
import com.labausegtic.aresvi.service.RecommendationService;
import com.labausegtic.aresvi.domain.Recommendation;
import com.labausegtic.aresvi.repository.RecommendationRepository;
import com.labausegtic.aresvi.service.dto.RecommendationDTO;
import com.labausegtic.aresvi.service.mapper.RecommendationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


/**
 * Service Implementation for managing Recommendation.
 */
@Service
@Transactional
public class RecommendationServiceImpl implements RecommendationService{

    private final Logger log = LoggerFactory.getLogger(RecommendationServiceImpl.class);

    private final RecommendationRepository recommendationRepository;

    private final RecommendationMapper recommendationMapper;

    private final AuditProcessRecommendationService auditProcessRecommendationService;

    public RecommendationServiceImpl(RecommendationRepository recommendationRepository, RecommendationMapper recommendationMapper,
                                     AuditProcessRecommendationService auditProcessRecommendationService) {
        this.recommendationRepository = recommendationRepository;
        this.recommendationMapper = recommendationMapper;
        this.auditProcessRecommendationService = auditProcessRecommendationService;
    }

    /**
     * Save a recommendation.
     *
     * @param recommendationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RecommendationDTO save(RecommendationDTO recommendationDTO) {
        log.debug("Request to save Recommendation : {}", recommendationDTO);
        Recommendation recommendation = recommendationMapper.toEntity(recommendationDTO);
        recommendation = recommendationRepository.save(recommendation);
        return recommendationMapper.toDto(recommendation);
    }

    /**
     *  Get all the recommendations.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RecommendationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Recommendations");
        return recommendationRepository.findAll(pageable)
            .map(recommendationMapper::toDto);
    }

    @Override
    public Set<RecommendationDTO> findAllByTraceabilityAudit_Id(Long traceabilityAudit_Id) {

        Set<RecommendationDTO> result = new HashSet<>();

        Set<Recommendation> recommendations = recommendationRepository.findAllByTraceabilityAudit_Id(traceabilityAudit_Id);

        for (Recommendation ta:recommendations){

            RecommendationDTO recommendationDTO = recommendationMapper.toDto(ta);

            recommendationDTO.setAuditProcessRecommendationSet(
                auditProcessRecommendationService.findAllByRecommendation_Id(
                    recommendationDTO.getId()
                )
            );

            result.add(recommendationDTO);

        }

        return result;

    }

    /**
     *  Get one recommendation by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public RecommendationDTO findOne(Long id) {
        log.debug("Request to get Recommendation : {}", id);
        Recommendation recommendation = recommendationRepository.findOne(id);
        return recommendationMapper.toDto(recommendation);
    }

    /**
     *  Delete the  recommendation by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Recommendation : {}", id);
        recommendationRepository.delete(id);
    }
}
