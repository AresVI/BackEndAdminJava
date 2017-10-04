package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.AttributeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Attribute and its DTO AttributeDTO.
 */
@Mapper(componentModel = "spring", uses = {WeightingMapper.class, CategoryAttributeMapper.class, })
public interface AttributeMapper extends EntityMapper <AttributeDTO, Attribute> {

    @Mapping(source = "weighting.id", target = "weightingId")

    @Mapping(source = "categoryAttribute.id", target = "categoryAttributeId")
    AttributeDTO toDto(Attribute attribute); 

    @Mapping(source = "weightingId", target = "weighting")

    @Mapping(source = "categoryAttributeId", target = "categoryAttribute")
    Attribute toEntity(AttributeDTO attributeDTO); 
    default Attribute fromId(Long id) {
        if (id == null) {
            return null;
        }
        Attribute attribute = new Attribute();
        attribute.setId(id);
        return attribute;
    }
}
