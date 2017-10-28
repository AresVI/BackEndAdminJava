package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.AuditTaskRecommendationService;
import com.labausegtic.aresvi.domain.AuditTaskRecommendation;
import com.labausegtic.aresvi.repository.AuditTaskRecommendationRepository;
import com.labausegtic.aresvi.service.CategoryAttrRecommendationService;
import com.labausegtic.aresvi.service.dto.AuditTaskRecommendationDTO;
import com.labausegtic.aresvi.service.mapper.AuditTaskRecommendationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


/**
 * Service Implementation for managing AuditTaskRecommendation.
 */
@Service
@Transactional
public class AuditTaskRecommendationServiceImpl implements AuditTaskRecommendationService{

    private final Logger log = LoggerFactory.getLogger(AuditTaskRecommendationServiceImpl.class);

    private final AuditTaskRecommendationRepository auditTaskRecommendationRepository;

    private final AuditTaskRecommendationMapper auditTaskRecommendationMapper;

    private final CategoryAttrRecommendationService categoryAttrRecommendationService;

    public AuditTaskRecommendationServiceImpl(AuditTaskRecommendationRepository auditTaskRecommendationRepository,
                                              AuditTaskRecommendationMapper auditTaskRecommendationMapper,
                                              CategoryAttrRecommendationService categoryAttrRecommendationService) {
        this.auditTaskRecommendationRepository = auditTaskRecommendationRepository;
        this.auditTaskRecommendationMapper = auditTaskRecommendationMapper;
        this.categoryAttrRecommendationService = categoryAttrRecommendationService;
    }

    /**
     * Save a auditTaskRecommendation.
     *
     * @param auditTaskRecommendationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AuditTaskRecommendationDTO save(AuditTaskRecommendationDTO auditTaskRecommendationDTO) {
        log.debug("Request to save AuditTaskRecommendation : {}", auditTaskRecommendationDTO);
        AuditTaskRecommendation auditTaskRecommendation = auditTaskRecommendationMapper.toEntity(auditTaskRecommendationDTO);
        auditTaskRecommendation = auditTaskRecommendationRepository.save(auditTaskRecommendation);
        return auditTaskRecommendationMapper.toDto(auditTaskRecommendation);
    }

    /**
     *  Get all the auditTaskRecommendations.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AuditTaskRecommendationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AuditTaskRecommendations");
        return auditTaskRecommendationRepository.findAll(pageable)
            .map(auditTaskRecommendationMapper::toDto);
    }

    @Override
    public Set<AuditTaskRecommendationDTO> findAllByAuditProcessRecom_Id(Long auditProcessRecom_id) {
        Set<AuditTaskRecommendationDTO> result = new HashSet<>();

        Set<AuditTaskRecommendation> AuditTaskRecommendations;

        AuditTaskRecommendations = auditTaskRecommendationRepository.findAllByAuditProcessRecom_Id(auditProcessRecom_id);

        for (AuditTaskRecommendation atr : AuditTaskRecommendations){

            AuditTaskRecommendationDTO auditTaskRecommendationDTO = auditTaskRecommendationMapper.toDto(atr);

            auditTaskRecommendationDTO.setCategoryAttrRecommendationSet(
                categoryAttrRecommendationService.findAllByAuditTaskRecom_Id(auditTaskRecommendationDTO.getId())
            );

            result.add(auditTaskRecommendationDTO);

        }

        return result;
    }

    /**
     *  Get one auditTaskRecommendation by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AuditTaskRecommendationDTO findOne(Long id) {
        log.debug("Request to get AuditTaskRecommendation : {}", id);
        AuditTaskRecommendation auditTaskRecommendation = auditTaskRecommendationRepository.findOne(id);
        return auditTaskRecommendationMapper.toDto(auditTaskRecommendation);
    }

    /**
     *  Delete the  auditTaskRecommendation by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AuditTaskRecommendation : {}", id);
        auditTaskRecommendationRepository.delete(id);
    }
}
