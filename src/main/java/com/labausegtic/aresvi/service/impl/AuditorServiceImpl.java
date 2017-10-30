package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.domain.Auditor;
import com.labausegtic.aresvi.domain.Authority;
import com.labausegtic.aresvi.repository.AuditorRepository;
import com.labausegtic.aresvi.repository.AuthorityRepository;
import com.labausegtic.aresvi.security.AuthoritiesConstants;
import com.labausegtic.aresvi.service.AuditorService;
import com.labausegtic.aresvi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


/**
 * Service Implementation for managing Auditor.
 */
@Service
@Transactional
public class AuditorServiceImpl implements AuditorService{

    private final Logger log = LoggerFactory.getLogger(AuditorServiceImpl.class);

    private final AuditorRepository auditorRepository;

    private final AuthorityRepository authorityRepository;

    private final UserService userService;

    public AuditorServiceImpl(AuditorRepository auditorRepository, AuthorityRepository authorityRepository,
                              UserService userService) {
        this.auditorRepository = auditorRepository;
        this.authorityRepository = authorityRepository;
        this.userService = userService;
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

        auditor.setInternal(auditor.getCompanies().size() > 0);

        Set<Authority> authorities = auditor.getUser().getAuthorities();

        Authority authority;

        if (auditor.getCompanies().size() > 0) {
            authority = authorityRepository.findOne(AuthoritiesConstants.AUDITOR_INTERNAL);
        } else {
            authority = authorityRepository.findOne(AuthoritiesConstants.AUDITOR_EXTERNAL);
        }

        authorities.add(authority);

        //userService.getUserWithAuthoritiesByLogin()

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
