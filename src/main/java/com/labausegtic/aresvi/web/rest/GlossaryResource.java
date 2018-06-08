package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.service.GlossaryService;
import com.labausegtic.aresvi.web.rest.util.HeaderUtil;
import com.labausegtic.aresvi.web.rest.util.PaginationUtil;
import com.labausegtic.aresvi.service.dto.GlossaryDTO;
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
 * REST controller for managing Glossary.
 */
@RestController
@RequestMapping("/api")
public class GlossaryResource {

    private final Logger log = LoggerFactory.getLogger(GlossaryResource.class);

    private static final String ENTITY_NAME = "glossary";

    private final GlossaryService glossaryService;

    public GlossaryResource(GlossaryService glossaryService) {
        this.glossaryService = glossaryService;
    }

    /**
     * POST  /glossaries : Create a new glossary.
     *
     * @param glossaryDTO the glossaryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new glossaryDTO, or with status 400 (Bad Request) if the glossary has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/glossaries")
    @Timed
    public ResponseEntity<GlossaryDTO> createGlossary(@Valid @RequestBody GlossaryDTO glossaryDTO) throws URISyntaxException {
        log.debug("REST request to save Glossary : {}", glossaryDTO);
        if (glossaryDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new glossary cannot already have an ID")).body(null);
        }
        GlossaryDTO result = glossaryService.save(glossaryDTO);
        return ResponseEntity.created(new URI("/api/glossaries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /glossaries : Updates an existing glossary.
     *
     * @param glossaryDTO the glossaryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated glossaryDTO,
     * or with status 400 (Bad Request) if the glossaryDTO is not valid,
     * or with status 500 (Internal Server Error) if the glossaryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/glossaries")
    @Timed
    public ResponseEntity<GlossaryDTO> updateGlossary(@Valid @RequestBody GlossaryDTO glossaryDTO) throws URISyntaxException {
        log.debug("REST request to update Glossary : {}", glossaryDTO);
        if (glossaryDTO.getId() == null) {
            return createGlossary(glossaryDTO);
        }
        GlossaryDTO result = glossaryService.save(glossaryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, glossaryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /glossaries : get all the glossaries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of glossaries in body
     */
    @GetMapping("/glossaries")
    @Timed
    public ResponseEntity<List<GlossaryDTO>> getAllGlossaries(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Glossaries");
        Page<GlossaryDTO> page = glossaryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/glossaries");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /glossaries/:id : get the "id" glossary.
     *
     * @param id the id of the glossaryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the glossaryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/glossaries/{id}")
    @Timed
    public ResponseEntity<GlossaryDTO> getGlossary(@PathVariable Long id) {
        log.debug("REST request to get Glossary : {}", id);
        GlossaryDTO glossaryDTO = glossaryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(glossaryDTO));
    }

    /**
     * DELETE  /glossaries/:id : delete the "id" glossary.
     *
     * @param id the id of the glossaryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/glossaries/{id}")
    @Timed
    public ResponseEntity<Void> deleteGlossary(@PathVariable Long id) {
        log.debug("REST request to delete Glossary : {}", id);
        glossaryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
