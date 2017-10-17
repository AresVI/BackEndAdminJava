package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.CategoryAttribute;
import com.labausegtic.aresvi.service.dto.CategoryAttributeCompleteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity CategoryAttribute and its DTO CategoryAttributeDTO.
 */
@Mapper(componentModel = "spring", uses = {AuditTaskMapper.class, })
public interface CategoryAttributeCompleteMapper extends EntityMapper <CategoryAttributeCompleteDTO, CategoryAttribute> {

    @Mapping(source = "auditTask.id", target = "auditTaskId")
    CategoryAttributeCompleteDTO toDto(CategoryAttribute categoryAttribute);

    @Mapping(source = "auditTaskId", target = "auditTask")
    CategoryAttribute toEntity(CategoryAttributeCompleteDTO categoryAttributeCompleteDTO);
    default CategoryAttribute fromId(Long id) {
        if (id == null) {
            return null;
        }
        CategoryAttribute categoryAttribute = new CategoryAttribute();
        categoryAttribute.setId(id);
        return categoryAttribute;
    }
}
