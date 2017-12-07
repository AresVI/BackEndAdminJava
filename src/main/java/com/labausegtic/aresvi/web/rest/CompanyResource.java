package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.domain.Auditor;
import com.labausegtic.aresvi.domain.Authority;
import com.labausegtic.aresvi.domain.Company;
import com.labausegtic.aresvi.domain.User;
import com.labausegtic.aresvi.repository.AuditorRepository;
import com.labausegtic.aresvi.repository.CompanyRepository;
import com.labausegtic.aresvi.security.AuthoritiesConstants;
import com.labausegtic.aresvi.security.SecurityUtils;
import com.labausegtic.aresvi.service.CompanyService;
import com.labausegtic.aresvi.service.TraceabilityAuditService;
import com.labausegtic.aresvi.service.UserService;
import com.labausegtic.aresvi.service.dto.ComparativeTaskRecommendationDTO;
import com.labausegtic.aresvi.service.dto.TraceabilityAuditDTO;
import com.labausegtic.aresvi.service.mapper.CompanyMapper;
import com.labausegtic.aresvi.web.rest.util.HeaderUtil;
import com.labausegtic.aresvi.web.rest.util.PaginationUtil;
import com.labausegtic.aresvi.service.dto.CompanyDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * REST controller for managing Company.
 */
@RestController
@RequestMapping("/api")
public class CompanyResource {

    private final Logger log = LoggerFactory.getLogger(CompanyResource.class);

    private static final String ENTITY_NAME = "company";

    private final CompanyService companyService;

    private final CompanyRepository companyRepository;

    private final TraceabilityAuditService traceabilityAuditService;

    private final UserService userService;

    private final AuditorRepository auditorRepository;

    private final CompanyMapper companyMapper;

    public CompanyResource(CompanyService companyService, CompanyRepository companyRepository, TraceabilityAuditService traceabilityAuditService, UserService userService, AuditorRepository auditorRepository, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyRepository = companyRepository;
        this.traceabilityAuditService = traceabilityAuditService;
        this.userService = userService;
        this.auditorRepository = auditorRepository;
        this.companyMapper = companyMapper;
    }

    /**
     * POST  /companies : Create a new company.
     *
     * @param companyDTO the companyDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new companyDTO, or with status 400 (Bad Request) if the company has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/companies")
    @Timed
    public ResponseEntity<CompanyDTO> createCompany(@Valid @RequestBody CompanyDTO companyDTO) throws URISyntaxException {
        log.debug("REST request to save Company : {}", companyDTO);
        if (companyDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new company cannot already have an ID")).body(null);
        }
        CompanyDTO result = companyService.save(companyDTO);
        return ResponseEntity.created(new URI("/api/companies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /companies : Updates an existing company.
     *
     * @param companyDTO the companyDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated companyDTO,
     * or with status 400 (Bad Request) if the companyDTO is not valid,
     * or with status 500 (Internal Server Error) if the companyDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/companies")
    @Timed
    public ResponseEntity<CompanyDTO> updateCompany(@Valid @RequestBody CompanyDTO companyDTO) throws URISyntaxException {
        log.debug("REST request to update Company : {}", companyDTO);
        if (companyDTO.getId() == null) {
            return createCompany(companyDTO);
        }
        CompanyDTO result = companyService.save(companyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, companyDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /companies : get all the companies.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of companies in body
     */
    @GetMapping("/companies")
    @Timed
    public ResponseEntity<List<CompanyDTO>> getAllCompanies(@ApiParam Pageable pageable, @Param("pagination") Boolean pagination) {
        log.debug("REST request to get a page of Companies");

        pageable = pagination != null && pagination ? pageable : null;

        Optional<User> user = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin());

        Authority authority = new Authority();
        authority.setName(AuthoritiesConstants.AUDITOR_INTERNAL);

        if (user.isPresent() && user.get().getAuthorities().contains(authority)) {

            Auditor auditor = auditorRepository.findAuditorByUser_Id(user.get().getId());

            List<CompanyDTO> result = new ArrayList<>();

            for (Company c : auditor.getCompanies()){
                result.add(companyMapper.toDto(c));
            }

            return new ResponseEntity<>(result, null, HttpStatus.OK);


        } else {

            Page<CompanyDTO> page  = companyService.findAll(pageable);

            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/companies");

            return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);

        }
    }

    /**
     * GET  /companies/:id : get the "id" company.
     *
     * @param id the id of the companyDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the companyDTO, or with status 404 (Not Found)
     */
    @GetMapping("/companies/{id}")
    @Timed
    public ResponseEntity<CompanyDTO> getCompany(@PathVariable Long id) {
        log.debug("REST request to get Company : {}", id);
        CompanyDTO companyDTO = companyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(companyDTO));
    }

    @GetMapping("/companies/{id}/last_two_results")
    @Timed
    public ResponseEntity<Set<TraceabilityAuditDTO>> getLastTwoTraceabilityAudit(@PathVariable Long id) {
        log.debug("REST request to get Company : {}", id);

        Set<TraceabilityAuditDTO> result;

        result= traceabilityAuditService.findLastTwoTraceabilityAuditsFinished(id);

        return  new ResponseEntity<>(result, null, HttpStatus.OK);
    }

    @GetMapping("/companies/{id}/last_two_results/comparative/{process_id}")
    @Timed
    public ResponseEntity<List<ComparativeTaskRecommendationDTO>> getLastTwoTraceabilityAudit(@PathVariable Long id, @PathVariable Long process_id) {
        log.debug("REST request to get Company : {}", id);

        List<ComparativeTaskRecommendationDTO> comparativeProcessRecommendationDTO;

        comparativeProcessRecommendationDTO = traceabilityAuditService.compareLastTwoTraceabilityAuditsFinished(id, process_id);

        return  new ResponseEntity<>(comparativeProcessRecommendationDTO, null, HttpStatus.OK);
    }

    /**
     * DELETE  /companies/:id : delete the "id" company.
     *
     * @param id the id of the companyDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/companies/{id}")
    @Timed
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        log.debug("REST request to delete Company : {}", id);
        companyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
