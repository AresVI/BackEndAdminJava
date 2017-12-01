package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.service.CompanyAddressService;
import com.labausegtic.aresvi.service.CompanyService;
import com.labausegtic.aresvi.domain.Company;
import com.labausegtic.aresvi.repository.CompanyRepository;
import com.labausegtic.aresvi.service.dto.CompanyDTO;
import com.labausegtic.aresvi.service.mapper.CompanyMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Company.
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService{

    private final Logger log = LoggerFactory.getLogger(CompanyServiceImpl.class);

    private final CompanyRepository companyRepository;

    private final CompanyMapper companyMapper;

    private final CompanyAddressService companyAddressService;

    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper,
                              CompanyAddressService companyAddressService) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.companyAddressService = companyAddressService;
    }

    /**
     * Save a company.
     *
     * @param companyDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CompanyDTO save(CompanyDTO companyDTO) {
        log.debug("Request to save Company : {}", companyDTO);
        Company company = companyMapper.toEntity(companyDTO);
        company = companyRepository.save(company);
        return companyMapper.toDto(company);
    }

    /**
     *  Get all the companies.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CompanyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Companies");
        return companyRepository.findAll(pageable)
            .map(companyMapper::toDto);
    }

    /**
     *  Get one company by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CompanyDTO findOne(Long id) {
        log.debug("Request to get Company : {}", id);
        Company company = companyRepository.findOne(id);
        CompanyDTO companyDTO = companyMapper.toDto(company);
        companyDTO.setCompanyAddress(companyAddressService.findOneByCompanyId(company.getId()));
        return companyDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyDTO findOneByIdentification(String identifier) {
        return companyMapper.toDto(companyRepository.findCompanyByIdentifier(identifier));
    }

    /**
     *  Delete the  company by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Company : {}", id);
        companyRepository.delete(id);
    }
}
