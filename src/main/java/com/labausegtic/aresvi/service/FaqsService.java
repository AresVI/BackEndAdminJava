package com.labausegtic.aresvi.service;

import com.labausegtic.aresvi.service.dto.FaqsDTO;
import java.util.List;

/**
 * Service Interface for managing Faqs.
 */
public interface FaqsService {

    /**
     * Save a faqs.
     *
     * @param faqsDTO the entity to save
     * @return the persisted entity
     */
    FaqsDTO save(FaqsDTO faqsDTO);

    /**
     *  Get all the faqs.
     *
     *  @return the list of entities
     */
    List<FaqsDTO> findAll();

    /**
     *  Get the "id" faqs.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    FaqsDTO findOne(Long id);

    /**
     *  Delete the "id" faqs.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
