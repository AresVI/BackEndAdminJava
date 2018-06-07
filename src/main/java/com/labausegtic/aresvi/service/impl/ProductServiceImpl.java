package com.labausegtic.aresvi.service.impl;

import com.labausegtic.aresvi.domain.ProductType;
import com.labausegtic.aresvi.repository.ProductTypeRepository;
import com.labausegtic.aresvi.service.CompanyService;
import com.labausegtic.aresvi.service.ProductService;
import com.labausegtic.aresvi.domain.Product;
import com.labausegtic.aresvi.repository.ProductRepository;
import com.labausegtic.aresvi.service.dto.CompanyDTO;
import com.labausegtic.aresvi.service.dto.CompanyProductDTO;
import com.labausegtic.aresvi.service.dto.ProductDTO;
import com.labausegtic.aresvi.service.dto.ProductTypeProductDTO;
import com.labausegtic.aresvi.service.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Service Implementation for managing Product.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    private final CompanyService companyService;

    private final ProductTypeRepository productTypeRepository;

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper,
                              CompanyService companyService, ProductTypeRepository productTypeRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.companyService = companyService;
        this.productTypeRepository = productTypeRepository;
    }

    /**
     * Save a product.
     *
     * @param productDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProductDTO save(ProductDTO productDTO) {
        log.debug("Request to save Product : {}", productDTO);
        Product product = productMapper.toEntity(productDTO);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    /**
     *  Get all the products.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public CompanyProductDTO findAll(Long company_id) {
        log.debug("Request to get all Products");

        CompanyProductDTO companyProductDTO = new CompanyProductDTO();

        CompanyDTO company = companyService.findOne(company_id);

        companyProductDTO.setId(company_id);
        companyProductDTO.setName(company.getName());

        Set<ProductTypeProductDTO> productTypeProductList = new HashSet<>();

        List<ProductType> productTypes = productTypeRepository.findAll();

        for (ProductType pt: productTypes) {
            ProductTypeProductDTO productTypeProductDTO = new ProductTypeProductDTO();

            productTypeProductDTO.setId(pt.getId());
            productTypeProductDTO.setName(pt.getName());

            Set<ProductDTO> productDTOSet = new HashSet<>();

            List<Product> products = productRepository.findAllByCompanyIdAndProductTypeId(company_id, pt.getId());

            for (Product p: products) {
                productDTOSet.add(productMapper.toDto(p));
            }

            productTypeProductDTO.setProducts(productDTOSet);

            productTypeProductList.add(productTypeProductDTO);
        }

        companyProductDTO.setProductTypes(productTypeProductList);

        return companyProductDTO;

    }

    /**
     *  Get one product by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ProductDTO findOne(Long id) {
        log.debug("Request to get Product : {}", id);
        Product product = productRepository.findOne(id);
        return productMapper.toDto(product);
    }

    /**
     *  Delete the  product by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Product : {}", id);
        productRepository.delete(id);
    }
}
