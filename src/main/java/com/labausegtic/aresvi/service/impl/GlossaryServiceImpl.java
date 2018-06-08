package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.GlossaryService;
import com.labausegtic.aresvi.domain.Glossary;
import com.labausegtic.aresvi.repository.GlossaryRepository;
import com.labausegtic.aresvi.service.dto.GlossaryDTO;
import com.labausegtic.aresvi.service.mapper.GlossaryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Glossary.
 */
@Service
@Transactional
public class GlossaryServiceImpl implements GlossaryService{

    private final Logger log = LoggerFactory.getLogger(GlossaryServiceImpl.class);

    private final GlossaryRepository glossaryRepository;

    private final GlossaryMapper glossaryMapper;

    public GlossaryServiceImpl(GlossaryRepository glossaryRepository, GlossaryMapper glossaryMapper) {
        this.glossaryRepository = glossaryRepository;
        this.glossaryMapper = glossaryMapper;
    }

    /**
     * Save a glossary.
     *
     * @param glossaryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GlossaryDTO save(GlossaryDTO glossaryDTO) {
        log.debug("Request to save Glossary : {}", glossaryDTO);
        Glossary glossary = glossaryMapper.toEntity(glossaryDTO);
        glossary = glossaryRepository.save(glossary);
        return glossaryMapper.toDto(glossary);
    }

    /**
     *  Get all the glossaries.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GlossaryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Glossaries");
        return glossaryRepository.findAll(pageable)
            .map(glossaryMapper::toDto);
    }

    /**
     *  Get one glossary by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public GlossaryDTO findOne(Long id) {
        log.debug("Request to get Glossary : {}", id);
        Glossary glossary = glossaryRepository.findOne(id);
        return glossaryMapper.toDto(glossary);
    }

    /**
     *  Delete the  glossary by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Glossary : {}", id);
        glossaryRepository.delete(id);
    }
}
