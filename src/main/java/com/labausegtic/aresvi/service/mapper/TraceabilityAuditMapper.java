package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.TraceabilityAuditDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TraceabilityAudit and its DTO TraceabilityAuditDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class, CompanyContactPersonMapper.class})
public interface TraceabilityAuditMapper extends EntityMapper <TraceabilityAuditDTO, TraceabilityAudit> {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "companyContactPerson.id", target = "companyContactPersonId")
    TraceabilityAuditDTO toDto(TraceabilityAudit traceabilityAudit);

    @Mapping(target = "auditProcesses", ignore = true)
    @Mapping(source = "companyId", target = "company")
    @Mapping(source = "companyContactPersonId", target = "companyContactPerson")
    TraceabilityAudit toEntity(TraceabilityAuditDTO traceabilityAuditDTO);

    default TraceabilityAudit fromId(Long id) {
        if (id == null) {
            return null;
        }
        TraceabilityAudit traceabilityAudit = new TraceabilityAudit();
        traceabilityAudit.setId(id);
        return traceabilityAudit;
    }

}
