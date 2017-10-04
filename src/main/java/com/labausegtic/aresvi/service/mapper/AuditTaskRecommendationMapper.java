package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.AuditTaskRecommendationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AuditTaskRecommendation and its DTO AuditTaskRecommendationDTO.
 */
@Mapper(componentModel = "spring", uses = {AuditProcessRecommendationMapper.class, AuditTaskMapper.class, })
public interface AuditTaskRecommendationMapper extends EntityMapper <AuditTaskRecommendationDTO, AuditTaskRecommendation> {

    @Mapping(source = "auditProcessRecom.id", target = "auditProcessRecomId")

    @Mapping(source = "auditTask.id", target = "auditTaskId")
    AuditTaskRecommendationDTO toDto(AuditTaskRecommendation auditTaskRecommendation); 

    @Mapping(source = "auditProcessRecomId", target = "auditProcessRecom")

    @Mapping(source = "auditTaskId", target = "auditTask")
    AuditTaskRecommendation toEntity(AuditTaskRecommendationDTO auditTaskRecommendationDTO); 
    default AuditTaskRecommendation fromId(Long id) {
        if (id == null) {
            return null;
        }
        AuditTaskRecommendation auditTaskRecommendation = new AuditTaskRecommendation();
        auditTaskRecommendation.setId(id);
        return auditTaskRecommendation;
    }
}
