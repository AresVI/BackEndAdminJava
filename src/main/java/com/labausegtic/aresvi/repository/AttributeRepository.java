package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.Attribute;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.Set;


/**
 * Spring Data JPA repository for the Attribute entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {

    Set<Attribute> findAllByCategoryAttribute_Id(Long categoryAttributeId);

}
