package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.service.RecommendationAttributeService;
import com.labausegtic.aresvi.web.rest.util.HeaderUtil;
import com.labausegtic.aresvi.service.dto.RecommendationAttributeDTO;
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
 * REST controller for managing RecommendationAttribute.
 */
@RestController
@RequestMapping("/api")
public class RecommendationAttributeResource {

    private final Logger log = LoggerFactory.getLogger(RecommendationAttributeResource.class);

    private static final String ENTITY_NAME = "recommendationAttribute";

    private final RecommendationAttributeService recommendationAttributeService;

    public RecommendationAttributeResource(RecommendationAttributeService recommendationAttributeService) {
        this.recommendationAttributeService = recommendationAttributeService;
    }

    /**
     * POST  /recommendation-attributes : Create a new recommendationAttribute.
     *
     * @param recommendationAttributeDTO the recommendationAttributeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new recommendationAttributeDTO, or with status 400 (Bad Request) if the recommendationAttribute has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/recommendation-attributes")
    @Timed
    public ResponseEntity<RecommendationAttributeDTO> createRecommendationAttribute(@Valid @RequestBody RecommendationAttributeDTO recommendationAttributeDTO) throws URISyntaxException {
        log.debug("REST request to save RecommendationAttribute : {}", recommendationAttributeDTO);
        if (recommendationAttributeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new recommendationAttribute cannot already have an ID")).body(null);
        }
        RecommendationAttributeDTO result = recommendationAttributeService.save(recommendationAttributeDTO);
        return ResponseEntity.created(new URI("/api/recommendation-attributes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /recommendation-attributes : Updates an existing recommendationAttribute.
     *
     * @param recommendationAttributeDTO the recommendationAttributeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated recommendationAttributeDTO,
     * or with status 400 (Bad Request) if the recommendationAttributeDTO is not valid,
     * or with status 500 (Internal Server Error) if the recommendationAttributeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/recommendation-attributes")
    @Timed
    public ResponseEntity<RecommendationAttributeDTO> updateRecommendationAttribute(@Valid @RequestBody RecommendationAttributeDTO recommendationAttributeDTO) throws URISyntaxException {
        log.debug("REST request to update RecommendationAttribute : {}", recommendationAttributeDTO);
        if (recommendationAttributeDTO.getId() == null) {
            return createRecommendationAttribute(recommendationAttributeDTO);
        }
        RecommendationAttributeDTO result = recommendationAttributeService.save(recommendationAttributeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, recommendationAttributeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /recommendation-attributes : get all the recommendationAttributes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of recommendationAttributes in body
     */
    @GetMapping("/recommendation-attributes")
    @Timed
    public List<RecommendationAttributeDTO> getAllRecommendationAttributes() {
        log.debug("REST request to get all RecommendationAttributes");
        return recommendationAttributeService.findAll();
        }

    /**
     * GET  /recommendation-attributes/:id : get the "id" recommendationAttribute.
     *
     * @param id the id of the recommendationAttributeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the recommendationAttributeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/recommendation-attributes/{id}")
    @Timed
    public ResponseEntity<RecommendationAttributeDTO> getRecommendationAttribute(@PathVariable Long id) {
        log.debug("REST request to get RecommendationAttribute : {}", id);
        RecommendationAttributeDTO recommendationAttributeDTO = recommendationAttributeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(recommendationAttributeDTO));
    }

    /**
     * DELETE  /recommendation-attributes/:id : delete the "id" recommendationAttribute.
     *
     * @param id the id of the recommendationAttributeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/recommendation-attributes/{id}")
    @Timed
    public ResponseEntity<Void> deleteRecommendationAttribute(@PathVariable Long id) {
        log.debug("REST request to delete RecommendationAttribute : {}", id);
        recommendationAttributeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
