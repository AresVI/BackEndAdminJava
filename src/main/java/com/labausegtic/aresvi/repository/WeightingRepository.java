package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.Weighting;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Weighting entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WeightingRepository extends JpaRepository<Weighting, Long> {

}
