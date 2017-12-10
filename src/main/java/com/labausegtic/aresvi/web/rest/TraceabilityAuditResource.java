package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.domain.Auditor;
import com.labausegtic.aresvi.domain.Authority;
import com.labausegtic.aresvi.domain.StatusTraceabilityAudit;
import com.labausegtic.aresvi.domain.User;
import com.labausegtic.aresvi.repository.AuditorRepository;
import com.labausegtic.aresvi.security.AuthoritiesConstants;
import com.labausegtic.aresvi.security.SecurityUtils;
import com.labausegtic.aresvi.service.RecommendationService;
import com.labausegtic.aresvi.service.TraceabilityAuditService;
import com.labausegtic.aresvi.service.UserService;
import com.labausegtic.aresvi.web.rest.util.HeaderUtil;
import com.labausegtic.aresvi.web.rest.util.PaginationUtil;
import com.labausegtic.aresvi.service.dto.TraceabilityAuditDTO;
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

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TraceabilityAudit.
 */
@RestController
@RequestMapping("/api")
public class TraceabilityAuditResource {

    private final Logger log = LoggerFactory.getLogger(TraceabilityAuditResource.class);

    private static final String ENTITY_NAME = "traceabilityAudit";

    private final TraceabilityAuditService traceabilityAuditService;

    private final RecommendationService recommendationService;

    private final UserService userService;

    private final AuditorRepository auditorRepository;

    public TraceabilityAuditResource(TraceabilityAuditService traceabilityAuditService, RecommendationService recommendationService, UserService userService, AuditorRepository auditorRepository) {
        this.traceabilityAuditService = traceabilityAuditService;
        this.recommendationService = recommendationService;
        this.userService = userService;
        this.auditorRepository = auditorRepository;
    }

    /**
     * POST  /traceability-audits : Create a new traceabilityAudit.
     *
     * @param traceabilityAuditDTO the traceabilityAuditDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new traceabilityAuditDTO, or with status 400 (Bad Request) if the traceabilityAudit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/traceability-audits")
    @Timed
    public ResponseEntity<TraceabilityAuditDTO> createTraceabilityAudit(@Valid @RequestBody TraceabilityAuditDTO traceabilityAuditDTO) throws URISyntaxException {
        log.debug("REST request to save TraceabilityAudit : {}", traceabilityAuditDTO);
        if (traceabilityAuditDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new traceabilityAudit cannot already have an ID")).body(null);
        }

        traceabilityAuditDTO.setCreationDate(Instant.now());
        traceabilityAuditDTO.setStatus(StatusTraceabilityAudit.NOT_STARTED);

        TraceabilityAuditDTO result = traceabilityAuditService.save(traceabilityAuditDTO);
        return ResponseEntity.created(new URI("/api/traceability-audits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/traceability-audits/{id}/start")
    @Timed
    public ResponseEntity<TraceabilityAuditDTO> startTraceabilityAudit(@PathVariable Long id) throws URISyntaxException {

        TraceabilityAuditDTO traceabilityAuditDTO = traceabilityAuditService.startTraceabilityAudit(id);

        return ResponseEntity.ok().headers(HeaderUtil.createFlowStartAlert(ENTITY_NAME, traceabilityAuditDTO.getName()))
            .body(traceabilityAuditDTO);

    }

    @PutMapping("/traceability-audits/{id}/finish")
    @Timed
    public ResponseEntity<TraceabilityAuditDTO> finishTraceabilityAudit(@PathVariable Long id) throws URISyntaxException {

        TraceabilityAuditDTO traceabilityAuditDTO = traceabilityAuditService.finishTraceabilityAudit(id);

        return ResponseEntity.ok().headers(HeaderUtil.createFlowFinishAlert(ENTITY_NAME, traceabilityAuditDTO.getName()))
            .body(traceabilityAuditDTO);

    }

    /**
     * PUT  /traceability-audits : Updates an existing traceabilityAudit.
     *
     * @param traceabilityAuditDTO the traceabilityAuditDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated traceabilityAuditDTO,
     * or with status 400 (Bad Request) if the traceabilityAuditDTO is not valid,
     * or with status 500 (Internal Server Error) if the traceabilityAuditDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/traceability-audits")
    @Timed
    public ResponseEntity<TraceabilityAuditDTO> updateTraceabilityAudit(@Valid @RequestBody TraceabilityAuditDTO traceabilityAuditDTO) throws URISyntaxException {
        log.debug("REST request to update TraceabilityAudit : {}", traceabilityAuditDTO);
        if (traceabilityAuditDTO.getId() == null) {
            return createTraceabilityAudit(traceabilityAuditDTO);
        }
        TraceabilityAuditDTO result = traceabilityAuditService.save(traceabilityAuditDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, traceabilityAuditDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /traceability-audits : get all the traceabilityAudits.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of traceabilityAudits in body
     */
    @GetMapping("/traceability-audits")
    @Timed
    public ResponseEntity<List<TraceabilityAuditDTO>> getAllTraceabilityAudits(@ApiParam Pageable pageable, @ApiParam String status) {
        log.debug("REST request to get a page of TraceabilityAudits");

        Page<TraceabilityAuditDTO> page;

        if (status != null ) {
            page = traceabilityAuditService.findAllByStatus(pageable, status);
        } else {
            page = traceabilityAuditService.findAll(pageable);
        }

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/traceability-audits");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/traceability-audits/finished")
    @Timed
    public ResponseEntity<List<TraceabilityAuditDTO>> getAllFinishedTraceabilityAudits(
            @ApiParam Pageable pageable, @ApiParam String category, @ApiParam Long company_id
        ) {
        log.debug("REST request to get a page of TraceabilityAudits");

        Page<TraceabilityAuditDTO> page;

        Optional<User> user = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin());

        Authority authority = new Authority();
        authority.setName(AuthoritiesConstants.AUDITOR_INTERNAL);

        if (user.get().getAuthorities().contains(authority)) {
            Auditor auditor = auditorRepository.findAuditorByUser_Id(user.get().getId());
            page = traceabilityAuditService.findAllFinishedByCategoryAndCompany(pageable, category, company_id, auditor.getCompanies()) ;
        } else {
            page = traceabilityAuditService.findAllFinishedByCategoryAndCompany(pageable, category, company_id, null) ;
        }

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/traceability-audits/finished");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /traceability-audits/:id : get the "id" traceabilityAudit.
     *
     * @param id the id of the traceabilityAuditDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the traceabilityAuditDTO, or with status 404 (Not Found)
     */
    @GetMapping("/traceability-audits/{id}")
    @Timed
    public ResponseEntity<TraceabilityAuditDTO> getTraceabilityAudit(@PathVariable Long id) {
        log.debug("REST request to get TraceabilityAudit : {}", id);
        TraceabilityAuditDTO traceabilityAuditDTO = traceabilityAuditService.findOne(id);

        traceabilityAuditDTO.setRecommendationDTOSet(
            recommendationService.findAllByTraceabilityAudit_Id(traceabilityAuditDTO.getId())
        );

        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(traceabilityAuditDTO));
    }

    /**
     * DELETE  /traceability-audits/:id : delete the "id" traceabilityAudit.
     *
     * @param id the id of the traceabilityAuditDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/traceability-audits/{id}")
    @Timed
    public ResponseEntity<Void> deleteTraceabilityAudit(@PathVariable Long id) {
        log.debug("REST request to delete TraceabilityAudit : {}", id);
        traceabilityAuditService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
