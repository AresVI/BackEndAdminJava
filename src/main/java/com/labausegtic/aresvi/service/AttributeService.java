package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.AttributeCompleteDTO;
import com.labausegtic.aresvi.service.dto.AttributeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

/**
 * Service Interface for managing Attribute.
 */
public interface AttributeService {

    /**
     * Save a attribute.
     *
     * @param attributeDTO the entity to save
     * @return the persisted entity
     */
    AttributeDTO save(AttributeDTO attributeDTO);

    /**
     *  Get all the attributes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<AttributeDTO> findAll(Pageable pageable);

    Set<AttributeCompleteDTO> findAllByCategoryAttribute_Id(Long categoryAttributeId);

    /**
     *  Get the "id" attribute.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    AttributeDTO findOne(Long id);

    /**
     *  Delete the "id" attribute.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
