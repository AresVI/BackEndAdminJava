package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.AttributeRecommendationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AttributeRecommendation and its DTO AttributeRecommendationDTO.
 */
@Mapper(componentModel = "spring", uses = {CategoryAttRecommendationMapper.class, AttributeMapper.class, })
public interface AttributeRecommendationMapper extends EntityMapper <AttributeRecommendationDTO, AttributeRecommendation> {

    @Mapping(source = "categoryAttRecom.id", target = "categoryAttRecomId")

    @Mapping(source = "attribute.id", target = "attributeId")
    AttributeRecommendationDTO toDto(AttributeRecommendation attributeRecommendation); 

    @Mapping(source = "categoryAttRecomId", target = "categoryAttRecom")

    @Mapping(source = "attributeId", target = "attribute")
    AttributeRecommendation toEntity(AttributeRecommendationDTO attributeRecommendationDTO); 
    default AttributeRecommendation fromId(Long id) {
        if (id == null) {
            return null;
        }
        AttributeRecommendation attributeRecommendation = new AttributeRecommendation();
        attributeRecommendation.setId(id);
        return attributeRecommendation;
    }
}
