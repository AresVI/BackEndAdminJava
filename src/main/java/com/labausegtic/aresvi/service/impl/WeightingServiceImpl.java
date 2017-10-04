package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.WeightingService;
import com.labausegtic.aresvi.domain.Weighting;
import com.labausegtic.aresvi.repository.WeightingRepository;
import com.labausegtic.aresvi.service.dto.WeightingDTO;
import com.labausegtic.aresvi.service.mapper.WeightingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Weighting.
 */
@Service
@Transactional
public class WeightingServiceImpl implements WeightingService{

    private final Logger log = LoggerFactory.getLogger(WeightingServiceImpl.class);

    private final WeightingRepository weightingRepository;

    private final WeightingMapper weightingMapper;

    public WeightingServiceImpl(WeightingRepository weightingRepository, WeightingMapper weightingMapper) {
        this.weightingRepository = weightingRepository;
        this.weightingMapper = weightingMapper;
    }

    /**
     * Save a weighting.
     *
     * @param weightingDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public WeightingDTO save(WeightingDTO weightingDTO) {
        log.debug("Request to save Weighting : {}", weightingDTO);
        Weighting weighting = weightingMapper.toEntity(weightingDTO);
        weighting = weightingRepository.save(weighting);
        return weightingMapper.toDto(weighting);
    }

    /**
     *  Get all the weightings.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WeightingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Weightings");
        return weightingRepository.findAll(pageable)
            .map(weightingMapper::toDto);
    }

    /**
     *  Get one weighting by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public WeightingDTO findOne(Long id) {
        log.debug("Request to get Weighting : {}", id);
        Weighting weighting = weightingRepository.findOne(id);
        return weightingMapper.toDto(weighting);
    }

    /**
     *  Delete the  weighting by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Weighting : {}", id);
        weightingRepository.delete(id);
    }
}
