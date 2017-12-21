package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.config.ApplicationProperties;
import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.repository.AuditProcessRecommendationRepository;
import com.labausegtic.aresvi.repository.RecommendationRepository;
import com.labausegtic.aresvi.security.SecurityUtils;
import com.labausegtic.aresvi.service.BonitaBPMService;
import com.labausegtic.aresvi.service.CancellationTraceabilityAuditService;
import com.labausegtic.aresvi.repository.CancellationTraceabilityAuditRepository;
import com.labausegtic.aresvi.service.TraceabilityAuditService;
import com.labausegtic.aresvi.service.UserService;
import com.labausegtic.aresvi.service.dto.CancellationTraceabilityAuditDTO;
import com.labausegtic.aresvi.service.dto.TraceabilityAuditDTO;
import com.labausegtic.aresvi.service.mapper.CancellationTraceabilityAuditMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;


/**
 * Service Implementation for managing CancellationTraceabilityAudit.
 */
@Service
@Transactional
public class CancellationTraceabilityAuditServiceImpl implements CancellationTraceabilityAuditService{

    private final Logger log = LoggerFactory.getLogger(CancellationTraceabilityAuditServiceImpl.class);

    private final CancellationTraceabilityAuditRepository cancellationTraceabilityAuditRepository;

    private final CancellationTraceabilityAuditMapper cancellationTraceabilityAuditMapper;

    private final UserService userService;

    private final TraceabilityAuditService traceabilityAuditService;

    private final RecommendationRepository recommendationRepository;

    private final AuditProcessRecommendationRepository auditProcessRecommendationRepository;

    private final ApplicationProperties applicationProperties;

    public CancellationTraceabilityAuditServiceImpl(CancellationTraceabilityAuditRepository cancellationTraceabilityAuditRepository,
                                                    CancellationTraceabilityAuditMapper cancellationTraceabilityAuditMapper,
                                                    UserService userService, TraceabilityAuditService traceabilityAuditService,
                                                    RecommendationRepository recommendationRepository, AuditProcessRecommendationRepository auditProcessRecommendationRepository, ApplicationProperties applicationProperties) {
        this.cancellationTraceabilityAuditRepository = cancellationTraceabilityAuditRepository;
        this.cancellationTraceabilityAuditMapper = cancellationTraceabilityAuditMapper;
        this.userService = userService;
        this.traceabilityAuditService = traceabilityAuditService;
        this.recommendationRepository = recommendationRepository;
        this.auditProcessRecommendationRepository = auditProcessRecommendationRepository;
        this.applicationProperties = applicationProperties;
    }

    /**
     * Save a CancellationTraceabilityAudit.
     *
     * @param cancellationTraceabilityAuditDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CancellationTraceabilityAuditDTO save(CancellationTraceabilityAuditDTO cancellationTraceabilityAuditDTO) {
        log.debug("Request to save CancellationTraceabilityAudit : {}", cancellationTraceabilityAuditDTO);
        CancellationTraceabilityAudit cancellationTraceabilityAudit = cancellationTraceabilityAuditMapper.toEntity(cancellationTraceabilityAuditDTO);

        Optional<User> user = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin());

        cancellationTraceabilityAudit.setUser(user.get());

        cancellationTraceabilityAudit = cancellationTraceabilityAuditRepository.save(cancellationTraceabilityAudit);
        return cancellationTraceabilityAuditMapper.toDto(cancellationTraceabilityAudit);
    }

    /**
     *  Get all the CancellationTraceabilityAudits.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CancellationTraceabilityAuditDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CancellationTraceabilityAudits");
        return cancellationTraceabilityAuditRepository.findAll(pageable)
            .map(cancellationTraceabilityAuditMapper::toDto);
    }

    /**
     *  Get one CancellationTraceabilityAudit by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CancellationTraceabilityAuditDTO findOne(Long id) {
        log.debug("Request to get CancellationTraceabilityAudit : {}", id);
        CancellationTraceabilityAudit CancellationTraceabilityAudit = cancellationTraceabilityAuditRepository.findOne(id);
        return cancellationTraceabilityAuditMapper.toDto(CancellationTraceabilityAudit);
    }

    /**
     *  Delete the  CancellationTraceabilityAudit by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CancellationTraceabilityAudit : {}", id);
        cancellationTraceabilityAuditRepository.delete(id);
    }

    @Override
    public CancellationTraceabilityAuditDTO cancelTraceabilityAudit(CancellationTraceabilityAuditDTO cancellationTraceabilityAuditDTO) {

        cancellationTraceabilityAuditDTO.setUserLogin(SecurityUtils.getCurrentUserLogin());

        CancellationTraceabilityAuditDTO result = save(cancellationTraceabilityAuditDTO);

        TraceabilityAuditDTO traceabilityAuditDTO = traceabilityAuditService.findOne(cancellationTraceabilityAuditDTO.getTraceabilityAuditId());

        if (traceabilityAuditDTO.getStatus() == StatusTraceabilityAudit.IN_PROGRESS) {

            BonitaBPMService bonitaBPMService = new BonitaBPMService(applicationProperties);

            Set<Recommendation> recommendationSet = recommendationRepository.findAllByTraceabilityAudit_Id(traceabilityAuditDTO.getId());

            Recommendation recommendation = (Recommendation) recommendationSet.toArray()[0];

            Set<AuditProcessRecommendation> auditProcessRecommendationSet;

            auditProcessRecommendationSet = auditProcessRecommendationRepository.findAllByRecommendation_Id(recommendation.getId());

            for (AuditProcessRecommendation apr : auditProcessRecommendationSet) {

                bonitaBPMService.stopBPMProcess(apr);

            }

        }

        traceabilityAuditDTO.setStatus(StatusTraceabilityAudit.CANCELED);

        traceabilityAuditService.save(traceabilityAuditDTO);

        return result;

    }

}
