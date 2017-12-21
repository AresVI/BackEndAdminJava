package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.CompanyAddressDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CompanyAddress and its DTO CompanyAddressDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class, })
public interface CompanyAddressMapper extends EntityMapper <CompanyAddressDTO, CompanyAddress> {

    @Mapping(source = "company.id", target = "companyId")
    CompanyAddressDTO toDto(CompanyAddress companyAddress); 

    @Mapping(source = "companyId", target = "company")
    CompanyAddress toEntity(CompanyAddressDTO companyAddressDTO); 
    default CompanyAddress fromId(Long id) {
        if (id == null) {
            return null;
        }
        CompanyAddress companyAddress = new CompanyAddress();
        companyAddress.setId(id);
        return companyAddress;
    }
}
