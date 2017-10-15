package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.domain.CompanyContactPerson;
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
    CompanyContactPerson save(CompanyContactPerson company_contact_person);

    /**
     *  Get all the company_contact_people.
     *
     *
     * @param company_id
     * @param pageable the pagination information
     *  @return the list of entities
     */
    Page<CompanyContactPerson> findAll(Long company_id, Pageable pageable);

    /**
     *  Get the "id" company_contact_person.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CompanyContactPerson findOne(Long id);

    /**
     *  Delete the "id" company_contact_person.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    Page<CompanyContactPerson> findCompanyContactPeopleByCompany_IdOrderByIdLast_nameAsc(Long company_id, Pageable pageable);
}
