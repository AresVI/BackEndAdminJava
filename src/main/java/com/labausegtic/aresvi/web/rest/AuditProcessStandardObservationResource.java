package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.service.AuditProcessStandardObservationService;
import com.labausegtic.aresvi.web.rest.util.HeaderUtil;
import com.labausegtic.aresvi.web.rest.util.PaginationUtil;
import com.labausegtic.aresvi.service.dto.AuditProcessStandardObservationDTO;
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
 * REST controller for managing AuditProcessStandardObservation.
 */
@RestController
@RequestMapping("/api")
public class AuditProcessStandardObservationResource {

    private final Logger log = LoggerFactory.getLogger(AuditProcessStandardObservationResource.class);

    private static final String ENTITY_NAME = "auditProcessStandardObservation";

    private final AuditProcessStandardObservationService auditProcessStandardObservationService;

    public AuditProcessStandardObservationResource(AuditProcessStandardObservationService auditProcessStandardObservationService) {
        this.auditProcessStandardObservationService = auditProcessStandardObservationService;
    }

    /**
     * POST  /audit-process-standard-observations : Create a new auditProcessStandardObservation.
     *
     * @param auditProcessStandardObservationDTO the auditProcessStandardObservationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new auditProcessStandardObservationDTO, or with status 400 (Bad Request) if the auditProcessStandardObservation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/audit-process-standard-observations")
    @Timed
    public ResponseEntity<AuditProcessStandardObservationDTO> createAuditProcessStandardObservation(@Valid @RequestBody AuditProcessStandardObservationDTO auditProcessStandardObservationDTO) throws URISyntaxException {
        log.debug("REST request to save AuditProcessStandardObservation : {}", auditProcessStandardObservationDTO);
        if (auditProcessStandardObservationDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new auditProcessStandardObservation cannot already have an ID")).body(null);
        }
        AuditProcessStandardObservationDTO result = auditProcessStandardObservationService.save(auditProcessStandardObservationDTO);
        return ResponseEntity.created(new URI("/api/audit-process-standard-observations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /audit-process-standard-observations : Updates an existing auditProcessStandardObservation.
     *
     * @param auditProcessStandardObservationDTO the auditProcessStandardObservationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated auditProcessStandardObservationDTO,
     * or with status 400 (Bad Request) if the auditProcessStandardObservationDTO is not valid,
     * or with status 500 (Internal Server Error) if the auditProcessStandardObservationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/audit-process-standard-observations")
    @Timed
    public ResponseEntity<AuditProcessStandardObservationDTO> updateAuditProcessStandardObservation(@Valid @RequestBody AuditProcessStandardObservationDTO auditProcessStandardObservationDTO) throws URISyntaxException {
        log.debug("REST request to update AuditProcessStandardObservation : {}", auditProcessStandardObservationDTO);
        if (auditProcessStandardObservationDTO.getId() == null) {
            return createAuditProcessStandardObservation(auditProcessStandardObservationDTO);
        }
        AuditProcessStandardObservationDTO result = auditProcessStandardObservationService.save(auditProcessStandardObservationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, auditProcessStandardObservationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /audit-process-standard-observations : get all the auditProcessStandardObservations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of auditProcessStandardObservations in body
     */
    @GetMapping("/audit-process-standard-observations")
    @Timed
    public ResponseEntity<List<AuditProcessStandardObservationDTO>> getAllAuditProcessStandardObservations(@ApiParam Pageable pageable
        , @Param("pagination") Boolean pagination) {
        log.debug("REST request to get a page of AuditProcessStandardObservations");

        pageable = pagination != null && pagination ? pageable : null;

        Page<AuditProcessStandardObservationDTO> page = auditProcessStandardObservationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/audit-process-standard-observations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /audit-process-standard-observations/:id : get the "id" auditProcessStandardObservation.
     *
     * @param id the id of the auditProcessStandardObservationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the auditProcessStandardObservationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/audit-process-standard-observations/{id}")
    @Timed
    public ResponseEntity<AuditProcessStandardObservationDTO> getAuditProcessStandardObservation(@PathVariable Long id) {
        log.debug("REST request to get AuditProcessStandardObservation : {}", id);
        AuditProcessStandardObservationDTO auditProcessStandardObservationDTO = auditProcessStandardObservationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(auditProcessStandardObservationDTO));
    }

    /**
     * DELETE  /audit-process-standard-observations/:id : delete the "id" auditProcessStandardObservation.
     *
     * @param id the id of the auditProcessStandardObservationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/audit-process-standard-observations/{id}")
    @Timed
    public ResponseEntity<Void> deleteAuditProcessStandardObservation(@PathVariable Long id) {
        log.debug("REST request to delete AuditProcessStandardObservation : {}", id);
        auditProcessStandardObservationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
