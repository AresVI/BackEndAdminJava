package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.domain.AuditProcessRecommendation;
import com.labausegtic.aresvi.repository.AuditProcessRecommendationRepository;
import com.labausegtic.aresvi.service.AttributeRecommendationService;
import com.labausegtic.aresvi.service.AuditTaskRecommendationService;
import com.labausegtic.aresvi.domain.AuditTaskRecommendation;
import com.labausegtic.aresvi.repository.AuditTaskRecommendationRepository;
import com.labausegtic.aresvi.service.CategoryAttrRecommendationService;
import com.labausegtic.aresvi.service.dto.AttributeRecommendationDTO;
import com.labausegtic.aresvi.service.dto.AuditTaskRecommendationDTO;
import com.labausegtic.aresvi.service.dto.CategoryAttrRecommendationDTO;
import com.labausegtic.aresvi.service.mapper.AuditTaskRecommendationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
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

    private final AuditProcessRecommendationRepository auditProcessRecommendationRepository;

    private final CategoryAttrRecommendationService categoryAttrRecommendationService;

    private final AttributeRecommendationService attributeRecommendationService;

    public AuditTaskRecommendationServiceImpl(AuditTaskRecommendationRepository auditTaskRecommendationRepository,
                                              AuditTaskRecommendationMapper auditTaskRecommendationMapper,
                                              AuditProcessRecommendationRepository auditProcessRecommendationRepository,
                                              CategoryAttrRecommendationService categoryAttrRecommendationService,
                                              AttributeRecommendationService attributeRecommendationService) {
        this.auditTaskRecommendationRepository = auditTaskRecommendationRepository;
        this.auditTaskRecommendationMapper = auditTaskRecommendationMapper;
        this.auditProcessRecommendationRepository = auditProcessRecommendationRepository;
        this.categoryAttrRecommendationService = categoryAttrRecommendationService;
        this.attributeRecommendationService = attributeRecommendationService;
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

        if (auditTaskRecommendation.getId() != null) {

            auditTaskRecommendation.setReviewed(true);
            auditTaskRecommendation.setRevisedDate(Instant.now());
            auditTaskRecommendation = auditTaskRecommendationRepository.save(auditTaskRecommendation);

            for (CategoryAttrRecommendationDTO categoryAttrRecommendationDTO : auditTaskRecommendationDTO.getCategoryAttrRecommendationSet()) {

                categoryAttrRecommendationService.save(categoryAttrRecommendationDTO);

                for (AttributeRecommendationDTO attributeRecommendationDTO : categoryAttrRecommendationDTO.getAttributeRecommendationSet()) {
                    attributeRecommendationService.save(attributeRecommendationDTO);
                }

            }

        }

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
        AuditTaskRecommendationDTO auditTaskRecommendationDTO = auditTaskRecommendationMapper.toDto(auditTaskRecommendation);

        auditTaskRecommendationDTO.setCategoryAttrRecommendationSet(
            categoryAttrRecommendationService.findAllByAuditTaskRecom_Id(auditTaskRecommendationDTO.getId())
        );

        return auditTaskRecommendationDTO;
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

    @Override
    public AuditTaskRecommendationDTO findOneByBonitaBpmCaseIdAndAuditTaskId(Long bonitaBpmCaseId, Long audit_task_id) {

        AuditProcessRecommendation auditProcessRecommendation = auditProcessRecommendationRepository.findByBonitaBpmCaseId(bonitaBpmCaseId);

        AuditTaskRecommendation auditTaskRecommendation;

        auditTaskRecommendation = auditTaskRecommendationRepository.findByAuditProcessRecomIdAndAuditTaskId(
            auditProcessRecommendation.getId(), audit_task_id
        );

        AuditTaskRecommendationDTO auditTaskRecommendationDTO = auditTaskRecommendationMapper.toDto(auditTaskRecommendation);

        auditTaskRecommendationDTO.setCategoryAttrRecommendationSet(
            categoryAttrRecommendationService.findAllByAuditTaskRecom_Id(auditTaskRecommendationDTO.getId())
        );

        return auditTaskRecommendationDTO;
    }
}
