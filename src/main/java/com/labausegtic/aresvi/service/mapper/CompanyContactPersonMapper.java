package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.CompanyContactPerson;
import com.labausegtic.aresvi.service.dto.CompanyContactPersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Company and its DTO CompanyDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class, })
public interface CompanyContactPersonMapper extends EntityMapper <CompanyContactPersonDTO, CompanyContactPerson> {

    @Mapping(source = "company.id", target = "companyId")
    CompanyContactPersonDTO toDto(CompanyContactPerson companyContactPersonId);

    @Mapping(source = "companyId", target = "company")
    CompanyContactPerson toEntity(CompanyContactPersonDTO companyContactPersonIdDTO);

    default CompanyContactPerson fromId(Long id) {
        if (id == null) {
            return null;
        }
        CompanyContactPerson companyContactPerson = new CompanyContactPerson();
        companyContactPerson.setId(id);
        return companyContactPerson;
    }
}
