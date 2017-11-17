package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.CategoryAttrRecommendationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CategoryAttrRecommendation and its DTO CategoryAttrRecommendationDTO.
 */
@Mapper(componentModel = "spring", uses = {AuditTaskRecommendationMapper.class, CategoryAttributeMapper.class, })
public interface CategoryAttrRecommendationMapper extends EntityMapper <CategoryAttrRecommendationDTO, CategoryAttrRecommendation> {

    @Mapping(source = "auditTaskRecom.id", target = "auditTaskRecomId")

    @Mapping(source = "categoryAttribute.id", target = "categoryAttributeId")
    CategoryAttrRecommendationDTO toDto(CategoryAttrRecommendation categoryAttrRecommendation);

    @Mapping(source = "auditTaskRecomId", target = "auditTaskRecom")

    @Mapping(source = "categoryAttributeId", target = "categoryAttribute")
    CategoryAttrRecommendation toEntity(CategoryAttrRecommendationDTO categoryAttrRecommendationDTO);
    default CategoryAttrRecommendation fromId(Long id) {
        if (id == null) {
            return null;
        }
        CategoryAttrRecommendation categoryAttrRecommendation = new CategoryAttrRecommendation();
        categoryAttrRecommendation.setId(id);
        return categoryAttrRecommendation;
    }
}
