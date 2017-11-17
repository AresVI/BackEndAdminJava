package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.domain.User;
import com.labausegtic.aresvi.security.SecurityUtils;
import com.labausegtic.aresvi.service.CancelationTraceabilityAuditService;
import com.labausegtic.aresvi.domain.CancelationTraceabilityAudit;
import com.labausegtic.aresvi.repository.CancelationTraceabilityAuditRepository;
import com.labausegtic.aresvi.service.UserService;
import com.labausegtic.aresvi.service.dto.CancelationTraceabilityAuditDTO;
import com.labausegtic.aresvi.service.mapper.CancelationTraceabilityAuditMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * Service Implementation for managing CancelationTraceabilityAudit.
 */
@Service
@Transactional
public class CancelationTraceabilityAuditServiceImpl implements CancelationTraceabilityAuditService{

    private final Logger log = LoggerFactory.getLogger(CancelationTraceabilityAuditServiceImpl.class);

    private final CancelationTraceabilityAuditRepository cancelationTraceabilityAuditRepository;

    private final CancelationTraceabilityAuditMapper cancelationTraceabilityAuditMapper;

    private final UserService userService;

    public CancelationTraceabilityAuditServiceImpl(CancelationTraceabilityAuditRepository cancelationTraceabilityAuditRepository,
                                                   CancelationTraceabilityAuditMapper cancelationTraceabilityAuditMapper,
                                                   UserService userService) {
        this.cancelationTraceabilityAuditRepository = cancelationTraceabilityAuditRepository;
        this.cancelationTraceabilityAuditMapper = cancelationTraceabilityAuditMapper;
        this.userService = userService;
    }

    /**
     * Save a cancelationTraceabilityAudit.
     *
     * @param cancelationTraceabilityAuditDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CancelationTraceabilityAuditDTO save(CancelationTraceabilityAuditDTO cancelationTraceabilityAuditDTO) {
        log.debug("Request to save CancelationTraceabilityAudit : {}", cancelationTraceabilityAuditDTO);
        CancelationTraceabilityAudit cancelationTraceabilityAudit = cancelationTraceabilityAuditMapper.toEntity(cancelationTraceabilityAuditDTO);

        Optional<User> user = userService.getUserWithAuthoritiesByLogin(SecurityUtils.getCurrentUserLogin());

        cancelationTraceabilityAudit.setUser(user.get());

        cancelationTraceabilityAudit = cancelationTraceabilityAuditRepository.save(cancelationTraceabilityAudit);
        return cancelationTraceabilityAuditMapper.toDto(cancelationTraceabilityAudit);
    }

    /**
     *  Get all the cancelationTraceabilityAudits.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CancelationTraceabilityAuditDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CancelationTraceabilityAudits");
        return cancelationTraceabilityAuditRepository.findAll(pageable)
            .map(cancelationTraceabilityAuditMapper::toDto);
    }

    /**
     *  Get one cancelationTraceabilityAudit by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CancelationTraceabilityAuditDTO findOne(Long id) {
        log.debug("Request to get CancelationTraceabilityAudit : {}", id);
        CancelationTraceabilityAudit cancelationTraceabilityAudit = cancelationTraceabilityAuditRepository.findOne(id);
        return cancelationTraceabilityAuditMapper.toDto(cancelationTraceabilityAudit);
    }

    /**
     *  Delete the  cancelationTraceabilityAudit by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CancelationTraceabilityAudit : {}", id);
        cancelationTraceabilityAuditRepository.delete(id);
    }
}
