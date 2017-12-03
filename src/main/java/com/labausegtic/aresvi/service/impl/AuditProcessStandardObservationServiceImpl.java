package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.AuditProcessStandardObservationService;
import com.labausegtic.aresvi.domain.AuditProcessStandardObservation;
import com.labausegtic.aresvi.repository.AuditProcessStandardObservationRepository;
import com.labausegtic.aresvi.service.dto.AuditProcessStandardObservationDTO;
import com.labausegtic.aresvi.service.mapper.AuditProcessStandardObservationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing AuditProcessStandardObservation.
 */
@Service
@Transactional
public class AuditProcessStandardObservationServiceImpl implements AuditProcessStandardObservationService{

    private final Logger log = LoggerFactory.getLogger(AuditProcessStandardObservationServiceImpl.class);

    private final AuditProcessStandardObservationRepository auditProcessStandardObservationRepository;

    private final AuditProcessStandardObservationMapper auditProcessStandardObservationMapper;

    public AuditProcessStandardObservationServiceImpl(AuditProcessStandardObservationRepository auditProcessStandardObservationRepository, AuditProcessStandardObservationMapper auditProcessStandardObservationMapper) {
        this.auditProcessStandardObservationRepository = auditProcessStandardObservationRepository;
        this.auditProcessStandardObservationMapper = auditProcessStandardObservationMapper;
    }

    /**
     * Save a auditProcessStandardObservation.
     *
     * @param auditProcessStandardObservationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AuditProcessStandardObservationDTO save(AuditProcessStandardObservationDTO auditProcessStandardObservationDTO) {
        log.debug("Request to save AuditProcessStandardObservation : {}", auditProcessStandardObservationDTO);
        AuditProcessStandardObservation auditProcessStandardObservation = auditProcessStandardObservationMapper.toEntity(auditProcessStandardObservationDTO);
        auditProcessStandardObservation = auditProcessStandardObservationRepository.save(auditProcessStandardObservation);
        return auditProcessStandardObservationMapper.toDto(auditProcessStandardObservation);
    }

    /**
     *  Get all the auditProcessStandardObservations.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AuditProcessStandardObservationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AuditProcessStandardObservations");
        return auditProcessStandardObservationRepository.findAll(pageable)
            .map(auditProcessStandardObservationMapper::toDto);
    }

    /**
     *  Get one auditProcessStandardObservation by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AuditProcessStandardObservationDTO findOne(Long id) {
        log.debug("Request to get AuditProcessStandardObservation : {}", id);
        AuditProcessStandardObservation auditProcessStandardObservation = auditProcessStandardObservationRepository.findOne(id);
        return auditProcessStandardObservationMapper.toDto(auditProcessStandardObservation);
    }

    /**
     *  Delete the  auditProcessStandardObservation by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AuditProcessStandardObservation : {}", id);
        auditProcessStandardObservationRepository.delete(id);
    }
}
