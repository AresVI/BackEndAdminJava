package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.Glossary;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Glossary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GlossaryRepository extends JpaRepository<Glossary, Long> {

}
