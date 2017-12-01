package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.CompanyAddressService;
import com.labausegtic.aresvi.domain.CompanyAddress;
import com.labausegtic.aresvi.repository.CompanyAddressRepository;
import com.labausegtic.aresvi.service.dto.CompanyAddressDTO;
import com.labausegtic.aresvi.service.mapper.CompanyAddressMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing CompanyAddress.
 */
@Service
@Transactional
public class CompanyAddressServiceImpl implements CompanyAddressService{

    private final Logger log = LoggerFactory.getLogger(CompanyAddressServiceImpl.class);

    private final CompanyAddressRepository companyAddressRepository;

    private final CompanyAddressMapper companyAddressMapper;

    public CompanyAddressServiceImpl(CompanyAddressRepository companyAddressRepository, CompanyAddressMapper companyAddressMapper) {
        this.companyAddressRepository = companyAddressRepository;
        this.companyAddressMapper = companyAddressMapper;
    }

    /**
     * Save a companyAddress.
     *
     * @param companyAddressDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CompanyAddressDTO save(CompanyAddressDTO companyAddressDTO) {
        log.debug("Request to save CompanyAddress : {}", companyAddressDTO);
        CompanyAddress companyAddress = companyAddressMapper.toEntity(companyAddressDTO);
        companyAddress = companyAddressRepository.save(companyAddress);
        return companyAddressMapper.toDto(companyAddress);
    }

    /**
     *  Get all the companyAddresses.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CompanyAddressDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CompanyAddresses");
        return companyAddressRepository.findAll(pageable)
            .map(companyAddressMapper::toDto);
    }

    /**
     *  Get one companyAddress by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CompanyAddressDTO findOne(Long id) {
        log.debug("Request to get CompanyAddress : {}", id);
        CompanyAddress companyAddress = companyAddressRepository.findOne(id);
        return companyAddressMapper.toDto(companyAddress);
    }

    /**
     *  Delete the  companyAddress by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CompanyAddress : {}", id);
        companyAddressRepository.delete(id);
    }

    @Override
    public CompanyAddressDTO findOneByCompanyId(Long company_id) {
        CompanyAddress companyAddress = companyAddressRepository.findFirstByCompanyId(company_id);
        return companyAddressMapper.toDto(companyAddress);
    }
}
