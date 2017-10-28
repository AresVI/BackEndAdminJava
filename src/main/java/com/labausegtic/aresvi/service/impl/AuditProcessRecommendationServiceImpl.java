package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.AuditProcessRecommendationService;
import com.labausegtic.aresvi.domain.AuditProcessRecommendation;
import com.labausegtic.aresvi.repository.AuditProcessRecommendationRepository;
import com.labausegtic.aresvi.service.AuditTaskRecommendationService;
import com.labausegtic.aresvi.service.AuditTaskService;
import com.labausegtic.aresvi.service.dto.AuditProcessRecommendationDTO;
import com.labausegtic.aresvi.service.mapper.AuditProcessRecommendationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


/**
 * Service Implementation for managing AuditProcessRecommendation.
 */
@Service
@Transactional
public class AuditProcessRecommendationServiceImpl implements AuditProcessRecommendationService{

    private final Logger log = LoggerFactory.getLogger(AuditProcessRecommendationServiceImpl.class);

    private final AuditProcessRecommendationRepository auditProcessRecommendationRepository;

    private final AuditProcessRecommendationMapper auditProcessRecommendationMapper;

    private final AuditTaskRecommendationService auditTaskRecommendationServiceService;

    public AuditProcessRecommendationServiceImpl(AuditProcessRecommendationRepository auditProcessRecommendationRepository,
                                                 AuditProcessRecommendationMapper auditProcessRecommendationMapper,
                                                 AuditTaskRecommendationService auditTaskRecommendationServiceService) {
        this.auditProcessRecommendationRepository = auditProcessRecommendationRepository;
        this.auditProcessRecommendationMapper = auditProcessRecommendationMapper;
        this.auditTaskRecommendationServiceService = auditTaskRecommendationServiceService;
    }

    /**
     * Save a auditProcessRecommendation.
     *
     * @param auditProcessRecommendationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AuditProcessRecommendationDTO save(AuditProcessRecommendationDTO auditProcessRecommendationDTO) {
        log.debug("Request to save AuditProcessRecommendation : {}", auditProcessRecommendationDTO);
        AuditProcessRecommendation auditProcessRecommendation = auditProcessRecommendationMapper.toEntity(auditProcessRecommendationDTO);
        auditProcessRecommendation = auditProcessRecommendationRepository.save(auditProcessRecommendation);
        return auditProcessRecommendationMapper.toDto(auditProcessRecommendation);
    }

    /**
     *  Get all the auditProcessRecommendations.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AuditProcessRecommendationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AuditProcessRecommendations");
        return auditProcessRecommendationRepository.findAll(pageable)
            .map(auditProcessRecommendationMapper::toDto);
    }

    @Override
    public Set<AuditProcessRecommendationDTO> findAllByRecommendation_Id(Long recommendation_id) {

        Set<AuditProcessRecommendationDTO> result = new HashSet<>();

        Set<AuditProcessRecommendation> auditProcessRecommendations;

        auditProcessRecommendations = auditProcessRecommendationRepository.findAllByRecommendation_Id(recommendation_id);

        for (AuditProcessRecommendation apr : auditProcessRecommendations){

            AuditProcessRecommendationDTO auditProcessRecommendationDTO = auditProcessRecommendationMapper.toDto(apr);


            auditProcessRecommendationDTO.setAuditTaskRecommendationSet(
                auditTaskRecommendationServiceService.findAllByAuditProcessRecom_Id(auditProcessRecommendationDTO.getId())
            );

            result.add(auditProcessRecommendationDTO);

        }

        return result;

    }

    /**
     *  Get one auditProcessRecommendation by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AuditProcessRecommendationDTO findOne(Long id) {
        log.debug("Request to get AuditProcessRecommendation : {}", id);
        AuditProcessRecommendation auditProcessRecommendation = auditProcessRecommendationRepository.findOne(id);
        return auditProcessRecommendationMapper.toDto(auditProcessRecommendation);
    }

    /**
     *  Delete the  auditProcessRecommendation by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AuditProcessRecommendation : {}", id);
        auditProcessRecommendationRepository.delete(id);
    }
}
