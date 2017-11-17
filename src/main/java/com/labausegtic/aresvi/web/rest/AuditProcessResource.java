package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.service.AuditProcessService;
import com.labausegtic.aresvi.service.ContainerService;
import com.labausegtic.aresvi.service.dto.AuditProcessCompleteDTO;
import com.labausegtic.aresvi.service.dto.ContainerCompleteDTO;
import com.labausegtic.aresvi.service.dto.ContainerDTO;
import com.labausegtic.aresvi.web.rest.util.HeaderUtil;
import com.labausegtic.aresvi.web.rest.util.PaginationUtil;
import com.labausegtic.aresvi.service.dto.AuditProcessDTO;
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
import java.util.Set;

/**
 * REST controller for managing AuditProcess.
 */
@RestController
@RequestMapping("/api")
public class AuditProcessResource {

    private final Logger log = LoggerFactory.getLogger(AuditProcessResource.class);

    private static final String ENTITY_NAME = "auditProcess";

    private final AuditProcessService auditProcessService;
    private final ContainerService containerService;

    public AuditProcessResource(AuditProcessService auditProcessService, ContainerService containerService) {
        this.auditProcessService = auditProcessService;
        this.containerService = containerService;
    }

    /**
     * POST  /audit-processes : Create a new auditProcess.
     *
     * @param auditProcessDTO the auditProcessDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new auditProcessDTO, or with status 400 (Bad Request) if the auditProcess has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/audit-processes")
    @Timed
    public ResponseEntity<AuditProcessDTO> createAuditProcess(@Valid @RequestBody AuditProcessDTO auditProcessDTO) throws URISyntaxException {
        log.debug("REST request to save AuditProcess : {}", auditProcessDTO);
        if (auditProcessDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new auditProcess cannot already have an ID")).body(null);
        }
        AuditProcessDTO result = auditProcessService.save(auditProcessDTO);
        return ResponseEntity.created(new URI("/api/audit-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /audit-processes : Updates an existing auditProcess.
     *
     * @param auditProcessDTO the auditProcessDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated auditProcessDTO,
     * or with status 400 (Bad Request) if the auditProcessDTO is not valid,
     * or with status 500 (Internal Server Error) if the auditProcessDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/audit-processes")
    @Timed
    public ResponseEntity<AuditProcessDTO> updateAuditProcess(@Valid @RequestBody AuditProcessDTO auditProcessDTO) throws URISyntaxException {
        log.debug("REST request to update AuditProcess : {}", auditProcessDTO);
        if (auditProcessDTO.getId() == null) {
            return createAuditProcess(auditProcessDTO);
        }
        AuditProcessDTO result = auditProcessService.save(auditProcessDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, auditProcessDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /audit-processes : get all the auditProcesses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of auditProcesses in body
     */
    @GetMapping("/audit-processes")
    @Timed
    public ResponseEntity<List<AuditProcessDTO>> getAllAuditProcesses(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of AuditProcesses");
        Page<AuditProcessDTO> page = auditProcessService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/audit-processes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /audit-processes/:id : get the "id" auditProcess.
     *
     * @param id the id of the auditProcessDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the auditProcessDTO, or with status 404 (Not Found)
     */
    @GetMapping("/audit-processes/{id}")
    @Timed
    public ResponseEntity<AuditProcessDTO> getAuditProcess(@PathVariable Long id) {
        log.debug("REST request to get AuditProcess : {}", id);
        AuditProcessDTO auditProcessDTO = auditProcessService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(auditProcessDTO));
    }

    @GetMapping("/audit-processes/{id}/complete")
    @Timed
    public ResponseEntity<AuditProcessCompleteDTO> getAuditProcessComplete(@PathVariable Long id) {
        log.debug("REST request to get AuditProcess : {}", id);
        AuditProcessDTO auditProcessDTO = auditProcessService.findOne(id);

        Set<ContainerCompleteDTO> content = containerService.findAllByAuditProcess_Id(auditProcessDTO.getId());

        AuditProcessCompleteDTO auditProcessCompleteDTO = new AuditProcessCompleteDTO();

        auditProcessCompleteDTO.setId(auditProcessDTO.getId());

        auditProcessCompleteDTO.setName(auditProcessDTO.getName());

        auditProcessCompleteDTO.setContainerSet(content);

        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(auditProcessCompleteDTO));
    }

    /**
     * DELETE  /audit-processes/:id : delete the "id" auditProcess.
     *
     * @param id the id of the auditProcessDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/audit-processes/{id}")
    @Timed
    public ResponseEntity<Void> deleteAuditProcess(@PathVariable Long id) {
        log.debug("REST request to delete AuditProcess : {}", id);
        auditProcessService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
