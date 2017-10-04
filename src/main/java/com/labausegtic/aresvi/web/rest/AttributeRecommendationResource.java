package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.service.AttributeRecommendationService;
import com.labausegtic.aresvi.web.rest.util.HeaderUtil;
import com.labausegtic.aresvi.web.rest.util.PaginationUtil;
import com.labausegtic.aresvi.service.dto.AttributeRecommendationDTO;
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
 * REST controller for managing AttributeRecommendation.
 */
@RestController
@RequestMapping("/api")
public class AttributeRecommendationResource {

    private final Logger log = LoggerFactory.getLogger(AttributeRecommendationResource.class);

    private static final String ENTITY_NAME = "attributeRecommendation";

    private final AttributeRecommendationService attributeRecommendationService;

    public AttributeRecommendationResource(AttributeRecommendationService attributeRecommendationService) {
        this.attributeRecommendationService = attributeRecommendationService;
    }

    /**
     * POST  /attribute-recommendations : Create a new attributeRecommendation.
     *
     * @param attributeRecommendationDTO the attributeRecommendationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new attributeRecommendationDTO, or with status 400 (Bad Request) if the attributeRecommendation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/attribute-recommendations")
    @Timed
    public ResponseEntity<AttributeRecommendationDTO> createAttributeRecommendation(@Valid @RequestBody AttributeRecommendationDTO attributeRecommendationDTO) throws URISyntaxException {
        log.debug("REST request to save AttributeRecommendation : {}", attributeRecommendationDTO);
        if (attributeRecommendationDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new attributeRecommendation cannot already have an ID")).body(null);
        }
        AttributeRecommendationDTO result = attributeRecommendationService.save(attributeRecommendationDTO);
        return ResponseEntity.created(new URI("/api/attribute-recommendations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /attribute-recommendations : Updates an existing attributeRecommendation.
     *
     * @param attributeRecommendationDTO the attributeRecommendationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated attributeRecommendationDTO,
     * or with status 400 (Bad Request) if the attributeRecommendationDTO is not valid,
     * or with status 500 (Internal Server Error) if the attributeRecommendationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/attribute-recommendations")
    @Timed
    public ResponseEntity<AttributeRecommendationDTO> updateAttributeRecommendation(@Valid @RequestBody AttributeRecommendationDTO attributeRecommendationDTO) throws URISyntaxException {
        log.debug("REST request to update AttributeRecommendation : {}", attributeRecommendationDTO);
        if (attributeRecommendationDTO.getId() == null) {
            return createAttributeRecommendation(attributeRecommendationDTO);
        }
        AttributeRecommendationDTO result = attributeRecommendationService.save(attributeRecommendationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, attributeRecommendationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /attribute-recommendations : get all the attributeRecommendations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of attributeRecommendations in body
     */
    @GetMapping("/attribute-recommendations")
    @Timed
    public ResponseEntity<List<AttributeRecommendationDTO>> getAllAttributeRecommendations(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of AttributeRecommendations");
        Page<AttributeRecommendationDTO> page = attributeRecommendationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/attribute-recommendations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /attribute-recommendations/:id : get the "id" attributeRecommendation.
     *
     * @param id the id of the attributeRecommendationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the attributeRecommendationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/attribute-recommendations/{id}")
    @Timed
    public ResponseEntity<AttributeRecommendationDTO> getAttributeRecommendation(@PathVariable Long id) {
        log.debug("REST request to get AttributeRecommendation : {}", id);
        AttributeRecommendationDTO attributeRecommendationDTO = attributeRecommendationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(attributeRecommendationDTO));
    }

    /**
     * DELETE  /attribute-recommendations/:id : delete the "id" attributeRecommendation.
     *
     * @param id the id of the attributeRecommendationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/attribute-recommendations/{id}")
    @Timed
    public ResponseEntity<Void> deleteAttributeRecommendation(@PathVariable Long id) {
        log.debug("REST request to delete AttributeRecommendation : {}", id);
        attributeRecommendationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
