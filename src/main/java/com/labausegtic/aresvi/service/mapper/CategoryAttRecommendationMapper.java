package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.CategoryAttRecommendationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CategoryAttRecommendation and its DTO CategoryAttRecommendationDTO.
 */
@Mapper(componentModel = "spring", uses = {AuditTaskRecommendationMapper.class, CategoryAttributeMapper.class, })
public interface CategoryAttRecommendationMapper extends EntityMapper <CategoryAttRecommendationDTO, CategoryAttRecommendation> {

    @Mapping(source = "auditTaskRecom.id", target = "auditTaskRecomId")

    @Mapping(source = "categoryAttribute.id", target = "categoryAttributeId")
    CategoryAttRecommendationDTO toDto(CategoryAttRecommendation categoryAttRecommendation); 

    @Mapping(source = "auditTaskRecomId", target = "auditTaskRecom")

    @Mapping(source = "categoryAttributeId", target = "categoryAttribute")
    CategoryAttRecommendation toEntity(CategoryAttRecommendationDTO categoryAttRecommendationDTO); 
    default CategoryAttRecommendation fromId(Long id) {
        if (id == null) {
            return null;
        }
        CategoryAttRecommendation categoryAttRecommendation = new CategoryAttRecommendation();
        categoryAttRecommendation.setId(id);
        return categoryAttRecommendation;
    }
}
