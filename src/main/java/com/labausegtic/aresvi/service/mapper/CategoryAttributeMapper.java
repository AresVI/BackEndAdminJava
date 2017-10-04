package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.CategoryAttributeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CategoryAttribute and its DTO CategoryAttributeDTO.
 */
@Mapper(componentModel = "spring", uses = {AuditTaskMapper.class, })
public interface CategoryAttributeMapper extends EntityMapper <CategoryAttributeDTO, CategoryAttribute> {

    @Mapping(source = "auditTask.id", target = "auditTaskId")
    CategoryAttributeDTO toDto(CategoryAttribute categoryAttribute); 

    @Mapping(source = "auditTaskId", target = "auditTask")
    CategoryAttribute toEntity(CategoryAttributeDTO categoryAttributeDTO); 
    default CategoryAttribute fromId(Long id) {
        if (id == null) {
            return null;
        }
        CategoryAttribute categoryAttribute = new CategoryAttribute();
        categoryAttribute.setId(id);
        return categoryAttribute;
    }
}
