package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.Faqs;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Faqs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FaqsRepository extends JpaRepository<Faqs, Long> {

}
