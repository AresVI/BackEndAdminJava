package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.AuditProcessRecommendationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AuditProcessRecommendation and its DTO AuditProcessRecommendationDTO.
 */
@Mapper(componentModel = "spring", uses = {RecommendationMapper.class, AuditProcessMapper.class, })
public interface AuditProcessRecommendationMapper extends EntityMapper <AuditProcessRecommendationDTO, AuditProcessRecommendation> {

    @Mapping(source = "recommendation.id", target = "recommendationId")

    @Mapping(source = "auditProcess.id", target = "auditProcessId")
    AuditProcessRecommendationDTO toDto(AuditProcessRecommendation auditProcessRecommendation); 

    @Mapping(source = "recommendationId", target = "recommendation")

    @Mapping(source = "auditProcessId", target = "auditProcess")
    AuditProcessRecommendation toEntity(AuditProcessRecommendationDTO auditProcessRecommendationDTO); 
    default AuditProcessRecommendation fromId(Long id) {
        if (id == null) {
            return null;
        }
        AuditProcessRecommendation auditProcessRecommendation = new AuditProcessRecommendation();
        auditProcessRecommendation.setId(id);
        return auditProcessRecommendation;
    }
}
