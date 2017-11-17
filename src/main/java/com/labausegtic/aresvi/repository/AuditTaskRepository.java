package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.AuditTask;
import com.labausegtic.aresvi.domain.Container;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.util.Set;


/**
 * Spring Data JPA repository for the AuditTask entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuditTaskRepository extends JpaRepository<AuditTask, Long> {

    Set<AuditTask> findAllByContainer_Id(Long containerId);

    List<AuditTask> findAllByContainerIn(Set<Container> containerList);

}
