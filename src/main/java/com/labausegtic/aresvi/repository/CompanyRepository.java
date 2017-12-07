package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.util.Set;


/**
 * Spring Data JPA repository for the Company entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findCompanyByIdentifier(@Param("identifier") String identifier);

    Page<Company> findAllByIdIn(Pageable pageable, Set<Company> companies);

    Company findCompanyByNameOrIdentifier(String name, String identifier);

}
