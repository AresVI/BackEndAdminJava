package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.AuditTaskDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AuditTask and its DTO AuditTaskDTO.
 */
@Mapper(componentModel = "spring", uses = {ContainerMapper.class, })
public interface AuditTaskMapper extends EntityMapper <AuditTaskDTO, AuditTask> {

    @Mapping(source = "container.id", target = "containerId")
    AuditTaskDTO toDto(AuditTask auditTask);

    @Mapping(source = "containerId", target = "container")
    AuditTask toEntity(AuditTaskDTO auditTaskDTO);

    default AuditTask fromId(Long id) {
        if (id == null) {
            return null;
        }
        AuditTask auditTask = new AuditTask();
        auditTask.setId(id);
        return auditTask;
    }
}
