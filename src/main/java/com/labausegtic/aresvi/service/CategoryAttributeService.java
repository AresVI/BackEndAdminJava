package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.CategoryAttributeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing CategoryAttribute.
 */
public interface CategoryAttributeService {

    /**
     * Save a categoryAttribute.
     *
     * @param categoryAttributeDTO the entity to save
     * @return the persisted entity
     */
    CategoryAttributeDTO save(CategoryAttributeDTO categoryAttributeDTO);

    /**
     *  Get all the categoryAttributes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<CategoryAttributeDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" categoryAttribute.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CategoryAttributeDTO findOne(Long id);

    /**
     *  Delete the "id" categoryAttribute.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
