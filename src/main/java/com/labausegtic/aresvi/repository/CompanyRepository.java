package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.Company;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the Company entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findCompanyByIdentifier(@Param("identifier") String identifier);

}
