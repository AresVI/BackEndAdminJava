package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.AuditTaskStandardObservation;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AuditTaskStandardObservation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuditTaskStandardObservationRepository extends JpaRepository<AuditTaskStandardObservation, Long> {

}
