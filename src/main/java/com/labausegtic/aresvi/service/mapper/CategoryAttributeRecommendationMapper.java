package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.CategoryAttributeRecommendationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CategoryAttributeRecommendation and its DTO CategoryAttributeRecommendationDTO.
 */
@Mapper(componentModel = "spring", uses = {AuditTaskRecommendationMapper.class, CategoryAttributeMapper.class, })
public interface CategoryAttributeRecommendationMapper extends EntityMapper <CategoryAttributeRecommendationDTO, CategoryAttributeRecommendation> {

    @Mapping(source = "auditTaskRecom.id", target = "auditTaskRecomId")

    @Mapping(source = "categoryAttribute.id", target = "categoryAttributeId")
    CategoryAttributeRecommendationDTO toDto(CategoryAttributeRecommendation categoryAttributeRecommendation); 

    @Mapping(source = "auditTaskRecomId", target = "auditTaskRecom")

    @Mapping(source = "categoryAttributeId", target = "categoryAttribute")
    CategoryAttributeRecommendation toEntity(CategoryAttributeRecommendationDTO categoryAttributeRecommendationDTO); 
    default CategoryAttributeRecommendation fromId(Long id) {
        if (id == null) {
            return null;
        }
        CategoryAttributeRecommendation categoryAttributeRecommendation = new CategoryAttributeRecommendation();
        categoryAttributeRecommendation.setId(id);
        return categoryAttributeRecommendation;
    }
}
