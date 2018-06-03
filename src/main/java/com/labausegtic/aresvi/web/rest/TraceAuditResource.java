package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.service.TraceAuditService;
import com.labausegtic.aresvi.web.rest.util.HeaderUtil;
import com.labausegtic.aresvi.service.dto.TraceAuditDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TraceAudit.
 */
@RestController
@RequestMapping("/api")
public class TraceAuditResource {

    private final Logger log = LoggerFactory.getLogger(TraceAuditResource.class);

    private static final String ENTITY_NAME = "traceAudit";

    private final TraceAuditService traceAuditService;

    public TraceAuditResource(TraceAuditService traceAuditService) {
        this.traceAuditService = traceAuditService;
    }

    /**
     * POST  /trace-audits : Create a new traceAudit.
     *
     * @param traceAuditDTO the traceAuditDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new traceAuditDTO, or with status 400 (Bad Request) if the traceAudit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/trace-audits")
    @Timed
    public ResponseEntity<TraceAuditDTO> createTraceAudit(@RequestBody TraceAuditDTO traceAuditDTO) throws URISyntaxException {
        log.debug("REST request to save TraceAudit : {}", traceAuditDTO);
        if (traceAuditDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new traceAudit cannot already have an ID")).body(null);
        }
        TraceAuditDTO result = traceAuditService.save(traceAuditDTO);
        return ResponseEntity.created(new URI("/api/trace-audits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /trace-audits : Updates an existing traceAudit.
     *
     * @param traceAuditDTO the traceAuditDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated traceAuditDTO,
     * or with status 400 (Bad Request) if the traceAuditDTO is not valid,
     * or with status 500 (Internal Server Error) if the traceAuditDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/trace-audits")
    @Timed
    public ResponseEntity<TraceAuditDTO> updateTraceAudit(@RequestBody TraceAuditDTO traceAuditDTO) throws URISyntaxException {
        log.debug("REST request to update TraceAudit : {}", traceAuditDTO);
        if (traceAuditDTO.getId() == null) {
            return createTraceAudit(traceAuditDTO);
        }
        TraceAuditDTO result = traceAuditService.save(traceAuditDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, traceAuditDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /trace-audits : get all the traceAudits.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of traceAudits in body
     */
    @GetMapping("/traceability-audits/{traceabilityAuditId}/trace-audits")
    @Timed
    public List<TraceAuditDTO> getAllTraceAudits(@PathVariable Long traceabilityAuditId) {
        log.debug("REST request to get all TraceAudits");
        return traceAuditService.findAll(traceabilityAuditId);
        }

    /**
     * GET  /trace-audits/:id : get the "id" traceAudit.
     *
     * @param id the id of the traceAuditDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the traceAuditDTO, or with status 404 (Not Found)
     */
    @GetMapping("/trace-audits/{id}")
    @Timed
    public ResponseEntity<TraceAuditDTO> getTraceAudit(@PathVariable Long id) {
        log.debug("REST request to get TraceAudit : {}", id);
        TraceAuditDTO traceAuditDTO = traceAuditService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(traceAuditDTO));
    }

    /**
     * DELETE  /trace-audits/:id : delete the "id" traceAudit.
     *
     * @param id the id of the traceAuditDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/trace-audits/{id}")
    @Timed
    public ResponseEntity<Void> deleteTraceAudit(@PathVariable Long id) {
        log.debug("REST request to delete TraceAudit : {}", id);
        traceAuditService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
