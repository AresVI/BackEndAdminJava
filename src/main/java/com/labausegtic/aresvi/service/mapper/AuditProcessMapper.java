package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.AuditProcessDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AuditProcess and its DTO AuditProcessDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AuditProcessMapper extends EntityMapper <AuditProcessDTO, AuditProcess> {

    default AuditProcess fromId(Long id) {
        if (id == null) {
            return null;
        }
        AuditProcess auditProcess = new AuditProcess();
        auditProcess.setId(id);
        return auditProcess;
    }
}
