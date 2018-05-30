package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.ProductTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing ProductType.
 */
public interface ProductTypeService {

    /**
     * Save a productType.
     *
     * @param productTypeDTO the entity to save
     * @return the persisted entity
     */
    ProductTypeDTO save(ProductTypeDTO productTypeDTO);

    /**
     *  Get all the productTypes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ProductTypeDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" productType.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ProductTypeDTO findOne(Long id);

    /**
     *  Delete the "id" productType.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
