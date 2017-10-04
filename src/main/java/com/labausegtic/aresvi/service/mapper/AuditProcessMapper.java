package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.AuditProcessDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AuditProcess and its DTO AuditProcessDTO.
 */
@Mapper(componentModel = "spring", uses = {TraceabilityAuditMapper.class, })
public interface AuditProcessMapper extends EntityMapper <AuditProcessDTO, AuditProcess> {

    @Mapping(source = "traceabilityAudit.id", target = "traceabilityAuditId")
    AuditProcessDTO toDto(AuditProcess auditProcess); 

    @Mapping(source = "traceabilityAuditId", target = "traceabilityAudit")
    AuditProcess toEntity(AuditProcessDTO auditProcessDTO); 
    default AuditProcess fromId(Long id) {
        if (id == null) {
            return null;
        }
        AuditProcess auditProcess = new AuditProcess();
        auditProcess.setId(id);
        return auditProcess;
    }
}
