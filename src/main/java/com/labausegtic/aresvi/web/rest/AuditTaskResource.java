package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.service.AuditTaskService;
import com.labausegtic.aresvi.web.rest.util.HeaderUtil;
import com.labausegtic.aresvi.web.rest.util.PaginationUtil;
import com.labausegtic.aresvi.service.dto.AuditTaskDTO;
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
 * REST controller for managing AuditTask.
 */
@RestController
@RequestMapping("/api")
public class AuditTaskResource {

    private final Logger log = LoggerFactory.getLogger(AuditTaskResource.class);

    private static final String ENTITY_NAME = "auditTask";

    private final AuditTaskService auditTaskService;

    public AuditTaskResource(AuditTaskService auditTaskService) {
        this.auditTaskService = auditTaskService;
    }

    /**
     * POST  /audit-tasks : Create a new auditTask.
     *
     * @param auditTaskDTO the auditTaskDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new auditTaskDTO, or with status 400 (Bad Request) if the auditTask has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/audit-tasks")
    @Timed
    public ResponseEntity<AuditTaskDTO> createAuditTask(@Valid @RequestBody AuditTaskDTO auditTaskDTO) throws URISyntaxException {
        log.debug("REST request to save AuditTask : {}", auditTaskDTO);
        if (auditTaskDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new auditTask cannot already have an ID")).body(null);
        }
        AuditTaskDTO result = auditTaskService.save(auditTaskDTO);
        return ResponseEntity.created(new URI("/api/audit-tasks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /audit-tasks : Updates an existing auditTask.
     *
     * @param auditTaskDTO the auditTaskDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated auditTaskDTO,
     * or with status 400 (Bad Request) if the auditTaskDTO is not valid,
     * or with status 500 (Internal Server Error) if the auditTaskDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/audit-tasks")
    @Timed
    public ResponseEntity<AuditTaskDTO> updateAuditTask(@Valid @RequestBody AuditTaskDTO auditTaskDTO) throws URISyntaxException {
        log.debug("REST request to update AuditTask : {}", auditTaskDTO);
        if (auditTaskDTO.getId() == null) {
            return createAuditTask(auditTaskDTO);
        }
        AuditTaskDTO result = auditTaskService.save(auditTaskDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, auditTaskDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /audit-tasks : get all the auditTasks.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of auditTasks in body
     */
    @GetMapping("/audit-tasks")
    @Timed
    public ResponseEntity<List<AuditTaskDTO>> getAllAuditTasks(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of AuditTasks");
        Page<AuditTaskDTO> page = auditTaskService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/audit-tasks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /audit-tasks/:id : get the "id" auditTask.
     *
     * @param id the id of the auditTaskDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the auditTaskDTO, or with status 404 (Not Found)
     */
    @GetMapping("/audit-tasks/{id}")
    @Timed
    public ResponseEntity<AuditTaskDTO> getAuditTask(@PathVariable Long id) {
        log.debug("REST request to get AuditTask : {}", id);
        AuditTaskDTO auditTaskDTO = auditTaskService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(auditTaskDTO));
    }

    /**
     * DELETE  /audit-tasks/:id : delete the "id" auditTask.
     *
     * @param id the id of the auditTaskDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/audit-tasks/{id}")
    @Timed
    public ResponseEntity<Void> deleteAuditTask(@PathVariable Long id) {
        log.debug("REST request to delete AuditTask : {}", id);
        auditTaskService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
