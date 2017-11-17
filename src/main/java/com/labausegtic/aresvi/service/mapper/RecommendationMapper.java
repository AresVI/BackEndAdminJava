package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.RecommendationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Recommendation and its DTO RecommendationDTO.
 */
@Mapper(componentModel = "spring", uses = {TraceabilityAuditMapper.class, AuditorMapper.class, AuditProcessMapper.class})
public interface RecommendationMapper extends EntityMapper <RecommendationDTO, Recommendation> {

    @Mapping(source = "traceabilityAudit.id", target = "traceabilityAuditId")
    @Mapping(source = "auditor.id", target = "auditorId")
    RecommendationDTO toDto(Recommendation recommendation);

    @Mapping(source = "traceabilityAuditId", target = "traceabilityAudit")
    @Mapping(source = "auditorId", target = "auditor")
    Recommendation toEntity(RecommendationDTO recommendationDTO);
    default Recommendation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Recommendation recommendation = new Recommendation();
        recommendation.setId(id);
        return recommendation;
    }
}
