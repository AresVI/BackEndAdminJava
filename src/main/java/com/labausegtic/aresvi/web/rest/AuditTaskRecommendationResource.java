package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.service.AuditTaskRecommendationService;
import com.labausegtic.aresvi.web.rest.util.HeaderUtil;
import com.labausegtic.aresvi.web.rest.util.PaginationUtil;
import com.labausegtic.aresvi.service.dto.AuditTaskRecommendationDTO;
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
 * REST controller for managing AuditTaskRecommendation.
 */
@RestController
@RequestMapping("/api")
public class AuditTaskRecommendationResource {

    private final Logger log = LoggerFactory.getLogger(AuditTaskRecommendationResource.class);

    private static final String ENTITY_NAME = "auditTaskRecommendation";

    private final AuditTaskRecommendationService auditTaskRecommendationService;

    public AuditTaskRecommendationResource(AuditTaskRecommendationService auditTaskRecommendationService) {
        this.auditTaskRecommendationService = auditTaskRecommendationService;
    }

    /**
     * POST  /audit-task-recommendations : Create a new auditTaskRecommendation.
     *
     * @param auditTaskRecommendationDTO the auditTaskRecommendationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new auditTaskRecommendationDTO, or with status 400 (Bad Request) if the auditTaskRecommendation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/audit-task-recommendations")
    @Timed
    public ResponseEntity<AuditTaskRecommendationDTO> createAuditTaskRecommendation(@Valid @RequestBody AuditTaskRecommendationDTO auditTaskRecommendationDTO) throws URISyntaxException {
        log.debug("REST request to save AuditTaskRecommendation : {}", auditTaskRecommendationDTO);
        if (auditTaskRecommendationDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new auditTaskRecommendation cannot already have an ID")).body(null);
        }
        AuditTaskRecommendationDTO result = auditTaskRecommendationService.save(auditTaskRecommendationDTO);
        return ResponseEntity.created(new URI("/api/audit-task-recommendations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /audit-task-recommendations : Updates an existing auditTaskRecommendation.
     *
     * @param auditTaskRecommendationDTO the auditTaskRecommendationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated auditTaskRecommendationDTO,
     * or with status 400 (Bad Request) if the auditTaskRecommendationDTO is not valid,
     * or with status 500 (Internal Server Error) if the auditTaskRecommendationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/audit-task-recommendations")
    @Timed
    public ResponseEntity<AuditTaskRecommendationDTO> updateAuditTaskRecommendation(@Valid @RequestBody AuditTaskRecommendationDTO auditTaskRecommendationDTO) throws URISyntaxException {
        log.debug("REST request to update AuditTaskRecommendation : {}", auditTaskRecommendationDTO);
        if (auditTaskRecommendationDTO.getId() == null) {
            return createAuditTaskRecommendation(auditTaskRecommendationDTO);
        }
        AuditTaskRecommendationDTO result = auditTaskRecommendationService.save(auditTaskRecommendationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, auditTaskRecommendationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /audit-task-recommendations : get all the auditTaskRecommendations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of auditTaskRecommendations in body
     */
    @GetMapping("/audit-task-recommendations")
    @Timed
    public ResponseEntity<List<AuditTaskRecommendationDTO>> getAllAuditTaskRecommendations(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of AuditTaskRecommendations");
        Page<AuditTaskRecommendationDTO> page = auditTaskRecommendationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/audit-task-recommendations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /audit-task-recommendations/:id : get the "id" auditTaskRecommendation.
     *
     * @param id the id of the auditTaskRecommendationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the auditTaskRecommendationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/audit-task-recommendations/{id}")
    @Timed
    public ResponseEntity<AuditTaskRecommendationDTO> getAuditTaskRecommendation(@PathVariable Long id) {
        log.debug("REST request to get AuditTaskRecommendation : {}", id);
        AuditTaskRecommendationDTO auditTaskRecommendationDTO = auditTaskRecommendationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(auditTaskRecommendationDTO));
    }

    @GetMapping("/audit-task-recommendations/{id}")
    @Timed
    public ResponseEntity<AuditTaskRecommendationDTO> getAuditTaskRecommendationNotById(@PathVariable Long id) {
        log.debug("REST request to get AuditTaskRecommendation : {}", id);
        AuditTaskRecommendationDTO auditTaskRecommendationDTO = auditTaskRecommendationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(auditTaskRecommendationDTO));
    }

    /**
     * DELETE  /audit-task-recommendations/:id : delete the "id" auditTaskRecommendation.
     *
     * @param id the id of the auditTaskRecommendationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/audit-task-recommendations/{id}")
    @Timed
    public ResponseEntity<Void> deleteAuditTaskRecommendation(@PathVariable Long id) {
        log.debug("REST request to delete AuditTaskRecommendation : {}", id);
        auditTaskRecommendationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
