package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.service.CategoryAttributeService;
import com.labausegtic.aresvi.web.rest.util.HeaderUtil;
import com.labausegtic.aresvi.web.rest.util.PaginationUtil;
import com.labausegtic.aresvi.service.dto.CategoryAttributeDTO;
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
 * REST controller for managing CategoryAttribute.
 */
@RestController
@RequestMapping("/api")
public class CategoryAttributeResource {

    private final Logger log = LoggerFactory.getLogger(CategoryAttributeResource.class);

    private static final String ENTITY_NAME = "categoryAttribute";

    private final CategoryAttributeService categoryAttributeService;

    public CategoryAttributeResource(CategoryAttributeService categoryAttributeService) {
        this.categoryAttributeService = categoryAttributeService;
    }

    /**
     * POST  /category-attributes : Create a new categoryAttribute.
     *
     * @param categoryAttributeDTO the categoryAttributeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new categoryAttributeDTO, or with status 400 (Bad Request) if the categoryAttribute has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/category-attributes")
    @Timed
    public ResponseEntity<CategoryAttributeDTO> createCategoryAttribute(@Valid @RequestBody CategoryAttributeDTO categoryAttributeDTO) throws URISyntaxException {
        log.debug("REST request to save CategoryAttribute : {}", categoryAttributeDTO);
        if (categoryAttributeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new categoryAttribute cannot already have an ID")).body(null);
        }
        CategoryAttributeDTO result = categoryAttributeService.save(categoryAttributeDTO);
        return ResponseEntity.created(new URI("/api/category-attributes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /category-attributes : Updates an existing categoryAttribute.
     *
     * @param categoryAttributeDTO the categoryAttributeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated categoryAttributeDTO,
     * or with status 400 (Bad Request) if the categoryAttributeDTO is not valid,
     * or with status 500 (Internal Server Error) if the categoryAttributeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/category-attributes")
    @Timed
    public ResponseEntity<CategoryAttributeDTO> updateCategoryAttribute(@Valid @RequestBody CategoryAttributeDTO categoryAttributeDTO) throws URISyntaxException {
        log.debug("REST request to update CategoryAttribute : {}", categoryAttributeDTO);
        if (categoryAttributeDTO.getId() == null) {
            return createCategoryAttribute(categoryAttributeDTO);
        }
        CategoryAttributeDTO result = categoryAttributeService.save(categoryAttributeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, categoryAttributeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /category-attributes : get all the categoryAttributes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of categoryAttributes in body
     */
    @GetMapping("/category-attributes")
    @Timed
    public ResponseEntity<List<CategoryAttributeDTO>> getAllCategoryAttributes(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of CategoryAttributes");
        Page<CategoryAttributeDTO> page = categoryAttributeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/category-attributes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /category-attributes/:id : get the "id" categoryAttribute.
     *
     * @param id the id of the categoryAttributeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the categoryAttributeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/category-attributes/{id}")
    @Timed
    public ResponseEntity<CategoryAttributeDTO> getCategoryAttribute(@PathVariable Long id) {
        log.debug("REST request to get CategoryAttribute : {}", id);
        CategoryAttributeDTO categoryAttributeDTO = categoryAttributeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(categoryAttributeDTO));
    }

    /**
     * DELETE  /category-attributes/:id : delete the "id" categoryAttribute.
     *
     * @param id the id of the categoryAttributeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/category-attributes/{id}")
    @Timed
    public ResponseEntity<Void> deleteCategoryAttribute(@PathVariable Long id) {
        log.debug("REST request to delete CategoryAttribute : {}", id);
        categoryAttributeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
