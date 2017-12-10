package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.service.AuditAttributeAnalysisService;
import com.labausegtic.aresvi.web.rest.util.HeaderUtil;
import com.labausegtic.aresvi.service.dto.AuditAttributeAnalysisDTO;
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
 * REST controller for managing AuditAttributeAnalysis.
 */
@RestController
@RequestMapping("/api")
public class AuditAttributeAnalysisResource {

    private final Logger log = LoggerFactory.getLogger(AuditAttributeAnalysisResource.class);

    private static final String ENTITY_NAME = "auditAttributeAnalysis";

    private final AuditAttributeAnalysisService auditAttributeAnalysisService;

    public AuditAttributeAnalysisResource(AuditAttributeAnalysisService auditAttributeAnalysisService) {
        this.auditAttributeAnalysisService = auditAttributeAnalysisService;
    }

    /**
     * POST  /audit-attribute-analyses : Create a new auditAttributeAnalysis.
     *
     * @param auditAttributeAnalysisDTO the auditAttributeAnalysisDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new auditAttributeAnalysisDTO, or with status 400 (Bad Request) if the auditAttributeAnalysis has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/audit-attribute-analyses")
    @Timed
    public ResponseEntity<AuditAttributeAnalysisDTO> createAuditAttributeAnalysis(@RequestBody AuditAttributeAnalysisDTO auditAttributeAnalysisDTO) throws URISyntaxException {
        log.debug("REST request to save AuditAttributeAnalysis : {}", auditAttributeAnalysisDTO);
        if (auditAttributeAnalysisDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new auditAttributeAnalysis cannot already have an ID")).body(null);
        }
        AuditAttributeAnalysisDTO result = auditAttributeAnalysisService.save(auditAttributeAnalysisDTO);
        return ResponseEntity.created(new URI("/api/audit-attribute-analyses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /audit-attribute-analyses : Updates an existing auditAttributeAnalysis.
     *
     * @param auditAttributeAnalysisDTO the auditAttributeAnalysisDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated auditAttributeAnalysisDTO,
     * or with status 400 (Bad Request) if the auditAttributeAnalysisDTO is not valid,
     * or with status 500 (Internal Server Error) if the auditAttributeAnalysisDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/audit-attribute-analyses")
    @Timed
    public ResponseEntity<AuditAttributeAnalysisDTO> updateAuditAttributeAnalysis(@RequestBody AuditAttributeAnalysisDTO auditAttributeAnalysisDTO) throws URISyntaxException {
        log.debug("REST request to update AuditAttributeAnalysis : {}", auditAttributeAnalysisDTO);
        if (auditAttributeAnalysisDTO.getId() == null) {
            return createAuditAttributeAnalysis(auditAttributeAnalysisDTO);
        }
        AuditAttributeAnalysisDTO result = auditAttributeAnalysisService.save(auditAttributeAnalysisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, auditAttributeAnalysisDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /audit-attribute-analyses : get all the auditAttributeAnalyses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of auditAttributeAnalyses in body
     */
    @GetMapping("/audit-attribute-analyses")
    @Timed
    public List<AuditAttributeAnalysisDTO> getAllAuditAttributeAnalyses() {
        log.debug("REST request to get all AuditAttributeAnalyses");
        return auditAttributeAnalysisService.findAll();
        }

    /**
     * GET  /audit-attribute-analyses/:id : get the "id" auditAttributeAnalysis.
     *
     * @param id the id of the auditAttributeAnalysisDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the auditAttributeAnalysisDTO, or with status 404 (Not Found)
     */
    @GetMapping("/audit-attribute-analyses/{id}")
    @Timed
    public ResponseEntity<AuditAttributeAnalysisDTO> getAuditAttributeAnalysis(@PathVariable Long id) {
        log.debug("REST request to get AuditAttributeAnalysis : {}", id);
        AuditAttributeAnalysisDTO auditAttributeAnalysisDTO = auditAttributeAnalysisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(auditAttributeAnalysisDTO));
    }

    /**
     * DELETE  /audit-attribute-analyses/:id : delete the "id" auditAttributeAnalysis.
     *
     * @param id the id of the auditAttributeAnalysisDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/audit-attribute-analyses/{id}")
    @Timed
    public ResponseEntity<Void> deleteAuditAttributeAnalysis(@PathVariable Long id) {
        log.debug("REST request to delete AuditAttributeAnalysis : {}", id);
        auditAttributeAnalysisService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
