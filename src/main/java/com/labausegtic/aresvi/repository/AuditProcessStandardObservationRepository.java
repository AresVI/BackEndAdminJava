package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.AuditProcessStandardObservation;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AuditProcessStandardObservation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuditProcessStandardObservationRepository extends JpaRepository<AuditProcessStandardObservation, Long> {

}
