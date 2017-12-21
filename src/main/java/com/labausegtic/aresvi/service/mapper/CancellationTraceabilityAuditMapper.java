package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.CancellationTraceabilityAuditDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CancellationTraceabilityAudit and its DTO CancellationTraceabilityAuditDTO.
 */
@Mapper(componentModel = "spring", uses = {TraceabilityAuditMapper.class, UserMapper.class, })
public interface CancellationTraceabilityAuditMapper extends EntityMapper <CancellationTraceabilityAuditDTO, CancellationTraceabilityAudit> {

    @Mapping(source = "traceabilityAudit.id", target = "traceabilityAuditId")
    @Mapping(source = "traceabilityAudit.name", target = "traceabilityAuditName")

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    CancellationTraceabilityAuditDTO toDto(CancellationTraceabilityAudit cancellationTraceabilityAudit);

    @Mapping(source = "traceabilityAuditId", target = "traceabilityAudit")

    @Mapping(source = "userId", target = "user")
    CancellationTraceabilityAudit toEntity(CancellationTraceabilityAuditDTO cancellationTraceabilityAuditDTO);
    default CancellationTraceabilityAudit fromId(Long id) {
        if (id == null) {
            return null;
        }
        CancellationTraceabilityAudit cancellationTraceabilityAudit = new CancellationTraceabilityAudit();
        cancellationTraceabilityAudit.setId(id);
        return cancellationTraceabilityAudit;
    }
}
