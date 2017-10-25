package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.domain.Auditor;
import com.labausegtic.aresvi.repository.AuditorRepository;
import com.labausegtic.aresvi.service.AuditorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Auditor.
 */
@Service
@Transactional
public class AuditorServiceImpl implements AuditorService{

    private final Logger log = LoggerFactory.getLogger(AuditorServiceImpl.class);

    private final AuditorRepository auditorRepository;

    public AuditorServiceImpl(AuditorRepository auditorRepository) {
        this.auditorRepository = auditorRepository;
    }

    /**
     * Save a auditor.
     *
     * @param auditor the entity to save
     * @return the persisted entity
     */
    @Override
    public Auditor save(Auditor auditor) throws Exception {
        log.debug("Request to save Auditor : {}", auditor);

        if (auditor.isInternal() && auditor.getCompanies().size() < 1) {

          //  throw new Exception();

        }

        return auditorRepository.save(auditor);
    }

    /**
     *  Get all the auditors.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Auditor> findAll(Pageable pageable) {
        log.debug("Request to get all Auditors");
        return auditorRepository.findAll(pageable);
    }

    /**
     *  Get one auditor by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Auditor findOne(Long id) {
        log.debug("Request to get Auditor : {}", id);
        return auditorRepository.findOneWithEagerRelationships(id);
    }

    /**
     *  Delete the  auditor by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Auditor : {}", id);
        auditorRepository.delete(id);
    }
}
