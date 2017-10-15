package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.CompanyContactPerson;
import com.labausegtic.aresvi.service.dto.CompanyContactPersonDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Company and its DTO CompanyDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CompanyContactPersonMapper extends EntityMapper <CompanyContactPersonDTO, CompanyContactPerson> {


    default CompanyContactPerson fromId(Long id) {
        if (id == null) {
            return null;
        }
        CompanyContactPerson companyContactPerson = new CompanyContactPerson();
        companyContactPerson.setId(id);
        return companyContactPerson;
    }
}
