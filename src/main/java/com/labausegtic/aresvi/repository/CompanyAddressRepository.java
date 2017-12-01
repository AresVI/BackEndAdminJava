package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.CompanyAddress;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CompanyAddress entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompanyAddressRepository extends JpaRepository<CompanyAddress, Long> {

    CompanyAddress findFirstByCompanyId(Long compnay_id);

}
