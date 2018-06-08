package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.GlossaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Glossary.
 */
public interface GlossaryService {

    /**
     * Save a glossary.
     *
     * @param glossaryDTO the entity to save
     * @return the persisted entity
     */
    GlossaryDTO save(GlossaryDTO glossaryDTO);

    /**
     *  Get all the glossaries.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<GlossaryDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" glossary.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    GlossaryDTO findOne(Long id);

    /**
     *  Delete the "id" glossary.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
