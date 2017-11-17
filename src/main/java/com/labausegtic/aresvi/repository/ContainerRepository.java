package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.Container;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


/**
 * Spring Data JPA repository for the Container entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContainerRepository extends JpaRepository<Container, Long> {

    Set<Container> findAllByAuditProcess_Id(Long auditProcess);

}
