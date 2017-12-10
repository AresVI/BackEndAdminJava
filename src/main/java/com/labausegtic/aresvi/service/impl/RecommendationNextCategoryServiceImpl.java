package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.*;
import com.labausegtic.aresvi.service.dto.*;
import com.labausegtic.aresvi.service.mapper.AttributeRecommendationMapper;
import com.labausegtic.aresvi.service.mapper.CategoryAttributeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RecommendationNextCategoryServiceImpl implements RecommendationNextCategoryService {

    private final BRMSService brmsService;

    private final TraceabilityAuditService traceabilityAuditService;

    private final AuditAttributeAnalysisService auditAttributeAnalysisService;

    private final AttributeService attributeService;

    private final AttributeRecommendationService attributeRecommendationService;

    private final AttributeRecommendationMapper attributeRecommendationMapper;

    private final CategoryAttributeMapper categoryAttributeMapper;

    public RecommendationNextCategoryServiceImpl(BRMSService brmsService, TraceabilityAuditService traceabilityAuditService,
                                                 AuditAttributeAnalysisService auditAttributeAnalysisService,
                                                 AttributeService attributeService, AttributeRecommendationService attributeRecommendationService,
                                                 AttributeRecommendationMapper attributeRecommendationMapper,
                                                 CategoryAttributeMapper categoryAttributeMapper) {
        this.brmsService = brmsService;
        this.traceabilityAuditService = traceabilityAuditService;
        this.auditAttributeAnalysisService = auditAttributeAnalysisService;
        this.attributeService = attributeService;
        this.attributeRecommendationService = attributeRecommendationService;
        this.attributeRecommendationMapper = attributeRecommendationMapper;
        this.categoryAttributeMapper = categoryAttributeMapper;
    }

    public List<AuditProcessRecommendationDTO>  getRecommendationNextCategory(Long company_id) {

        TraceabilityAuditDTO traceabilityAuditDTO = traceabilityAuditService.findLastByCompanyId(company_id);

        if ( traceabilityAuditDTO != null) {

            AuditAttributeAnalysisDTO attributeAnalysisDTO;

            attributeAnalysisDTO = auditAttributeAnalysisService.getOneByTraceabilityAuditId(traceabilityAuditDTO.getId());

            RecommendationParameterNextCategoryDTO recommendationParameterNextCategoryDTO = new RecommendationParameterNextCategoryDTO();

            recommendationParameterNextCategoryDTO.setCategory(traceabilityAuditDTO.getCategory());

            recommendationParameterNextCategoryDTO.setPercentageNotRequired(attributeAnalysisDTO.getPercentageNotRequired());

            recommendationParameterNextCategoryDTO.setPercentageLevel1(attributeAnalysisDTO.getPercentageLevel1());
            recommendationParameterNextCategoryDTO.setPercentageLevel2(attributeAnalysisDTO.getPercentageLevel2());
            recommendationParameterNextCategoryDTO.setPercentageLevel3(attributeAnalysisDTO.getPercentageLevel3());
            recommendationParameterNextCategoryDTO.setPercentageLevel4(attributeAnalysisDTO.getPercentageLevel4());
            recommendationParameterNextCategoryDTO.setPercentageLevel5(attributeAnalysisDTO.getPercentageLevel5());

            ResultRecommendationNextCategoryDTO recommendationNextCategory;

            recommendationNextCategory = brmsService.getRecommendationNextCategory(recommendationParameterNextCategoryDTO);

            return getRecommendation(getAllAttributesByLevel(recommendationNextCategory.getLevel()));

        } else {
            return null;
        }

    }

    private List<Attribute> getAllAttributesByLevel(Long level){

        if(level != null) {

            return attributeService.getByWeighting(level, level.compareTo(0L) != 0);

        } else {
            return new ArrayList<>();
        }

    }

    private List<AuditProcessRecommendationDTO> getRecommendation(List<Attribute> attributeList){

        RecommendationDTO result = new RecommendationDTO();

        List<AttributeRecommendation> attributeRecommendationList;

        attributeRecommendationList = attributeRecommendationService.findAllInAttributeListAndNotImplemented(attributeList);

        List<Long> categoryAttrRecommendations = new ArrayList<>();
        List<CategoryAttrRecommendationDTO> categoryAttrRecommendationDTOList = new ArrayList<>();

        for (AttributeRecommendation ar: attributeRecommendationList) {

            CategoryAttrRecommendation categoryAttRecom = ar.getCategoryAttRecom();

            int index_car = categoryAttrRecommendations.indexOf(categoryAttRecom.getCategoryAttribute().getId());

            if ( index_car >  -1 ){

                categoryAttrRecommendationDTOList.get(index_car).addAttributeRecommendationSet(
                    attributeRecommendationMapper.toDto(ar)
                );

            } else {

                categoryAttrRecommendations.add(categoryAttRecom.getCategoryAttribute().getId());

                CategoryAttrRecommendationDTO categoryAttrRecommendationDTO = new CategoryAttrRecommendationDTO();

                categoryAttrRecommendationDTO.setCategoryAttribute(
                    categoryAttributeMapper.toDto(categoryAttRecom.getCategoryAttribute())
                );

                categoryAttrRecommendationDTO.setCategoryAttributeId(categoryAttRecom.getCategoryAttribute().getId());

                categoryAttrRecommendationDTO.addAttributeRecommendationSet(
                    attributeRecommendationMapper.toDto(ar)
                );

                categoryAttrRecommendationDTOList.add(categoryAttrRecommendationDTO);

            }

        }

        List<Long> auditTaskRecommendations = new ArrayList<>();
        List<AuditTaskRecommendationDTO> auditTaskRecommendationDTOList = new ArrayList<>();

        for (CategoryAttrRecommendationDTO car: categoryAttrRecommendationDTOList) {

            AuditTaskDTO auditTask = car.getCategoryAttribute().getAuditTask();

            int index_atr = auditTaskRecommendations.indexOf(auditTask.getId());

            if ( index_atr >  -1 ){

                auditTaskRecommendationDTOList.get(index_atr).addCategoryAttrRecommendationSet(car);

            } else {

                auditTaskRecommendations.add(auditTask.getId());

                AuditTaskRecommendationDTO auditTaskRecommendationDTO = new AuditTaskRecommendationDTO();

                auditTaskRecommendationDTO.setAuditTask(auditTask);

                auditTaskRecommendationDTO.setAuditTaskId(auditTask.getId());

                auditTaskRecommendationDTO.addCategoryAttrRecommendationSet(car);

                auditTaskRecommendationDTOList.add(auditTaskRecommendationDTO);

            }

        }

        List<Long> auditProcessRecommendations = new ArrayList<>();
        List<AuditProcessRecommendationDTO> auditProcessRecommendationDTOList = new ArrayList<>();

        for (AuditTaskRecommendationDTO atr: auditTaskRecommendationDTOList) {

            AuditProcessDTO auditProcess = atr.getAuditTask().getContainer().getAuditProcess();

            int index_apr = auditProcessRecommendations.indexOf(auditProcess.getId());

            if ( index_apr >  -1 ){

                auditProcessRecommendationDTOList.get(index_apr).addAuditTaskRecommendationSet(atr);

            } else {

                auditProcessRecommendations.add(auditProcess.getId());

                AuditProcessRecommendationDTO auditProcessRecommendationDTO = new AuditProcessRecommendationDTO();

                auditProcessRecommendationDTO.setAuditProcess(auditProcess);

                auditProcessRecommendationDTO.setAuditProcessId(auditProcess.getId());

                auditProcessRecommendationDTO.addAuditTaskRecommendationSet(atr);

                auditProcessRecommendationDTOList.add(auditProcessRecommendationDTO);

            }

        }

        return auditProcessRecommendationDTOList;

    }

}
