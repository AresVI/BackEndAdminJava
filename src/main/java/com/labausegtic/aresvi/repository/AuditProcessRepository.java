package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.AuditProcess;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.Set;


/**
 * Spring Data JPA repository for the AuditProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuditProcessRepository extends JpaRepository<AuditProcess, Long> {



}
