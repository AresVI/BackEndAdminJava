package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.ProductTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ProductType and its DTO ProductTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {AuditProcessMapper.class, })
public interface ProductTypeMapper extends EntityMapper <ProductTypeDTO, ProductType> {
    
    
    default ProductType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProductType productType = new ProductType();
        productType.setId(id);
        return productType;
    }
}
