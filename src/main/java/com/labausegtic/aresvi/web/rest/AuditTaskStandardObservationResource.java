package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.service.AuditTaskStandardObservationService;
import com.labausegtic.aresvi.web.rest.util.HeaderUtil;
import com.labausegtic.aresvi.web.rest.util.PaginationUtil;
import com.labausegtic.aresvi.service.dto.AuditTaskStandardObservationDTO;
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

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AuditTaskStandardObservation.
 */
@RestController
@RequestMapping("/api")
public class AuditTaskStandardObservationResource {

    private final Logger log = LoggerFactory.getLogger(AuditTaskStandardObservationResource.class);

    private static final String ENTITY_NAME = "auditTaskStandardObservation";

    private final AuditTaskStandardObservationService auditTaskStandardObservationService;

    public AuditTaskStandardObservationResource(AuditTaskStandardObservationService auditTaskStandardObservationService) {
        this.auditTaskStandardObservationService = auditTaskStandardObservationService;
    }

    /**
     * POST  /audit-task-standard-observations : Create a new auditTaskStandardObservation.
     *
     * @param auditTaskStandardObservationDTO the auditTaskStandardObservationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new auditTaskStandardObservationDTO, or with status 400 (Bad Request) if the auditTaskStandardObservation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/audit-task-standard-observations")
    @Timed
    public ResponseEntity<AuditTaskStandardObservationDTO> createAuditTaskStandardObservation(@Valid @RequestBody AuditTaskStandardObservationDTO auditTaskStandardObservationDTO) throws URISyntaxException {
        log.debug("REST request to save AuditTaskStandardObservation : {}", auditTaskStandardObservationDTO);
        if (auditTaskStandardObservationDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new auditTaskStandardObservation cannot already have an ID")).body(null);
        }
        AuditTaskStandardObservationDTO result = auditTaskStandardObservationService.save(auditTaskStandardObservationDTO);
        return ResponseEntity.created(new URI("/api/audit-task-standard-observations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /audit-task-standard-observations : Updates an existing auditTaskStandardObservation.
     *
     * @param auditTaskStandardObservationDTO the auditTaskStandardObservationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated auditTaskStandardObservationDTO,
     * or with status 400 (Bad Request) if the auditTaskStandardObservationDTO is not valid,
     * or with status 500 (Internal Server Error) if the auditTaskStandardObservationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/audit-task-standard-observations")
    @Timed
    public ResponseEntity<AuditTaskStandardObservationDTO> updateAuditTaskStandardObservation(@Valid @RequestBody AuditTaskStandardObservationDTO auditTaskStandardObservationDTO) throws URISyntaxException {
        log.debug("REST request to update AuditTaskStandardObservation : {}", auditTaskStandardObservationDTO);
        if (auditTaskStandardObservationDTO.getId() == null) {
            return createAuditTaskStandardObservation(auditTaskStandardObservationDTO);
        }
        AuditTaskStandardObservationDTO result = auditTaskStandardObservationService.save(auditTaskStandardObservationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, auditTaskStandardObservationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /audit-task-standard-observations : get all the auditTaskStandardObservations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of auditTaskStandardObservations in body
     */
    @GetMapping("/audit-task-standard-observations")
    @Timed
    public ResponseEntity<List<AuditTaskStandardObservationDTO>> getAllAuditTaskStandardObservations(@ApiParam Pageable pageable
        , @Param("pagination") Boolean pagination) {
        log.debug("REST request to get a page of AuditTaskStandardObservations");

        pageable = pagination != null && pagination ? pageable : null;

        Page<AuditTaskStandardObservationDTO> page = auditTaskStandardObservationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/audit-task-standard-observations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /audit-task-standard-observations/:id : get the "id" auditTaskStandardObservation.
     *
     * @param id the id of the auditTaskStandardObservationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the auditTaskStandardObservationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/audit-task-standard-observations/{id}")
    @Timed
    public ResponseEntity<AuditTaskStandardObservationDTO> getAuditTaskStandardObservation(@PathVariable Long id) {
        log.debug("REST request to get AuditTaskStandardObservation : {}", id);
        AuditTaskStandardObservationDTO auditTaskStandardObservationDTO = auditTaskStandardObservationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(auditTaskStandardObservationDTO));
    }

    /**
     * DELETE  /audit-task-standard-observations/:id : delete the "id" auditTaskStandardObservation.
     *
     * @param id the id of the auditTaskStandardObservationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/audit-task-standard-observations/{id}")
    @Timed
    public ResponseEntity<Void> deleteAuditTaskStandardObservation(@PathVariable Long id) {
        log.debug("REST request to delete AuditTaskStandardObservation : {}", id);
        auditTaskStandardObservationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
