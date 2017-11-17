package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.domain.Auditor;
import com.labausegtic.aresvi.service.AuditorService;
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
 * REST controller for managing Auditor.
 */
@RestController
@RequestMapping("/api")
public class AuditorResource {

    private final Logger log = LoggerFactory.getLogger(AuditorResource.class);

    private static final String ENTITY_NAME = "auditor";

    private final AuditorService auditorService;

    public AuditorResource(AuditorService auditorService) {
        this.auditorService = auditorService;
    }

    /**
     * POST  /auditors : Create a new auditor.
     *
     * @param auditor the auditor to create
     * @return the ResponseEntity with status 201 (Created) and with body the new auditor, or with status 400 (Bad Request) if the auditor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/auditors")
    @Timed
    public ResponseEntity<Auditor> createAuditor(@Valid @RequestBody Auditor auditor) throws URISyntaxException {
        log.debug("REST request to save Auditor : {}", auditor);
        if (auditor.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new auditor cannot already have an ID")).body(null);
        }
        Auditor result = null;
        try {
            result = auditorService.save(auditor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.created(new URI("/api/auditors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /auditors : Updates an existing auditor.
     *
     * @param auditor the auditor to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated auditor,
     * or with status 400 (Bad Request) if the auditor is not valid,
     * or with status 500 (Internal Server Error) if the auditor couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/auditors")
    @Timed
    public ResponseEntity<Auditor> updateAuditor(@Valid @RequestBody Auditor auditor) throws Exception {
        log.debug("REST request to update Auditor : {}", auditor);
        if (auditor.getId() == null) {
            return createAuditor(auditor);
        }
        Auditor result = auditorService.save(auditor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, auditor.getId().toString()))
            .body(result);
    }

    /**
     * GET  /auditors : get all the auditors.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of auditors in body
     */
    @GetMapping("/auditors")
    @Timed
    public ResponseEntity<List<Auditor>> getAllAuditors(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Auditors");
        Page<Auditor> page = auditorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/auditors");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/auditors/external")
    @Timed
    public ResponseEntity<List<Auditor>> getAllExternalAuditors() {
        log.debug("REST request to get a page of Auditors");
        Page<Auditor> page = auditorService.findAllExternal(null);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/auditors");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /auditors/:id : get the "id" auditor.
     *
     * @param id the id of the auditor to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the auditor, or with status 404 (Not Found)
     */
    @GetMapping("/auditors/{id}")
    @Timed
    public ResponseEntity<Auditor> getAuditor(@PathVariable Long id) {
        log.debug("REST request to get Auditor : {}", id);
        Auditor auditor = auditorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(auditor));
    }

    /**
     * DELETE  /auditors/:id : delete the "id" auditor.
     *
     * @param id the id of the auditor to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/auditors/{id}")
    @Timed
    public ResponseEntity<Void> deleteAuditor(@PathVariable Long id) {
        log.debug("REST request to delete Auditor : {}", id);
        auditorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
