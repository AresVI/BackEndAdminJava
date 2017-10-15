package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.domain.CompanyContactPerson;
import com.labausegtic.aresvi.service.dto.CompanyContactPersonDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing CompanyContactPerson.
 */
public interface CompanyContactPersonService {

    /**
     * Save a company_contact_person.
     *
     * @param company_contact_person the entity to save
     * @return the persisted entity
     */
    CompanyContactPersonDTO save(CompanyContactPersonDTO company_contact_person);

    /**
     *  Get all the company_contact_people.
     *
     *
     * @param company_id
     * @param pageable the pagination information
     *  @return the list of entities
     */
    Page<CompanyContactPersonDTO> findAll(Long company_id, Pageable pageable);

    /**
     *  Get the "id" company_contact_person.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CompanyContactPersonDTO findOne(Long id);

    /**
     *  Delete the "id" company_contact_person.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    Page<CompanyContactPersonDTO> findCompanyContactPeopleByCompany_Id(Long company_id, Pageable pageable);
}
