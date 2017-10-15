package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.CompanyContactPerson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the CompanyContactPerson entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompanyContactPersonRepository extends JpaRepository<CompanyContactPerson, Long> {

    Page<CompanyContactPerson> findCompanyContactPeopleByCompany_Id(Long company_id, Pageable pageable);
}
