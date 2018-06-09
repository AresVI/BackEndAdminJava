package com.labausegtic.aresvi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.labausegtic.aresvi.service.FaqsService;
import com.labausegtic.aresvi.web.rest.util.HeaderUtil;
import com.labausegtic.aresvi.service.dto.FaqsDTO;
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
 * REST controller for managing Faqs.
 */
@RestController
@RequestMapping("/api")
public class FaqsResource {

    private final Logger log = LoggerFactory.getLogger(FaqsResource.class);

    private static final String ENTITY_NAME = "faqs";

    private final FaqsService faqsService;

    public FaqsResource(FaqsService faqsService) {
        this.faqsService = faqsService;
    }

    /**
     * POST  /faqs : Create a new faqs.
     *
     * @param faqsDTO the faqsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new faqsDTO, or with status 400 (Bad Request) if the faqs has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/faqs")
    @Timed
    public ResponseEntity<FaqsDTO> createFaqs(@Valid @RequestBody FaqsDTO faqsDTO) throws URISyntaxException {
        log.debug("REST request to save Faqs : {}", faqsDTO);
        if (faqsDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new faqs cannot already have an ID")).body(null);
        }
        FaqsDTO result = faqsService.save(faqsDTO);
        return ResponseEntity.created(new URI("/api/faqs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /faqs : Updates an existing faqs.
     *
     * @param faqsDTO the faqsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated faqsDTO,
     * or with status 400 (Bad Request) if the faqsDTO is not valid,
     * or with status 500 (Internal Server Error) if the faqsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/faqs")
    @Timed
    public ResponseEntity<FaqsDTO> updateFaqs(@Valid @RequestBody FaqsDTO faqsDTO) throws URISyntaxException {
        log.debug("REST request to update Faqs : {}", faqsDTO);
        if (faqsDTO.getId() == null) {
            return createFaqs(faqsDTO);
        }
        FaqsDTO result = faqsService.save(faqsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, faqsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /faqs : get all the faqs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of faqs in body
     */
    @GetMapping("/faqs")
    @Timed
    public List<FaqsDTO> getAllFaqs() {
        log.debug("REST request to get all Faqs");
        return faqsService.findAll();
        }

    /**
     * GET  /faqs/:id : get the "id" faqs.
     *
     * @param id the id of the faqsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the faqsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/faqs/{id}")
    @Timed
    public ResponseEntity<FaqsDTO> getFaqs(@PathVariable Long id) {
        log.debug("REST request to get Faqs : {}", id);
        FaqsDTO faqsDTO = faqsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(faqsDTO));
    }

    /**
     * DELETE  /faqs/:id : delete the "id" faqs.
     *
     * @param id the id of the faqsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/faqs/{id}")
    @Timed
    public ResponseEntity<Void> deleteFaqs(@PathVariable Long id) {
        log.debug("REST request to delete Faqs : {}", id);
        faqsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
