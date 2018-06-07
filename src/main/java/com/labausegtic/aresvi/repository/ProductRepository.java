package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.Product;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the Product entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCompanyIdAndProductTypeId(Long company_id, Long product_type_id);

}
