package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.AuditTaskStandardObservationService;
import com.labausegtic.aresvi.domain.AuditTaskStandardObservation;
import com.labausegtic.aresvi.repository.AuditTaskStandardObservationRepository;
import com.labausegtic.aresvi.service.dto.AuditTaskStandardObservationDTO;
import com.labausegtic.aresvi.service.mapper.AuditTaskStandardObservationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing AuditTaskStandardObservation.
 */
@Service
@Transactional
public class AuditTaskStandardObservationServiceImpl implements AuditTaskStandardObservationService{

    private final Logger log = LoggerFactory.getLogger(AuditTaskStandardObservationServiceImpl.class);

    private final AuditTaskStandardObservationRepository auditTaskStandardObservationRepository;

    private final AuditTaskStandardObservationMapper auditTaskStandardObservationMapper;

    public AuditTaskStandardObservationServiceImpl(AuditTaskStandardObservationRepository auditTaskStandardObservationRepository, AuditTaskStandardObservationMapper auditTaskStandardObservationMapper) {
        this.auditTaskStandardObservationRepository = auditTaskStandardObservationRepository;
        this.auditTaskStandardObservationMapper = auditTaskStandardObservationMapper;
    }

    /**
     * Save a auditTaskStandardObservation.
     *
     * @param auditTaskStandardObservationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AuditTaskStandardObservationDTO save(AuditTaskStandardObservationDTO auditTaskStandardObservationDTO) {
        log.debug("Request to save AuditTaskStandardObservation : {}", auditTaskStandardObservationDTO);
        AuditTaskStandardObservation auditTaskStandardObservation = auditTaskStandardObservationMapper.toEntity(auditTaskStandardObservationDTO);
        auditTaskStandardObservation = auditTaskStandardObservationRepository.save(auditTaskStandardObservation);
        return auditTaskStandardObservationMapper.toDto(auditTaskStandardObservation);
    }

    /**
     *  Get all the auditTaskStandardObservations.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AuditTaskStandardObservationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AuditTaskStandardObservations");
        return auditTaskStandardObservationRepository.findAll(pageable)
            .map(auditTaskStandardObservationMapper::toDto);
    }

    /**
     *  Get one auditTaskStandardObservation by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AuditTaskStandardObservationDTO findOne(Long id) {
        log.debug("Request to get AuditTaskStandardObservation : {}", id);
        AuditTaskStandardObservation auditTaskStandardObservation = auditTaskStandardObservationRepository.findOne(id);
        return auditTaskStandardObservationMapper.toDto(auditTaskStandardObservation);
    }

    /**
     *  Delete the  auditTaskStandardObservation by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AuditTaskStandardObservation : {}", id);
        auditTaskStandardObservationRepository.delete(id);
    }
}
