package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.Attribute;
import com.labausegtic.aresvi.service.dto.AttributeCompleteDTO;
import com.labausegtic.aresvi.service.dto.AttributeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Attribute and its DTO AttributeDTO.
 */
@Mapper(componentModel = "spring", uses = {WeightingMapper.class, CategoryAttributeMapper.class, })
public interface AttributeCompleteMapper extends EntityMapper <AttributeCompleteDTO, Attribute> {

    @Mapping(source = "weighting.id", target = "weightingId")

    @Mapping(source = "categoryAttribute.id", target = "categoryAttributeId")
    AttributeCompleteDTO toDto(Attribute attribute);

    @Mapping(source = "weightingId", target = "weighting")

    @Mapping(source = "categoryAttributeId", target = "categoryAttribute")
    Attribute toEntity(AttributeCompleteDTO attributeCompleteDTO);
    default Attribute fromId(Long id) {
        if (id == null) {
            return null;
        }
        Attribute attribute = new Attribute();
        attribute.setId(id);
        return attribute;
    }
}
