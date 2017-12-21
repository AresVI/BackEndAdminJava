package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.AuditAttributeAnalysisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AuditAttributeAnalysis and its DTO AuditAttributeAnalysisDTO.
 */
@Mapper(componentModel = "spring", uses = {TraceabilityAuditMapper.class, })
public interface AuditAttributeAnalysisMapper extends EntityMapper <AuditAttributeAnalysisDTO, AuditAttributeAnalysis> {

    @Mapping(source = "traceabilityAudit.id", target = "traceabilityAuditId")
    AuditAttributeAnalysisDTO toDto(AuditAttributeAnalysis auditAttributeAnalysis);

    @Mapping(source = "traceabilityAuditId", target = "traceabilityAudit")
    AuditAttributeAnalysis toEntity(AuditAttributeAnalysisDTO auditAttributeAnalysisDTO);
    default AuditAttributeAnalysis fromId(Long id) {
        if (id == null) {
            return null;
        }
        AuditAttributeAnalysis auditAttributeAnalysis = new AuditAttributeAnalysis();
        auditAttributeAnalysis.setId(id);
        return auditAttributeAnalysis;
    }
}
