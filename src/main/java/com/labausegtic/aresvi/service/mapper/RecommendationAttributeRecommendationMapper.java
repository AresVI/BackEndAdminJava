package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.RecommendationAttributeRecommendationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RecommendationAttributeRecommendation and its DTO RecommendationAttributeRecommendationDTO.
 */
@Mapper(componentModel = "spring", uses = {RecommendationAttributeMapper.class, RecommendationMapper.class, })
public interface RecommendationAttributeRecommendationMapper extends EntityMapper <RecommendationAttributeRecommendationDTO, RecommendationAttributeRecommendation> {

    @Mapping(source = "recommendationAttribute.id", target = "recommendationAttributeId")
    @Mapping(source = "recommendationAttribute.name", target = "recommendationAttributeName")

    @Mapping(source = "recommendation.id", target = "recommendationId")
    @Mapping(source = "recommendation.name", target = "recommendationName")
    RecommendationAttributeRecommendationDTO toDto(RecommendationAttributeRecommendation recommendationAttributeRecommendation); 

    @Mapping(source = "recommendationAttributeId", target = "recommendationAttribute")

    @Mapping(source = "recommendationId", target = "recommendation")
    RecommendationAttributeRecommendation toEntity(RecommendationAttributeRecommendationDTO recommendationAttributeRecommendationDTO); 
    default RecommendationAttributeRecommendation fromId(Long id) {
        if (id == null) {
            return null;
        }
        RecommendationAttributeRecommendation recommendationAttributeRecommendation = new RecommendationAttributeRecommendation();
        recommendationAttributeRecommendation.setId(id);
        return recommendationAttributeRecommendation;
    }
}
