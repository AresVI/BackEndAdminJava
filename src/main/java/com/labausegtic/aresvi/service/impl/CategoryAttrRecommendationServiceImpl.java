package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.domain.CategoryAttrRecommendation;
import com.labausegtic.aresvi.service.AttributeRecommendationService;
import com.labausegtic.aresvi.service.CategoryAttrRecommendationService;
import com.labausegtic.aresvi.repository.CategoryAttrRecommendationRepository;
import com.labausegtic.aresvi.service.dto.CategoryAttrRecommendationDTO;
import com.labausegtic.aresvi.service.mapper.CategoryAttrRecommendationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


/**
 * Service Implementation for managing CategoryAttrRecommendation.
 */
@Service
@Transactional
public class CategoryAttrRecommendationServiceImpl implements CategoryAttrRecommendationService{

    private final Logger log = LoggerFactory.getLogger(CategoryAttrRecommendationServiceImpl.class);

    private final CategoryAttrRecommendationRepository categoryAttrRecommendationRepository;

    private final CategoryAttrRecommendationMapper categoryAttrRecommendationMapper;

    private final AttributeRecommendationService attributeRecommendationService;

    public CategoryAttrRecommendationServiceImpl(CategoryAttrRecommendationRepository categoryAttrRecommendationRepository,
                                                 CategoryAttrRecommendationMapper categoryAttrRecommendationMapper,
                                                 AttributeRecommendationService attributeRecommendationService) {
        this.categoryAttrRecommendationRepository = categoryAttrRecommendationRepository;
        this.categoryAttrRecommendationMapper = categoryAttrRecommendationMapper;
        this.attributeRecommendationService = attributeRecommendationService;
    }

    /**
     * Save a CategoryAttrRecommendation.
     *
     * @param categoryAttrRecommendationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CategoryAttrRecommendationDTO save(CategoryAttrRecommendationDTO categoryAttrRecommendationDTO) {
        log.debug("Request to save CategoryAttrRecommendation : {}", categoryAttrRecommendationDTO);
        CategoryAttrRecommendation categoryAttrRecommendation = categoryAttrRecommendationMapper.toEntity(categoryAttrRecommendationDTO);
        categoryAttrRecommendation = categoryAttrRecommendationRepository.save(categoryAttrRecommendation);
        return categoryAttrRecommendationMapper.toDto(categoryAttrRecommendation);
    }

    /**
     *  Get all the CategoryAttrRecommendations.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CategoryAttrRecommendationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CategoryAttrRecommendations");
        return categoryAttrRecommendationRepository.findAll(pageable)
            .map(categoryAttrRecommendationMapper::toDto);
    }

    @Override
    public Set<CategoryAttrRecommendationDTO> findAllByAuditTaskRecom_Id(Long auditTaskRecom_id) {

        Set<CategoryAttrRecommendationDTO> result = new HashSet<>();

        Set<CategoryAttrRecommendation> categoryAttrRecommendations;

        categoryAttrRecommendations = categoryAttrRecommendationRepository.findAllByAuditTaskRecom_Id(auditTaskRecom_id);

        for (CategoryAttrRecommendation car : categoryAttrRecommendations) {

            CategoryAttrRecommendationDTO categoryAttrRecommendationDTO = categoryAttrRecommendationMapper.toDto(car);

            categoryAttrRecommendationDTO.setAttributeRecommendationDTOSet(
                attributeRecommendationService.findAllByCategoryAttrRecom_Id(car.getId())
            );

            result.add(categoryAttrRecommendationDTO);

        }

        return result;

    }

    /**
     *  Get one CategoryAttrRecommendation by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CategoryAttrRecommendationDTO findOne(Long id) {
        log.debug("Request to get CategoryAttrRecommendation : {}", id);
        CategoryAttrRecommendation categoryAttrRecommendation = categoryAttrRecommendationRepository.findOne(id);
        return categoryAttrRecommendationMapper.toDto(categoryAttrRecommendation);
    }

    /**
     *  Delete the  CategoryAttrRecommendation by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CategoryAttrRecommendation : {}", id);
        categoryAttrRecommendationRepository.delete(id);
    }
}
