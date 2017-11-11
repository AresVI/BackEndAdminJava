package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.BRMSService;
import com.labausegtic.aresvi.service.UserService;
import com.labausegtic.aresvi.service.dto.InferenceParameterDTO;
import com.labausegtic.aresvi.service.dto.ResultInferenceDTO;
import com.labausegtic.aresvi.repository.*;
import com.labausegtic.aresvi.security.SecurityUtils;
import com.labausegtic.aresvi.service.TraceabilityAuditService;
import com.labausegtic.aresvi.service.dto.TraceabilityAuditDTO;
import com.labausegtic.aresvi.service.mapper.TraceabilityAuditMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;


/**
 * Service Implementation for managing TraceabilityAudit.
 */
@Service
@Transactional
public class TraceabilityAuditServiceImpl implements TraceabilityAuditService{

    private final Logger log = LoggerFactory.getLogger(TraceabilityAuditServiceImpl.class);

    private final TraceabilityAuditRepository traceabilityAuditRepository;

    private final RecommendationRepository recommendationRepository;

    private final AuditProcessRecommendationRepository auditProcessRecommendationRepository;

    private final ContainerRepository containerRepository;

    private final AuditTaskRepository auditTaskRepository;

    private final AuditTaskRecommendationRepository auditTaskRecommendationRepository;

    private final CategoryAttributeRepository categoryAttributeRepository;

    private final CategoryAttrRecommendationRepository categoryAttrRecommendationRepository;

    private final AttributeRepository attributeRepository;

    private final AttributeRecommendationRepository attributeRecommendationRepository;

    private final BRMSService brmsService;

    private final TraceabilityAuditMapper traceabilityAuditMapper;

    private final UserService userService;

    private final AuditorRepository auditorRepository;

    public TraceabilityAuditServiceImpl(TraceabilityAuditRepository traceabilityAuditRepository,
                                        RecommendationRepository recommendationRepository,
                                        AuditProcessRecommendationRepository auditProcessRecommendationRepository,
                                        ContainerRepository containerRepository, AuditTaskRepository auditTaskRepository,
                                        AuditTaskRecommendationRepository auditTaskRecommendationRepository,
                                        CategoryAttributeRepository categoryAttributeRepository,
                                        CategoryAttrRecommendationRepository categoryAttrRecommendationRepository,
                                        AttributeRepository attributeRepository,
                                        AttributeRecommendationRepository attributeRecommendationRepository,
                                        BRMSService brmsService, TraceabilityAuditMapper traceabilityAuditMapper,
                                        UserService userService, AuditorRepository auditorRepository) {
        this.traceabilityAuditRepository = traceabilityAuditRepository;
        this.recommendationRepository = recommendationRepository;
        this.auditProcessRecommendationRepository = auditProcessRecommendationRepository;
        this.containerRepository = containerRepository;
        this.auditTaskRepository = auditTaskRepository;
        this.auditTaskRecommendationRepository = auditTaskRecommendationRepository;
        this.categoryAttributeRepository = categoryAttributeRepository;
        this.categoryAttrRecommendationRepository = categoryAttrRecommendationRepository;
        this.attributeRepository = attributeRepository;
        this.attributeRecommendationRepository = attributeRecommendationRepository;
        this.brmsService = brmsService;
        this.traceabilityAuditMapper = traceabilityAuditMapper;
        this.userService = userService;
        this.auditorRepository = auditorRepository;
    }

    /**
     * Save a traceabilityAudit.
     *
     * @param traceabilityAuditDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TraceabilityAuditDTO save(TraceabilityAuditDTO traceabilityAuditDTO) {
        log.debug("Request to save TraceabilityAudit : {}", traceabilityAuditDTO);
        TraceabilityAudit traceabilityAudit = traceabilityAuditMapper.toEntity(traceabilityAuditDTO);
        log.debug(traceabilityAudit.toString());
        traceabilityAudit = traceabilityAuditRepository.save(traceabilityAudit);
        return traceabilityAuditMapper.toDto(traceabilityAudit);
    }

    /**
     *  Get all the traceabilityAudits.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TraceabilityAuditDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TraceabilityAudits");
        return traceabilityAuditRepository.findAll(pageable)
            .map(traceabilityAuditMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TraceabilityAuditDTO> findAllByStatus(Pageable pageable, String status) {
        log.debug("Request to get all TraceabilityAudits");
        return traceabilityAuditRepository.findAllByStatus(pageable, status)
            .map(traceabilityAuditMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TraceabilityAuditDTO> findAllFinishedByCategoryAndCompany(Pageable pageable, String category,Long company_id) {
        log.debug("Request to get all TraceabilityAudits");

        String status = "STATUS_FINISHED";

        if (category == null && company_id == 0L) {
            return traceabilityAuditRepository.findAllByStatus(pageable, status)
                .map(traceabilityAuditMapper::toDto);
        }

        if (company_id == 0L) {
            return traceabilityAuditRepository.findAllByCategoryAndStatus(pageable, category, status)
                .map(traceabilityAuditMapper::toDto);
        }

        if (category == null) {
            return traceabilityAuditRepository.findAllByCompanyIdAndStatus(pageable, company_id, status)
                .map(traceabilityAuditMapper::toDto);
        }

        return traceabilityAuditRepository.findAllByCompanyIdAndCategoryAndStatus(pageable, company_id, category, status)
            .map(traceabilityAuditMapper::toDto);
    }

    /**
     *  Get one traceabilityAudit by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TraceabilityAuditDTO findOne(Long id) {
        log.debug("Request to get TraceabilityAudit : {}", id);
        TraceabilityAudit traceabilityAudit = traceabilityAuditRepository.findOne(id);
        return traceabilityAuditMapper.toDto(traceabilityAudit);
    }

    @Override
    @Transactional(readOnly = true)
    public TraceabilityAuditDTO findAllLastByCompanyId(Long company_id) {

        Set<TraceabilityAudit> traceabilityAuditSet = traceabilityAuditRepository.findByCompanyIdOrderByCreationDateDesc(company_id);

        if (traceabilityAuditSet.size() > 0) {
            return traceabilityAuditMapper.toDto((TraceabilityAudit)(traceabilityAuditSet.toArray()[0]));
        } else {
            return null;
        }

    }

    /**
     *  Delete the  traceabilityAudit by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TraceabilityAudit : {}", id);
        traceabilityAuditRepository.delete(id);
    }

    @Override
    public TraceabilityAuditDTO startTraceabilityAudit(Long id) {

        TraceabilityAudit traceabilityAudit = traceabilityAuditRepository.findOne(id);

        Recommendation recommendation = new Recommendation();

        recommendation.setTraceabilityAudit(traceabilityAudit);

        recommendation.setCreationDate(Instant.now());

        recommendation.setName("Recomendación para la " + traceabilityAudit.getName());

        Optional<User> user = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin());

        recommendation.setAuditor(auditorRepository.findAuditorByUser_Id(user.get().getId()));

        recommendation.setReviewed(false);

        recommendation = recommendationRepository.save(recommendation);

        Set<AuditProcess> auditProcessSet = traceabilityAudit.getAuditProcesses();

        for (AuditProcess ap : auditProcessSet) {

            AuditProcessRecommendation auditProcessRecommendation = new AuditProcessRecommendation();

            auditProcessRecommendation.setAuditProcess(ap);

            auditProcessRecommendation.setDescription("");

            auditProcessRecommendation.setReviewed(false);

            auditProcessRecommendation.setRecommendation(recommendation);

            auditProcessRecommendationRepository.save(auditProcessRecommendation);

            Set<Container> containers = containerRepository.findAllByAuditProcess_Id(ap.getId());

            for (Container c : containers){

                Set<AuditTask> auditTasks = auditTaskRepository.findAllByContainer_Id(c.getId());

                for (AuditTask at : auditTasks) {

                    AuditTaskRecommendation auditTaskRecommendation = new AuditTaskRecommendation();

                    auditTaskRecommendation.setAuditProcessRecom(auditProcessRecommendation);

                    auditTaskRecommendation.setAuditTask(at);

                    auditTaskRecommendation.setDescription("");

                    auditTaskRecommendation.setReviewed(false);

                    auditTaskRecommendationRepository.save(auditTaskRecommendation);

                    Set<CategoryAttribute> categoryAttributes = categoryAttributeRepository.findAllByAuditTask_Id(at.getId());

                    for ( CategoryAttribute ca : categoryAttributes ) {

                        CategoryAttrRecommendation categoryAttrRecommendation = new CategoryAttrRecommendation();

                        categoryAttrRecommendation.setAuditTaskRecom(auditTaskRecommendation);

                        categoryAttrRecommendation.setCategoryAttribute(ca);

                        categoryAttrRecommendation.setDescription("");

                        categoryAttrRecommendationRepository.save(categoryAttrRecommendation);

                        Set<Attribute> attributes = attributeRepository.findAllByCategoryAttribute_Id(ca.getId());

                        for (Attribute a : attributes) {

                            AttributeRecommendation attributeRecommendation = new AttributeRecommendation();

                            attributeRecommendation.setCategoryAttRecom(categoryAttrRecommendation);

                            attributeRecommendation.setAttribute(a);

                            attributeRecommendation.setDescription("");

                            attributeRecommendationRepository.save(attributeRecommendation);

                        }

                    }

                }

            }

        }

        traceabilityAudit.setStatus(StatusTraceabilityAudit.IN_PROGRESS);

        traceabilityAuditRepository.save(traceabilityAudit);

        return traceabilityAuditMapper.toDto(traceabilityAudit);

    }

    @Override
    public TraceabilityAuditDTO finishTraceabilityAudit(Long id) {

        TraceabilityAudit traceabilityAudit = traceabilityAuditRepository.findOne(id);

        traceabilityAudit.setStatus(StatusTraceabilityAudit.FINISHED);

        Set<AttributeRecommendation> attributeRecommendationSet;

        attributeRecommendationSet = attributeRecommendationRepository.findAllForTraceabilityAuditId(traceabilityAudit.getId());

        double countNotRequired = 0, totalNotRequired = 0,
            countLevel1 = 0, totalLevel1 = 0,
            countLevel2 = 0, totalLevel2 = 0,
            countLevel3 = 0, totalLevel3 = 0,
            countLevel4 = 0, totalLevel4 = 0,
            countLevel5 = 0, totalLevel5 = 0;

        for (AttributeRecommendation ar : attributeRecommendationSet) {

            Attribute a = ar.getAttribute();

            if (a.isRequired()) {

                Weighting w = a.getWeighting();

                switch (w.getValue()){
                    case 1:
                        if (ar.isImplemented()) {
                            countLevel1 += 1;
                        }
                        totalLevel1 += 1;
                    break;
                    case 2:
                        if (ar.isImplemented()) {
                            countLevel2 += 1;
                        }
                        totalLevel2 += 1;
                    break;
                    case 3:
                        if (ar.isImplemented()) {
                            countLevel3 += 1;
                        }
                        totalLevel3 += 1;
                    break;
                    case 4:
                        if (ar.isImplemented()) {
                            countLevel4 += 1;
                        }
                        totalLevel4 += 1;
                    break;
                    case 5:
                        if (ar.isImplemented()) {
                            countLevel5 += 1;
                        }
                        totalLevel5 += 1;
                    break;
                }

            } else {

                if (ar.isImplemented()) {
                    countNotRequired += 1;
                }
                totalNotRequired += 1;

            }

        }

        InferenceParameterDTO inferenceParameterDTO = new InferenceParameterDTO();

        Set<Recommendation> recommendationSet = recommendationRepository.findAllByTraceabilityAudit_Id(traceabilityAudit.getId());

        Integer levelComputerization = ((Recommendation) recommendationSet.toArray()[0]).getLevelComputerization();

        inferenceParameterDTO.setLevelComputerization(levelComputerization);

        inferenceParameterDTO.setPercentageNotRequired(
            (countNotRequired/totalNotRequired)
        );

        inferenceParameterDTO.setPercentageLevel1(
            countLevel1/totalLevel1
        );

        inferenceParameterDTO.setPercentageLevel2(
            countLevel2/totalLevel2
        );

        inferenceParameterDTO.setPercentageLevel3(
            countLevel3/totalLevel3
        );

        inferenceParameterDTO.setPercentageLevel4(
            countLevel4/totalLevel4
        );

        inferenceParameterDTO.setPercentageLevel5(
            countLevel5/totalLevel5
        );

        ResultInferenceDTO category = brmsService.getCategory(inferenceParameterDTO);

        traceabilityAudit.setCategory(category.getCategory());

        traceabilityAuditRepository.save(traceabilityAudit);

        return traceabilityAuditMapper.toDto(traceabilityAudit);

    }
}
