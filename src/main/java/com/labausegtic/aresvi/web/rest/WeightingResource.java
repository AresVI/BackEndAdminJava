package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.service.WeightingService;
import com.labausegtic.aresvi.web.rest.util.HeaderUtil;
import com.labausegtic.aresvi.web.rest.util.PaginationUtil;
import com.labausegtic.aresvi.service.dto.WeightingDTO;
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
 * REST controller for managing Weighting.
 */
@RestController
@RequestMapping("/api")
public class WeightingResource {

    private final Logger log = LoggerFactory.getLogger(WeightingResource.class);

    private static final String ENTITY_NAME = "weighting";

    private final WeightingService weightingService;

    public WeightingResource(WeightingService weightingService) {
        this.weightingService = weightingService;
    }

    /**
     * POST  /weightings : Create a new weighting.
     *
     * @param weightingDTO the weightingDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new weightingDTO, or with status 400 (Bad Request) if the weighting has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/weightings")
    @Timed
    public ResponseEntity<WeightingDTO> createWeighting(@Valid @RequestBody WeightingDTO weightingDTO) throws URISyntaxException {
        log.debug("REST request to save Weighting : {}", weightingDTO);
        if (weightingDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new weighting cannot already have an ID")).body(null);
        }
        WeightingDTO result = weightingService.save(weightingDTO);
        return ResponseEntity.created(new URI("/api/weightings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /weightings : Updates an existing weighting.
     *
     * @param weightingDTO the weightingDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated weightingDTO,
     * or with status 400 (Bad Request) if the weightingDTO is not valid,
     * or with status 500 (Internal Server Error) if the weightingDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/weightings")
    @Timed
    public ResponseEntity<WeightingDTO> updateWeighting(@Valid @RequestBody WeightingDTO weightingDTO) throws URISyntaxException {
        log.debug("REST request to update Weighting : {}", weightingDTO);
        if (weightingDTO.getId() == null) {
            return createWeighting(weightingDTO);
        }
        WeightingDTO result = weightingService.save(weightingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, weightingDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /weightings : get all the weightings.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of weightings in body
     */
    @GetMapping("/weightings")
    @Timed
    public ResponseEntity<List<WeightingDTO>> getAllWeightings(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Weightings");
        Page<WeightingDTO> page = weightingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/weightings");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /weightings/:id : get the "id" weighting.
     *
     * @param id the id of the weightingDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the weightingDTO, or with status 404 (Not Found)
     */
    @GetMapping("/weightings/{id}")
    @Timed
    public ResponseEntity<WeightingDTO> getWeighting(@PathVariable Long id) {
        log.debug("REST request to get Weighting : {}", id);
        WeightingDTO weightingDTO = weightingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(weightingDTO));
    }

    /**
     * DELETE  /weightings/:id : delete the "id" weighting.
     *
     * @param id the id of the weightingDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/weightings/{id}")
    @Timed
    public ResponseEntity<Void> deleteWeighting(@PathVariable Long id) {
        log.debug("REST request to delete Weighting : {}", id);
        weightingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
