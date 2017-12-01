package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.CompanyAddressDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing CompanyAddress.
 */
public interface CompanyAddressService {

    /**
     * Save a companyAddress.
     *
     * @param companyAddressDTO the entity to save
     * @return the persisted entity
     */
    CompanyAddressDTO save(CompanyAddressDTO companyAddressDTO);

    /**
     *  Get all the companyAddresses.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<CompanyAddressDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" companyAddress.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CompanyAddressDTO findOne(Long id);

    /**
     *  Delete the "id" companyAddress.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    CompanyAddressDTO findOneByCompanyId(Long id);
}
