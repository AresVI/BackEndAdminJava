package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.Container;
import com.labausegtic.aresvi.service.dto.ContainerCompleteDTO;
import com.labausegtic.aresvi.service.dto.ContainerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Container and its DTO ContainerDTO.
 */
@Mapper(componentModel = "spring", uses = {ParticipantMapper.class, AuditProcessMapper.class, })
public interface ContainerCompleteMapper extends EntityMapper <ContainerCompleteDTO, Container> {

    @Mapping(source = "participant.id", target = "participantId")
    @Mapping(source = "auditProcess.id", target = "auditProcessId")
    ContainerCompleteDTO toDto(Container container);

    @Mapping(source = "participantId", target = "participant")
    @Mapping(source = "auditProcessId", target = "auditProcess")
    Container toEntity(ContainerCompleteDTO containerCompleteDTO);

    default Container fromId(Long id) {
        if (id == null) {
            return null;
        }
        Container container = new Container();
        container.setId(id);
        return container;
    }
}
