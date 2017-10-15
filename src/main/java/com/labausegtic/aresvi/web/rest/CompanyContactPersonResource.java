package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.domain.CompanyContactPerson;
import com.labausegtic.aresvi.service.CompanyContactPersonService;
import com.labausegtic.aresvi.service.dto.CompanyContactPersonDTO;
import com.labausegtic.aresvi.web.rest.util.HeaderUtil;
import com.labausegtic.aresvi.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CompanyContactPerson.
 */
@RestController
@RequestMapping("/api")
public class CompanyContactPersonResource {

    private final Logger log = LoggerFactory.getLogger(CompanyContactPersonResource.class);

    private static final String ENTITY_NAME = "company_contact_person";

    private final CompanyContactPersonService company_contact_personService;

    public CompanyContactPersonResource(CompanyContactPersonService company_contact_personService) {
        this.company_contact_personService = company_contact_personService;
    }

    /**
     * POST  /company-contact-people : Create a new company_contact_person.
     *
     * @param company_contact_person the company_contact_person to create
     * @return the ResponseEntity with status 201 (Created) and with body the new company_contact_person, or with status 400 (Bad Request) if the company_contact_person has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("company/{company_id}/company-contact-people")
    @Timed
    public ResponseEntity<CompanyContactPersonDTO> createCompanyContactPerson(@Valid @RequestBody CompanyContactPersonDTO company_contact_person, @PathVariable("company_id") String company_id) throws URISyntaxException {
        log.debug("REST request to save CompanyContactPerson : {}", company_contact_person);
        if (company_contact_person.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new company_contact_person cannot already have an ID")).body(null);
        }
        CompanyContactPersonDTO result = company_contact_personService.save(company_contact_person);
        return ResponseEntity.created(new URI("/api/company/" + company_id + "/company-contact-people/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /company-contact-people : Updates an existing company_contact_person.
     *
     * @param company_contact_person the company_contact_person to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated company_contact_person,
     * or with status 400 (Bad Request) if the company_contact_person is not valid,
     * or with status 500 (Internal Server Error) if the company_contact_person couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("company/{company_id}/company-contact-people")
    @Timed
    public ResponseEntity<CompanyContactPersonDTO> updateCompanyContactPerson(@Valid @RequestBody CompanyContactPersonDTO company_contact_person, @PathVariable("company_id") String company_id) throws URISyntaxException {
        log.debug("REST request to update CompanyContactPerson : {}", company_contact_person);
        if (company_contact_person.getId() == null) {
            return createCompanyContactPerson(company_contact_person, company_id);
        }
        CompanyContactPersonDTO result = company_contact_personService.save(company_contact_person);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, company_contact_person.getId().toString()))
            .body(result);
    }

    /**
     * GET  /company-contact-people : get all the company_contact_people.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of company_contact_people in body
     */
    @GetMapping("company/{company_id}/company-contact-people")
    @Timed
    public ResponseEntity<List<CompanyContactPersonDTO>> getAllCompany_contact_people(@ApiParam Pageable pageable, @PathVariable("company_id") Long company_id) {
        log.debug("REST request to get a page of Company_contact_people");
        Page<CompanyContactPersonDTO> page = company_contact_personService.findCompanyContactPeopleByCompany_Id(company_id, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/company/" + company_id + "company-contact-people");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /company-contact-people/:id : get the "id" company_contact_person.
     *
     * @param id the id of the company_contact_person to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the company_contact_person, or with status 404 (Not Found)
     */
    @GetMapping("company/{company_id}/company-contact-people/{id}")
    @Timed
    public ResponseEntity<CompanyContactPersonDTO> getCompanyContactPerson(@PathVariable Long id, @PathVariable("company_id") Long company_id) {
        log.debug("REST request to get CompanyContactPerson : {}", id);
        CompanyContactPersonDTO company_contact_person = company_contact_personService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(company_contact_person));
    }

    /**
     * DELETE  /company-contact-people/:id : delete the "id" company_contact_person.
     *
     * @param id the id of the company_contact_person to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("company/{company_id}/company-contact-people/{id}")
    @Timed
    public ResponseEntity<Void> deleteCompanyContactPerson(@PathVariable Long id, @PathVariable("company_id") String company_id) {
        log.debug("REST request to delete CompanyContactPerson : {}", id);
        company_contact_personService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
