package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.service.CancelationTraceabilityAuditService;
import com.labausegtic.aresvi.web.rest.util.HeaderUtil;
import com.labausegtic.aresvi.web.rest.util.PaginationUtil;
import com.labausegtic.aresvi.service.dto.CancelationTraceabilityAuditDTO;
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
 * REST controller for managing CancelationTraceabilityAudit.
 */
@RestController
@RequestMapping("/api")
public class CancelationTraceabilityAuditResource {

    private final Logger log = LoggerFactory.getLogger(CancelationTraceabilityAuditResource.class);

    private static final String ENTITY_NAME = "cancelationTraceabilityAudit";

    private final CancelationTraceabilityAuditService cancelationTraceabilityAuditService;

    public CancelationTraceabilityAuditResource(CancelationTraceabilityAuditService cancelationTraceabilityAuditService) {
        this.cancelationTraceabilityAuditService = cancelationTraceabilityAuditService;
    }

    /**
     * POST  /cancelation-traceability-audits : Create a new cancelationTraceabilityAudit.
     *
     * @param cancelationTraceabilityAuditDTO the cancelationTraceabilityAuditDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cancelationTraceabilityAuditDTO, or with status 400 (Bad Request) if the cancelationTraceabilityAudit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cancelation-traceability-audits")
    @Timed
    public ResponseEntity<CancelationTraceabilityAuditDTO> createCancelationTraceabilityAudit(@Valid @RequestBody CancelationTraceabilityAuditDTO cancelationTraceabilityAuditDTO) throws URISyntaxException {
        log.debug("REST request to save CancelationTraceabilityAudit : {}", cancelationTraceabilityAuditDTO);
        if (cancelationTraceabilityAuditDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new cancelationTraceabilityAudit cannot already have an ID")).body(null);
        }
        CancelationTraceabilityAuditDTO result = cancelationTraceabilityAuditService.save(cancelationTraceabilityAuditDTO);
        return ResponseEntity.created(new URI("/api/cancelation-traceability-audits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cancelation-traceability-audits : Updates an existing cancelationTraceabilityAudit.
     *
     * @param cancelationTraceabilityAuditDTO the cancelationTraceabilityAuditDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cancelationTraceabilityAuditDTO,
     * or with status 400 (Bad Request) if the cancelationTraceabilityAuditDTO is not valid,
     * or with status 500 (Internal Server Error) if the cancelationTraceabilityAuditDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cancelation-traceability-audits")
    @Timed
    public ResponseEntity<CancelationTraceabilityAuditDTO> updateCancelationTraceabilityAudit(@Valid @RequestBody CancelationTraceabilityAuditDTO cancelationTraceabilityAuditDTO) throws URISyntaxException {
        log.debug("REST request to update CancelationTraceabilityAudit : {}", cancelationTraceabilityAuditDTO);
        if (cancelationTraceabilityAuditDTO.getId() == null) {
            return createCancelationTraceabilityAudit(cancelationTraceabilityAuditDTO);
        }
        CancelationTraceabilityAuditDTO result = cancelationTraceabilityAuditService.save(cancelationTraceabilityAuditDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cancelationTraceabilityAuditDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cancelation-traceability-audits : get all the cancelationTraceabilityAudits.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of cancelationTraceabilityAudits in body
     */
    @GetMapping("/cancelation-traceability-audits")
    @Timed
    public ResponseEntity<List<CancelationTraceabilityAuditDTO>> getAllCancelationTraceabilityAudits(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of CancelationTraceabilityAudits");
        Page<CancelationTraceabilityAuditDTO> page = cancelationTraceabilityAuditService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cancelation-traceability-audits");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cancelation-traceability-audits/:id : get the "id" cancelationTraceabilityAudit.
     *
     * @param id the id of the cancelationTraceabilityAuditDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cancelationTraceabilityAuditDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cancelation-traceability-audits/{id}")
    @Timed
    public ResponseEntity<CancelationTraceabilityAuditDTO> getCancelationTraceabilityAudit(@PathVariable Long id) {
        log.debug("REST request to get CancelationTraceabilityAudit : {}", id);
        CancelationTraceabilityAuditDTO cancelationTraceabilityAuditDTO = cancelationTraceabilityAuditService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(cancelationTraceabilityAuditDTO));
    }

    /**
     * DELETE  /cancelation-traceability-audits/:id : delete the "id" cancelationTraceabilityAudit.
     *
     * @param id the id of the cancelationTraceabilityAuditDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cancelation-traceability-audits/{id}")
    @Timed
    public ResponseEntity<Void> deleteCancelationTraceabilityAudit(@PathVariable Long id) {
        log.debug("REST request to delete CancelationTraceabilityAudit : {}", id);
        cancelationTraceabilityAuditService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
