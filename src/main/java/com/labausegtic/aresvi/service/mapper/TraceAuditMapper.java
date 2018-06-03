package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.TraceAuditDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TraceAudit and its DTO TraceAuditDTO.
 */
@Mapper(componentModel = "spring", uses = {TraceabilityAuditMapper.class, AuditTaskRecommendationMapper.class, })
public interface TraceAuditMapper extends EntityMapper <TraceAuditDTO, TraceAudit> {

    @Mapping(source = "traceabilityAudit.id", target = "traceabilityAuditId")

    @Mapping(source = "auditTaskRecommendation.id", target = "auditTaskRecommendationId")
    TraceAuditDTO toDto(TraceAudit traceAudit); 

    @Mapping(source = "traceabilityAuditId", target = "traceabilityAudit")

    @Mapping(source = "auditTaskRecommendationId", target = "auditTaskRecommendation")
    TraceAudit toEntity(TraceAuditDTO traceAuditDTO); 
    default TraceAudit fromId(Long id) {
        if (id == null) {
            return null;
        }
        TraceAudit traceAudit = new TraceAudit();
        traceAudit.setId(id);
        return traceAudit;
    }
}
