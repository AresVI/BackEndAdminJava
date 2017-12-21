package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.service.CompanyAddressService;
import com.labausegtic.aresvi.web.rest.util.HeaderUtil;
import com.labausegtic.aresvi.web.rest.util.PaginationUtil;
import com.labausegtic.aresvi.service.dto.CompanyAddressDTO;
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
 * REST controller for managing CompanyAddress.
 */
@RestController
@RequestMapping("/api")
public class CompanyAddressResource {

    private final Logger log = LoggerFactory.getLogger(CompanyAddressResource.class);

    private static final String ENTITY_NAME = "companyAddress";

    private final CompanyAddressService companyAddressService;

    public CompanyAddressResource(CompanyAddressService companyAddressService) {
        this.companyAddressService = companyAddressService;
    }

    /**
     * POST  /company-addresses : Create a new companyAddress.
     *
     * @param companyAddressDTO the companyAddressDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new companyAddressDTO, or with status 400 (Bad Request) if the companyAddress has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/company-addresses")
    @Timed
    public ResponseEntity<CompanyAddressDTO> createCompanyAddress(@Valid @RequestBody CompanyAddressDTO companyAddressDTO) throws URISyntaxException {
        log.debug("REST request to save CompanyAddress : {}", companyAddressDTO);
        if (companyAddressDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new companyAddress cannot already have an ID")).body(null);
        }
        CompanyAddressDTO result = companyAddressService.save(companyAddressDTO);
        return ResponseEntity.created(new URI("/api/company-addresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /company-addresses : Updates an existing companyAddress.
     *
     * @param companyAddressDTO the companyAddressDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated companyAddressDTO,
     * or with status 400 (Bad Request) if the companyAddressDTO is not valid,
     * or with status 500 (Internal Server Error) if the companyAddressDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/company-addresses")
    @Timed
    public ResponseEntity<CompanyAddressDTO> updateCompanyAddress(@Valid @RequestBody CompanyAddressDTO companyAddressDTO) throws URISyntaxException {
        log.debug("REST request to update CompanyAddress : {}", companyAddressDTO);
        if (companyAddressDTO.getId() == null) {
            return createCompanyAddress(companyAddressDTO);
        }
        CompanyAddressDTO result = companyAddressService.save(companyAddressDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, companyAddressDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /company-addresses : get all the companyAddresses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of companyAddresses in body
     */
    @GetMapping("/company-addresses")
    @Timed
    public ResponseEntity<List<CompanyAddressDTO>> getAllCompanyAddresses(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of CompanyAddresses");
        Page<CompanyAddressDTO> page = companyAddressService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/company-addresses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /company-addresses/:id : get the "id" companyAddress.
     *
     * @param id the id of the companyAddressDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the companyAddressDTO, or with status 404 (Not Found)
     */
    @GetMapping("/company-addresses/{id}")
    @Timed
    public ResponseEntity<CompanyAddressDTO> getCompanyAddress(@PathVariable Long id) {
        log.debug("REST request to get CompanyAddress : {}", id);
        CompanyAddressDTO companyAddressDTO = companyAddressService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(companyAddressDTO));
    }

    /**
     * DELETE  /company-addresses/:id : delete the "id" companyAddress.
     *
     * @param id the id of the companyAddressDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/company-addresses/{id}")
    @Timed
    public ResponseEntity<Void> deleteCompanyAddress(@PathVariable Long id) {
        log.debug("REST request to delete CompanyAddress : {}", id);
        companyAddressService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
