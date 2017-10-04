package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.AuditTask;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AuditTask entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuditTaskRepository extends JpaRepository<AuditTask, Long> {

}
