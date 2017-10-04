package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.service.RecommendationService;
import com.labausegtic.aresvi.web.rest.util.HeaderUtil;
import com.labausegtic.aresvi.web.rest.util.PaginationUtil;
import com.labausegtic.aresvi.service.dto.RecommendationDTO;
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
 * REST controller for managing Recommendation.
 */
@RestController
@RequestMapping("/api")
public class RecommendationResource {

    private final Logger log = LoggerFactory.getLogger(RecommendationResource.class);

    private static final String ENTITY_NAME = "recommendation";

    private final RecommendationService recommendationService;

    public RecommendationResource(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    /**
     * POST  /recommendations : Create a new recommendation.
     *
     * @param recommendationDTO the recommendationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new recommendationDTO, or with status 400 (Bad Request) if the recommendation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/recommendations")
    @Timed
    public ResponseEntity<RecommendationDTO> createRecommendation(@Valid @RequestBody RecommendationDTO recommendationDTO) throws URISyntaxException {
        log.debug("REST request to save Recommendation : {}", recommendationDTO);
        if (recommendationDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new recommendation cannot already have an ID")).body(null);
        }
        RecommendationDTO result = recommendationService.save(recommendationDTO);
        return ResponseEntity.created(new URI("/api/recommendations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /recommendations : Updates an existing recommendation.
     *
     * @param recommendationDTO the recommendationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated recommendationDTO,
     * or with status 400 (Bad Request) if the recommendationDTO is not valid,
     * or with status 500 (Internal Server Error) if the recommendationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/recommendations")
    @Timed
    public ResponseEntity<RecommendationDTO> updateRecommendation(@Valid @RequestBody RecommendationDTO recommendationDTO) throws URISyntaxException {
        log.debug("REST request to update Recommendation : {}", recommendationDTO);
        if (recommendationDTO.getId() == null) {
            return createRecommendation(recommendationDTO);
        }
        RecommendationDTO result = recommendationService.save(recommendationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, recommendationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /recommendations : get all the recommendations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of recommendations in body
     */
    @GetMapping("/recommendations")
    @Timed
    public ResponseEntity<List<RecommendationDTO>> getAllRecommendations(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Recommendations");
        Page<RecommendationDTO> page = recommendationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/recommendations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /recommendations/:id : get the "id" recommendation.
     *
     * @param id the id of the recommendationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the recommendationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/recommendations/{id}")
    @Timed
    public ResponseEntity<RecommendationDTO> getRecommendation(@PathVariable Long id) {
        log.debug("REST request to get Recommendation : {}", id);
        RecommendationDTO recommendationDTO = recommendationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(recommendationDTO));
    }

    /**
     * DELETE  /recommendations/:id : delete the "id" recommendation.
     *
     * @param id the id of the recommendationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/recommendations/{id}")
    @Timed
    public ResponseEntity<Void> deleteRecommendation(@PathVariable Long id) {
        log.debug("REST request to delete Recommendation : {}", id);
        recommendationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
