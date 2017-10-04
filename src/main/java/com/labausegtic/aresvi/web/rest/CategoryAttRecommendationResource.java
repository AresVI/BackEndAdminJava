package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.service.CategoryAttRecommendationService;
import com.labausegtic.aresvi.web.rest.util.HeaderUtil;
import com.labausegtic.aresvi.web.rest.util.PaginationUtil;
import com.labausegtic.aresvi.service.dto.CategoryAttRecommendationDTO;
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
 * REST controller for managing CategoryAttRecommendation.
 */
@RestController
@RequestMapping("/api")
public class CategoryAttRecommendationResource {

    private final Logger log = LoggerFactory.getLogger(CategoryAttRecommendationResource.class);

    private static final String ENTITY_NAME = "categoryAttRecommendation";

    private final CategoryAttRecommendationService categoryAttRecommendationService;

    public CategoryAttRecommendationResource(CategoryAttRecommendationService categoryAttRecommendationService) {
        this.categoryAttRecommendationService = categoryAttRecommendationService;
    }

    /**
     * POST  /category-att-recommendations : Create a new categoryAttRecommendation.
     *
     * @param categoryAttRecommendationDTO the categoryAttRecommendationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new categoryAttRecommendationDTO, or with status 400 (Bad Request) if the categoryAttRecommendation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/category-att-recommendations")
    @Timed
    public ResponseEntity<CategoryAttRecommendationDTO> createCategoryAttRecommendation(@Valid @RequestBody CategoryAttRecommendationDTO categoryAttRecommendationDTO) throws URISyntaxException {
        log.debug("REST request to save CategoryAttRecommendation : {}", categoryAttRecommendationDTO);
        if (categoryAttRecommendationDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new categoryAttRecommendation cannot already have an ID")).body(null);
        }
        CategoryAttRecommendationDTO result = categoryAttRecommendationService.save(categoryAttRecommendationDTO);
        return ResponseEntity.created(new URI("/api/category-att-recommendations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /category-att-recommendations : Updates an existing categoryAttRecommendation.
     *
     * @param categoryAttRecommendationDTO the categoryAttRecommendationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated categoryAttRecommendationDTO,
     * or with status 400 (Bad Request) if the categoryAttRecommendationDTO is not valid,
     * or with status 500 (Internal Server Error) if the categoryAttRecommendationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/category-att-recommendations")
    @Timed
    public ResponseEntity<CategoryAttRecommendationDTO> updateCategoryAttRecommendation(@Valid @RequestBody CategoryAttRecommendationDTO categoryAttRecommendationDTO) throws URISyntaxException {
        log.debug("REST request to update CategoryAttRecommendation : {}", categoryAttRecommendationDTO);
        if (categoryAttRecommendationDTO.getId() == null) {
            return createCategoryAttRecommendation(categoryAttRecommendationDTO);
        }
        CategoryAttRecommendationDTO result = categoryAttRecommendationService.save(categoryAttRecommendationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, categoryAttRecommendationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /category-att-recommendations : get all the categoryAttRecommendations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of categoryAttRecommendations in body
     */
    @GetMapping("/category-att-recommendations")
    @Timed
    public ResponseEntity<List<CategoryAttRecommendationDTO>> getAllCategoryAttRecommendations(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of CategoryAttRecommendations");
        Page<CategoryAttRecommendationDTO> page = categoryAttRecommendationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/category-att-recommendations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /category-att-recommendations/:id : get the "id" categoryAttRecommendation.
     *
     * @param id the id of the categoryAttRecommendationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the categoryAttRecommendationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/category-att-recommendations/{id}")
    @Timed
    public ResponseEntity<CategoryAttRecommendationDTO> getCategoryAttRecommendation(@PathVariable Long id) {
        log.debug("REST request to get CategoryAttRecommendation : {}", id);
        CategoryAttRecommendationDTO categoryAttRecommendationDTO = categoryAttRecommendationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(categoryAttRecommendationDTO));
    }

    /**
     * DELETE  /category-att-recommendations/:id : delete the "id" categoryAttRecommendation.
     *
     * @param id the id of the categoryAttRecommendationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/category-att-recommendations/{id}")
    @Timed
    public ResponseEntity<Void> deleteCategoryAttRecommendation(@PathVariable Long id) {
        log.debug("REST request to delete CategoryAttRecommendation : {}", id);
        categoryAttRecommendationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
