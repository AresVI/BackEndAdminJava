package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.Auditor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Auditor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuditorRepository extends JpaRepository<Auditor, Long> {
    @Query("select distinct auditor from Auditor auditor left join fetch auditor.companies")
    List<Auditor> findAllWithEagerRelationships();

    @Query("select auditor from Auditor auditor left join fetch auditor.companies where auditor.id =:id")
    Auditor findOneWithEagerRelationships(@Param("id") Long id);

}
