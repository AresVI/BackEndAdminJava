package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.service.AuditProcessRecommendationService;
import com.labausegtic.aresvi.web.rest.util.HeaderUtil;
import com.labausegtic.aresvi.web.rest.util.PaginationUtil;
import com.labausegtic.aresvi.service.dto.AuditProcessRecommendationDTO;
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
 * REST controller for managing AuditProcessRecommendation.
 */
@RestController
@RequestMapping("/api")
public class AuditProcessRecommendationResource {

    private final Logger log = LoggerFactory.getLogger(AuditProcessRecommendationResource.class);

    private static final String ENTITY_NAME = "auditProcessRecommendation";

    private final AuditProcessRecommendationService auditProcessRecommendationService;

    public AuditProcessRecommendationResource(AuditProcessRecommendationService auditProcessRecommendationService) {
        this.auditProcessRecommendationService = auditProcessRecommendationService;
    }

    /**
     * POST  /audit-process-recommendations : Create a new auditProcessRecommendation.
     *
     * @param auditProcessRecommendationDTO the auditProcessRecommendationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new auditProcessRecommendationDTO, or with status 400 (Bad Request) if the auditProcessRecommendation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/audit-process-recommendations")
    @Timed
    public ResponseEntity<AuditProcessRecommendationDTO> createAuditProcessRecommendation(@Valid @RequestBody AuditProcessRecommendationDTO auditProcessRecommendationDTO) throws URISyntaxException {
        log.debug("REST request to save AuditProcessRecommendation : {}", auditProcessRecommendationDTO);
        if (auditProcessRecommendationDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new auditProcessRecommendation cannot already have an ID")).body(null);
        }
        AuditProcessRecommendationDTO result = auditProcessRecommendationService.save(auditProcessRecommendationDTO);
        return ResponseEntity.created(new URI("/api/audit-process-recommendations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /audit-process-recommendations : Updates an existing auditProcessRecommendation.
     *
     * @param auditProcessRecommendationDTO the auditProcessRecommendationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated auditProcessRecommendationDTO,
     * or with status 400 (Bad Request) if the auditProcessRecommendationDTO is not valid,
     * or with status 500 (Internal Server Error) if the auditProcessRecommendationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/audit-process-recommendations")
    @Timed
    public ResponseEntity<AuditProcessRecommendationDTO> updateAuditProcessRecommendation(@Valid @RequestBody AuditProcessRecommendationDTO auditProcessRecommendationDTO) throws URISyntaxException {
        log.debug("REST request to update AuditProcessRecommendation : {}", auditProcessRecommendationDTO);
        if (auditProcessRecommendationDTO.getId() == null) {
            return createAuditProcessRecommendation(auditProcessRecommendationDTO);
        }
        AuditProcessRecommendationDTO result = auditProcessRecommendationService.save(auditProcessRecommendationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, auditProcessRecommendationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /audit-process-recommendations : get all the auditProcessRecommendations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of auditProcessRecommendations in body
     */
    @GetMapping("/audit-process-recommendations")
    @Timed
    public ResponseEntity<List<AuditProcessRecommendationDTO>> getAllAuditProcessRecommendations(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of AuditProcessRecommendations");
        Page<AuditProcessRecommendationDTO> page = auditProcessRecommendationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/audit-process-recommendations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /audit-process-recommendations/:id : get the "id" auditProcessRecommendation.
     *
     * @param id the id of the auditProcessRecommendationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the auditProcessRecommendationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/audit-process-recommendations/{id}")
    @Timed
    public ResponseEntity<AuditProcessRecommendationDTO> getAuditProcessRecommendation(@PathVariable Long id) {
        log.debug("REST request to get AuditProcessRecommendation : {}", id);
        AuditProcessRecommendationDTO auditProcessRecommendationDTO = auditProcessRecommendationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(auditProcessRecommendationDTO));
    }

    /**
     * DELETE  /audit-process-recommendations/:id : delete the "id" auditProcessRecommendation.
     *
     * @param id the id of the auditProcessRecommendationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/audit-process-recommendations/{id}")
    @Timed
    public ResponseEntity<Void> deleteAuditProcessRecommendation(@PathVariable Long id) {
        log.debug("REST request to delete AuditProcessRecommendation : {}", id);
        auditProcessRecommendationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
