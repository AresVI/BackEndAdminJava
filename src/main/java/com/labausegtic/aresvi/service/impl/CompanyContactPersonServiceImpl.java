package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.CompanyContactPersonService;
import com.labausegtic.aresvi.domain.CompanyContactPerson;
import com.labausegtic.aresvi.repository.CompanyContactPersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing CompanyContactPerson.
 */
@Service
@Transactional
public class CompanyContactPersonServiceImpl implements CompanyContactPersonService{

    private final Logger log = LoggerFactory.getLogger(CompanyContactPersonServiceImpl.class);

    private final CompanyContactPersonRepository company_contact_personRepository;

    public CompanyContactPersonServiceImpl(CompanyContactPersonRepository company_contact_personRepository) {
        this.company_contact_personRepository = company_contact_personRepository;
    }

    /**
     * Save a company_contact_person.
     *
     * @param company_contact_person the entity to save
     * @return the persisted entity
     */
    @Override
    public CompanyContactPerson save(CompanyContactPerson company_contact_person) {
        log.debug("Request to save CompanyContactPerson : {}", company_contact_person);
        return company_contact_personRepository.save(company_contact_person);
    }

    /**
     *  Get all the company_contact_people.
     *
     *
     * @param company_id
     * @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CompanyContactPerson> findAll(Long company_id, Pageable pageable) {
        log.debug("Request to get all Company_contact_people");
        return company_contact_personRepository.findAll(pageable);
    }

    /**
     *  Get one company_contact_person by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CompanyContactPerson findOne(Long id) {
        log.debug("Request to get CompanyContactPerson : {}", id);
        return company_contact_personRepository.findOne(id);
    }

    /**
     *  Delete the  company_contact_person by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CompanyContactPerson : {}", id);
        company_contact_personRepository.delete(id);
    }

    @Override
    public Page<CompanyContactPerson> findCompanyContactPeopleByCompany_IdOrderByIdLast_nameAsc(Long company_id, Pageable pageable) {

        return company_contact_personRepository.findCompanyContactPeopleByCompany_Id(company_id, pageable);

    }
}
