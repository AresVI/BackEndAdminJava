package com.labausegtic.aresvi.service.mapper;

import com.labausegtic.aresvi.domain.*;
import com.labausegtic.aresvi.service.dto.GlossaryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Glossary and its DTO GlossaryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GlossaryMapper extends EntityMapper <GlossaryDTO, Glossary> {
    
    
    default Glossary fromId(Long id) {
        if (id == null) {
            return null;
        }
        Glossary glossary = new Glossary();
        glossary.setId(id);
        return glossary;
    }
}
