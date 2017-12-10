package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.AttributeService;
import com.labausegtic.aresvi.domain.Attribute;
import com.labausegtic.aresvi.repository.AttributeRepository;
import com.labausegtic.aresvi.service.WeightingService;
import com.labausegtic.aresvi.service.dto.AttributeCompleteDTO;
import com.labausegtic.aresvi.service.dto.AttributeDTO;
import com.labausegtic.aresvi.service.dto.WeightingDTO;
import com.labausegtic.aresvi.service.mapper.AttributeCompleteMapper;
import com.labausegtic.aresvi.service.mapper.AttributeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Service Implementation for managing Attribute.
 */
@Service
@Transactional
public class AttributeServiceImpl implements AttributeService{

    private final Logger log = LoggerFactory.getLogger(AttributeServiceImpl.class);

    private final AttributeRepository attributeRepository;

    private final AttributeMapper attributeMapper;

    private final AttributeCompleteMapper attributeCompleteMapper;

    private final WeightingService weightingService;

    public AttributeServiceImpl(AttributeRepository attributeRepository, AttributeMapper attributeMapper,
                                AttributeCompleteMapper attributeCompleteMapper, WeightingService weightingService) {
        this.attributeRepository = attributeRepository;
        this.attributeMapper = attributeMapper;
        this.attributeCompleteMapper = attributeCompleteMapper;
        this.weightingService = weightingService;
    }

    /**
     * Save a attribute.
     *
     * @param attributeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AttributeDTO save(AttributeDTO attributeDTO) {
        log.debug("Request to save Attribute : {}", attributeDTO);
        Attribute attribute = attributeMapper.toEntity(attributeDTO);
        attribute = attributeRepository.save(attribute);
        return attributeMapper.toDto(attribute);
    }

    /**
     *  Get all the attributes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AttributeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Attributes");
        return attributeRepository.findAll(pageable)
            .map(attributeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<AttributeCompleteDTO> findAllByCategoryAttribute_Id(Long categoryAttributeId){

        Set<AttributeCompleteDTO> result = new HashSet<>();

        attributeRepository.findAllByCategoryAttribute_Id(categoryAttributeId).forEach(
            item -> result.add(attributeCompleteMapper.toDto(item))
        );

        return result;
    }

    /**
     *  Get one attribute by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AttributeDTO findOne(Long id) {
        log.debug("Request to get Attribute : {}", id);
        Attribute attribute = attributeRepository.findOne(id);
        return attributeMapper.toDto(attribute);
    }

    /**
     *  Delete the  attribute by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Attribute : {}", id);
        attributeRepository.delete(id);
    }

    @Override
    public List<Attribute> getByWeighting(Long level, boolean required) {

        if(required){
            WeightingDTO weightingDTO = weightingService.findOneByValue(level.intValue());
            return attributeRepository.findTopTenByWeightingId(weightingDTO.getId());
        } else {
            return attributeRepository.findTopTenByRequiredFalse();
        }

    }
}
