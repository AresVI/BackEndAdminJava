package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.RecommendationAttributeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RecommendationAttribute and its DTO RecommendationAttributeDTO.
 */
@Mapper(componentModel = "spring", uses = {WeightingMapper.class, RecommendationMapper.class, })
public interface RecommendationAttributeMapper extends EntityMapper <RecommendationAttributeDTO, RecommendationAttribute> {

    @Mapping(source = "weighting.id", target = "weightingId")

    @Mapping(source = "recommendation.id", target = "recommendationId")
    @Mapping(source = "recommendation.name", target = "recommendationName")
    RecommendationAttributeDTO toDto(RecommendationAttribute recommendationAttribute); 

    @Mapping(source = "weightingId", target = "weighting")

    @Mapping(source = "recommendationId", target = "recommendation")
    RecommendationAttribute toEntity(RecommendationAttributeDTO recommendationAttributeDTO); 
    default RecommendationAttribute fromId(Long id) {
        if (id == null) {
            return null;
        }
        RecommendationAttribute recommendationAttribute = new RecommendationAttribute();
        recommendationAttribute.setId(id);
        return recommendationAttribute;
    }
}
