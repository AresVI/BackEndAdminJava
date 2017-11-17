package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.AuditTask;
import com.labausegtic.aresvi.service.dto.AuditTaskCompleteDTO;
import com.labausegtic.aresvi.service.dto.AuditTaskDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity AuditTask and its DTO AuditTaskDTO.
 */
@Mapper(componentModel = "spring", uses = {ContainerMapper.class, })
public interface AuditTaskCompleteMapper extends EntityMapper <AuditTaskCompleteDTO, AuditTask> {

    @Mapping(source = "container.id", target = "containerId")
    AuditTaskCompleteDTO toDto(AuditTask auditTask);

    @Mapping(source = "containerId", target = "container")
    AuditTask toEntity(AuditTaskCompleteDTO auditTaskCompleteDTO);

    default AuditTask fromId(Long id) {
        if (id == null) {
            return null;
        }
        AuditTask auditTask = new AuditTask();
        auditTask.setId(id);
        return auditTask;
    }
}
