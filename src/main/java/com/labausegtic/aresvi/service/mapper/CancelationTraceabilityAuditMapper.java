package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.CancelationTraceabilityAuditDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CancelationTraceabilityAudit and its DTO CancelationTraceabilityAuditDTO.
 */
@Mapper(componentModel = "spring", uses = {TraceabilityAuditMapper.class, UserMapper.class, })
public interface CancelationTraceabilityAuditMapper extends EntityMapper <CancelationTraceabilityAuditDTO, CancelationTraceabilityAudit> {

    @Mapping(source = "traceabilityAudit.id", target = "traceabilityAuditId")
    @Mapping(source = "traceabilityAudit.name", target = "traceabilityAuditName")

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    CancelationTraceabilityAuditDTO toDto(CancelationTraceabilityAudit cancelationTraceabilityAudit); 

    @Mapping(source = "traceabilityAuditId", target = "traceabilityAudit")

    @Mapping(source = "userId", target = "user")
    CancelationTraceabilityAudit toEntity(CancelationTraceabilityAuditDTO cancelationTraceabilityAuditDTO); 
    default CancelationTraceabilityAudit fromId(Long id) {
        if (id == null) {
            return null;
        }
        CancelationTraceabilityAudit cancelationTraceabilityAudit = new CancelationTraceabilityAudit();
        cancelationTraceabilityAudit.setId(id);
        return cancelationTraceabilityAudit;
    }
}
