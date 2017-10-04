package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.service.CategoryAttributeRecommendationService;
import com.labausegtic.aresvi.web.rest.util.HeaderUtil;
import com.labausegtic.aresvi.web.rest.util.PaginationUtil;
import com.labausegtic.aresvi.service.dto.CategoryAttributeRecommendationDTO;
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
 * REST controller for managing CategoryAttributeRecommendation.
 */
@RestController
@RequestMapping("/api")
public class CategoryAttributeRecommendationResource {

    private final Logger log = LoggerFactory.getLogger(CategoryAttributeRecommendationResource.class);

    private static final String ENTITY_NAME = "categoryAttributeRecommendation";

    private final CategoryAttributeRecommendationService categoryAttributeRecommendationService;

    public CategoryAttributeRecommendationResource(CategoryAttributeRecommendationService categoryAttributeRecommendationService) {
        this.categoryAttributeRecommendationService = categoryAttributeRecommendationService;
    }

    /**
     * POST  /category-attribute-recommendations : Create a new categoryAttributeRecommendation.
     *
     * @param categoryAttributeRecommendationDTO the categoryAttributeRecommendationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new categoryAttributeRecommendationDTO, or with status 400 (Bad Request) if the categoryAttributeRecommendation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/category-attribute-recommendations")
    @Timed
    public ResponseEntity<CategoryAttributeRecommendationDTO> createCategoryAttributeRecommendation(@Valid @RequestBody CategoryAttributeRecommendationDTO categoryAttributeRecommendationDTO) throws URISyntaxException {
        log.debug("REST request to save CategoryAttributeRecommendation : {}", categoryAttributeRecommendationDTO);
        if (categoryAttributeRecommendationDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new categoryAttributeRecommendation cannot already have an ID")).body(null);
        }
        CategoryAttributeRecommendationDTO result = categoryAttributeRecommendationService.save(categoryAttributeRecommendationDTO);
        return ResponseEntity.created(new URI("/api/category-attribute-recommendations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /category-attribute-recommendations : Updates an existing categoryAttributeRecommendation.
     *
     * @param categoryAttributeRecommendationDTO the categoryAttributeRecommendationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated categoryAttributeRecommendationDTO,
     * or with status 400 (Bad Request) if the categoryAttributeRecommendationDTO is not valid,
     * or with status 500 (Internal Server Error) if the categoryAttributeRecommendationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/category-attribute-recommendations")
    @Timed
    public ResponseEntity<CategoryAttributeRecommendationDTO> updateCategoryAttributeRecommendation(@Valid @RequestBody CategoryAttributeRecommendationDTO categoryAttributeRecommendationDTO) throws URISyntaxException {
        log.debug("REST request to update CategoryAttributeRecommendation : {}", categoryAttributeRecommendationDTO);
        if (categoryAttributeRecommendationDTO.getId() == null) {
            return createCategoryAttributeRecommendation(categoryAttributeRecommendationDTO);
        }
        CategoryAttributeRecommendationDTO result = categoryAttributeRecommendationService.save(categoryAttributeRecommendationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, categoryAttributeRecommendationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /category-attribute-recommendations : get all the categoryAttributeRecommendations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of categoryAttributeRecommendations in body
     */
    @GetMapping("/category-attribute-recommendations")
    @Timed
    public ResponseEntity<List<CategoryAttributeRecommendationDTO>> getAllCategoryAttributeRecommendations(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of CategoryAttributeRecommendations");
        Page<CategoryAttributeRecommendationDTO> page = categoryAttributeRecommendationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/category-attribute-recommendations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /category-attribute-recommendations/:id : get the "id" categoryAttributeRecommendation.
     *
     * @param id the id of the categoryAttributeRecommendationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the categoryAttributeRecommendationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/category-attribute-recommendations/{id}")
    @Timed
    public ResponseEntity<CategoryAttributeRecommendationDTO> getCategoryAttributeRecommendation(@PathVariable Long id) {
        log.debug("REST request to get CategoryAttributeRecommendation : {}", id);
        CategoryAttributeRecommendationDTO categoryAttributeRecommendationDTO = categoryAttributeRecommendationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(categoryAttributeRecommendationDTO));
    }

    /**
     * DELETE  /category-attribute-recommendations/:id : delete the "id" categoryAttributeRecommendation.
     *
     * @param id the id of the categoryAttributeRecommendationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/category-attribute-recommendations/{id}")
    @Timed
    public ResponseEntity<Void> deleteCategoryAttributeRecommendation(@PathVariable Long id) {
        log.debug("REST request to delete CategoryAttributeRecommendation : {}", id);
        categoryAttributeRecommendationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
