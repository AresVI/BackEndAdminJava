package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.service.RecommendationAttributeRecommendationService;
import com.labausegtic.aresvi.web.rest.util.HeaderUtil;
import com.labausegtic.aresvi.service.dto.RecommendationAttributeRecommendationDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing RecommendationAttributeRecommendation.
 */
@RestController
@RequestMapping("/api")
public class RecommendationAttributeRecommendationResource {

    private final Logger log = LoggerFactory.getLogger(RecommendationAttributeRecommendationResource.class);

    private static final String ENTITY_NAME = "recommendationAttributeRecommendation";

    private final RecommendationAttributeRecommendationService recommendationAttributeRecommendationService;

    public RecommendationAttributeRecommendationResource(RecommendationAttributeRecommendationService recommendationAttributeRecommendationService) {
        this.recommendationAttributeRecommendationService = recommendationAttributeRecommendationService;
    }

    /**
     * POST  /recommendation-attribute-recommendations : Create a new recommendationAttributeRecommendation.
     *
     * @param recommendationAttributeRecommendationDTO the recommendationAttributeRecommendationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new recommendationAttributeRecommendationDTO, or with status 400 (Bad Request) if the recommendationAttributeRecommendation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/recommendation-attribute-recommendations")
    @Timed
    public ResponseEntity<RecommendationAttributeRecommendationDTO> createRecommendationAttributeRecommendation(@Valid @RequestBody RecommendationAttributeRecommendationDTO recommendationAttributeRecommendationDTO) throws URISyntaxException {
        log.debug("REST request to save RecommendationAttributeRecommendation : {}", recommendationAttributeRecommendationDTO);
        if (recommendationAttributeRecommendationDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new recommendationAttributeRecommendation cannot already have an ID")).body(null);
        }
        RecommendationAttributeRecommendationDTO result = recommendationAttributeRecommendationService.save(recommendationAttributeRecommendationDTO);
        return ResponseEntity.created(new URI("/api/recommendation-attribute-recommendations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /recommendation-attribute-recommendations : Updates an existing recommendationAttributeRecommendation.
     *
     * @param recommendationAttributeRecommendationDTO the recommendationAttributeRecommendationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated recommendationAttributeRecommendationDTO,
     * or with status 400 (Bad Request) if the recommendationAttributeRecommendationDTO is not valid,
     * or with status 500 (Internal Server Error) if the recommendationAttributeRecommendationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/recommendation-attribute-recommendations")
    @Timed
    public ResponseEntity<RecommendationAttributeRecommendationDTO> updateRecommendationAttributeRecommendation(@Valid @RequestBody RecommendationAttributeRecommendationDTO recommendationAttributeRecommendationDTO) throws URISyntaxException {
        log.debug("REST request to update RecommendationAttributeRecommendation : {}", recommendationAttributeRecommendationDTO);
        if (recommendationAttributeRecommendationDTO.getId() == null) {
            return createRecommendationAttributeRecommendation(recommendationAttributeRecommendationDTO);
        }
        RecommendationAttributeRecommendationDTO result = recommendationAttributeRecommendationService.save(recommendationAttributeRecommendationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, recommendationAttributeRecommendationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /recommendation-attribute-recommendations : get all the recommendationAttributeRecommendations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of recommendationAttributeRecommendations in body
     */
    @GetMapping("/recommendation-attribute-recommendations")
    @Timed
    public List<RecommendationAttributeRecommendationDTO> getAllRecommendationAttributeRecommendations() {
        log.debug("REST request to get all RecommendationAttributeRecommendations");
        return recommendationAttributeRecommendationService.findAll();
        }

    /**
     * GET  /recommendation-attribute-recommendations/:id : get the "id" recommendationAttributeRecommendation.
     *
     * @param id the id of the recommendationAttributeRecommendationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the recommendationAttributeRecommendationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/recommendation-attribute-recommendations/{id}")
    @Timed
    public ResponseEntity<RecommendationAttributeRecommendationDTO> getRecommendationAttributeRecommendation(@PathVariable Long id) {
        log.debug("REST request to get RecommendationAttributeRecommendation : {}", id);
        RecommendationAttributeRecommendationDTO recommendationAttributeRecommendationDTO = recommendationAttributeRecommendationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(recommendationAttributeRecommendationDTO));
    }

    /**
     * DELETE  /recommendation-attribute-recommendations/:id : delete the "id" recommendationAttributeRecommendation.
     *
     * @param id the id of the recommendationAttributeRecommendationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/recommendation-attribute-recommendations/{id}")
    @Timed
    public ResponseEntity<Void> deleteRecommendationAttributeRecommendation(@PathVariable Long id) {
        log.debug("REST request to delete RecommendationAttributeRecommendation : {}", id);
        recommendationAttributeRecommendationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
