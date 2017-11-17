package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.Auditor;
import com.labausegtic.aresvi.service.dto.AuditorDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Company and its DTO CompanyDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AuditorMapper extends EntityMapper <AuditorDTO, Auditor> {


    default Auditor fromId(Long id) {
        if (id == null) {
            return null;
        }
        Auditor auditor = new Auditor();
        auditor.setId(id);
        return auditor;
    }
}
