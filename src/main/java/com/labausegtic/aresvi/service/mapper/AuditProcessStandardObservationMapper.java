package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.AuditProcessStandardObservationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AuditProcessStandardObservation and its DTO AuditProcessStandardObservationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AuditProcessStandardObservationMapper extends EntityMapper <AuditProcessStandardObservationDTO, AuditProcessStandardObservation> {
    
    
    default AuditProcessStandardObservation fromId(Long id) {
        if (id == null) {
            return null;
        }
        AuditProcessStandardObservation auditProcessStandardObservation = new AuditProcessStandardObservation();
        auditProcessStandardObservation.setId(id);
        return auditProcessStandardObservation;
    }
}
