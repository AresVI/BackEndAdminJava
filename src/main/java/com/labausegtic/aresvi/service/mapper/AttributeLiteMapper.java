package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.Attribute;
import com.labausegtic.aresvi.service.dto.AttributeLiteDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Attribute and its DTO AttributeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AttributeLiteMapper extends EntityMapper <AttributeLiteDTO, Attribute> {


    Attribute toEntity(AttributeLiteDTO attributeLiteDTO);
    default Attribute fromId(Long id) {
        if (id == null) {
            return null;
        }
        Attribute attribute = new Attribute();
        attribute.setId(id);
        return attribute;
    }
}
