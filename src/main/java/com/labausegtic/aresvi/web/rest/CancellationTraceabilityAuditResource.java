package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.domain.StatusTraceabilityAudit;
import com.labausegtic.aresvi.security.SecurityUtils;
import com.labausegtic.aresvi.service.CancellationTraceabilityAuditService;
import com.labausegtic.aresvi.service.TraceabilityAuditService;
import com.labausegtic.aresvi.service.dto.TraceabilityAuditDTO;
import com.labausegtic.aresvi.web.rest.util.HeaderUtil;
import com.labausegtic.aresvi.web.rest.util.PaginationUtil;
import com.labausegtic.aresvi.service.dto.CancellationTraceabilityAuditDTO;
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
 * REST controller for managing CancellationTraceabilityAudit.
 */
@RestController
@RequestMapping("/api")
public class CancellationTraceabilityAuditResource {

    private final Logger log = LoggerFactory.getLogger(CancellationTraceabilityAuditResource.class);

    private static final String ENTITY_NAME = "cancellationTraceabilityAudit";

    private final CancellationTraceabilityAuditService cancellationTraceabilityAuditService;

    public CancellationTraceabilityAuditResource(CancellationTraceabilityAuditService cancellationTraceabilityAuditService) {
        this.cancellationTraceabilityAuditService = cancellationTraceabilityAuditService;
    }

    /**
     * POST  /cancellation-traceability-audits : Create a new CancellationTraceabilityAudit.
     *
     * @param cancellationTraceabilityAuditDTO the CancellationTraceabilityAuditDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new CancellationTraceabilityAuditDTO, or with status 400 (Bad Request) if the CancellationTraceabilityAudit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cancellation-traceability-audits")
    @Timed
    public ResponseEntity<CancellationTraceabilityAuditDTO> createCancellationTraceabilityAudit(@Valid @RequestBody CancellationTraceabilityAuditDTO cancellationTraceabilityAuditDTO) throws URISyntaxException {
        log.debug("REST request to save CancellationTraceabilityAudit : {}", cancellationTraceabilityAuditDTO);
        if (cancellationTraceabilityAuditDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new CancellationTraceabilityAudit cannot already have an ID")).body(null);
        }

        CancellationTraceabilityAuditDTO result = cancellationTraceabilityAuditService.cancelTraceabilityAudit(cancellationTraceabilityAuditDTO);

        return ResponseEntity.created(new URI("/api/cancellation-traceability-audits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cancellation-traceability-audits : Updates an existing CancellationTraceabilityAudit.
     *
     * @param cancellationTraceabilityAuditDTO the CancellationTraceabilityAuditDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated CancellationTraceabilityAuditDTO,
     * or with status 400 (Bad Request) if the CancellationTraceabilityAuditDTO is not valid,
     * or with status 500 (Internal Server Error) if the CancellationTraceabilityAuditDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cancellation-traceability-audits")
    @Timed
    public ResponseEntity<CancellationTraceabilityAuditDTO> updateCancellationTraceabilityAudit(@Valid @RequestBody CancellationTraceabilityAuditDTO cancellationTraceabilityAuditDTO) throws URISyntaxException {
        log.debug("REST request to update CancellationTraceabilityAudit : {}", cancellationTraceabilityAuditDTO);
        if (cancellationTraceabilityAuditDTO.getId() == null) {
            return createCancellationTraceabilityAudit(cancellationTraceabilityAuditDTO);
        }
        CancellationTraceabilityAuditDTO result = cancellationTraceabilityAuditService.save(cancellationTraceabilityAuditDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cancellation-traceability-audits : get all the CancellationTraceabilityAudits.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of CancellationTraceabilityAudits in body
     */
    @GetMapping("/cancellation-traceability-audits")
    @Timed
    public ResponseEntity<List<CancellationTraceabilityAuditDTO>> getAllCancellationTraceabilityAudits(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of CancellationTraceabilityAudits");
        Page<CancellationTraceabilityAuditDTO> page = cancellationTraceabilityAuditService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cancellation-traceability-audits");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cancellation-traceability-audits/:id : get the "id" CancellationTraceabilityAudit.
     *
     * @param id the id of the CancellationTraceabilityAuditDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the CancellationTraceabilityAuditDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cancellation-traceability-audits/{id}")
    @Timed
    public ResponseEntity<CancellationTraceabilityAuditDTO> getCancellationTraceabilityAudit(@PathVariable Long id) {
        log.debug("REST request to get CancellationTraceabilityAudit : {}", id);
        CancellationTraceabilityAuditDTO CancellationTraceabilityAuditDTO = cancellationTraceabilityAuditService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(CancellationTraceabilityAuditDTO));
    }

    /**
     * DELETE  /cancellation-traceability-audits/:id : delete the "id" CancellationTraceabilityAudit.
     *
     * @param id the id of the CancellationTraceabilityAuditDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cancellation-traceability-audits/{id}")
    @Timed
    public ResponseEntity<Void> deleteCancellationTraceabilityAudit(@PathVariable Long id) {
        log.debug("REST request to delete CancellationTraceabilityAudit : {}", id);
        cancellationTraceabilityAuditService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
