package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.Container;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Container entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContainerRepository extends JpaRepository<Container, Long> {

}
