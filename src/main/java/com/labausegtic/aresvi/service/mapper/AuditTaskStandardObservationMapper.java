package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.AuditTaskStandardObservationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AuditTaskStandardObservation and its DTO AuditTaskStandardObservationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AuditTaskStandardObservationMapper extends EntityMapper <AuditTaskStandardObservationDTO, AuditTaskStandardObservation> {
    
    
    default AuditTaskStandardObservation fromId(Long id) {
        if (id == null) {
            return null;
        }
        AuditTaskStandardObservation auditTaskStandardObservation = new AuditTaskStandardObservation();
        auditTaskStandardObservation.setId(id);
        return auditTaskStandardObservation;
    }
}
