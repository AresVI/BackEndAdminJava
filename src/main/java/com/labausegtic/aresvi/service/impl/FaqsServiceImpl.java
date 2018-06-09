package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.FaqsService;
import com.labausegtic.aresvi.domain.Faqs;
import com.labausegtic.aresvi.repository.FaqsRepository;
import com.labausegtic.aresvi.service.dto.FaqsDTO;
import com.labausegtic.aresvi.service.mapper.FaqsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Faqs.
 */
@Service
@Transactional
public class FaqsServiceImpl implements FaqsService{

    private final Logger log = LoggerFactory.getLogger(FaqsServiceImpl.class);

    private final FaqsRepository faqsRepository;

    private final FaqsMapper faqsMapper;

    public FaqsServiceImpl(FaqsRepository faqsRepository, FaqsMapper faqsMapper) {
        this.faqsRepository = faqsRepository;
        this.faqsMapper = faqsMapper;
    }

    /**
     * Save a faqs.
     *
     * @param faqsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FaqsDTO save(FaqsDTO faqsDTO) {
        log.debug("Request to save Faqs : {}", faqsDTO);
        Faqs faqs = faqsMapper.toEntity(faqsDTO);
        faqs = faqsRepository.save(faqs);
        return faqsMapper.toDto(faqs);
    }

    /**
     *  Get all the faqs.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<FaqsDTO> findAll() {
        log.debug("Request to get all Faqs");
        return faqsRepository.findAll().stream()
            .map(faqsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one faqs by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public FaqsDTO findOne(Long id) {
        log.debug("Request to get Faqs : {}", id);
        Faqs faqs = faqsRepository.findOne(id);
        return faqsMapper.toDto(faqs);
    }

    /**
     *  Delete the  faqs by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Faqs : {}", id);
        faqsRepository.delete(id);
    }
}
