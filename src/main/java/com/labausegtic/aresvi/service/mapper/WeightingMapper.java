package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.WeightingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Weighting and its DTO WeightingDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WeightingMapper extends EntityMapper <WeightingDTO, Weighting> {
    
    
    default Weighting fromId(Long id) {
        if (id == null) {
            return null;
        }
        Weighting weighting = new Weighting();
        weighting.setId(id);
        return weighting;
    }
}
