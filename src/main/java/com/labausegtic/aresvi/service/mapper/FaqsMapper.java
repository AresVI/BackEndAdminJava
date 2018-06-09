package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.FaqsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Faqs and its DTO FaqsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FaqsMapper extends EntityMapper <FaqsDTO, Faqs> {
    
    
    default Faqs fromId(Long id) {
        if (id == null) {
            return null;
        }
        Faqs faqs = new Faqs();
        faqs.setId(id);
        return faqs;
    }
}
