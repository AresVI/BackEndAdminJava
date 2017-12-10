package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.service.RecommendationNextCategoryService;
import com.labausegtic.aresvi.service.dto.AuditProcessRecommendationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing Weighting.
 */
@RestController
@RequestMapping("/api")
public class RecommendationNextCategoryResource {

    private final Logger log = LoggerFactory.getLogger(RecommendationNextCategoryResource.class);

    private final RecommendationNextCategoryService recommendationNextCategoryService;

    public RecommendationNextCategoryResource(RecommendationNextCategoryService recommendationNextCategoryService) {
        this.recommendationNextCategoryService = recommendationNextCategoryService;
    }

    /**
     * GET  /weightings : get all the weightings.
     *
     * @param company_id company to recommended
     * @return the ResponseEntity with status 200 (OK) and the list of weightings in body
     */
    @GetMapping("/recommendation-next-category/{company_id}")
    @Timed
    public ResponseEntity<List<AuditProcessRecommendationDTO>> getRecommendationNextCategory(@PathVariable("company_id") Long company_id) {
        log.debug("REST request to get a page of Weightings");

        return new ResponseEntity<>(
            recommendationNextCategoryService.getRecommendationNextCategory(company_id)
            , null, HttpStatus.OK);
    }

}
