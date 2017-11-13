package com.labausegtic.aresvi.repository;

import com.labausegtic.aresvi.domain.AttributeRecommendation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.util.Set;


/**
 * Spring Data JPA repository for the AttributeRecommendation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttributeRecommendationRepository extends JpaRepository<AttributeRecommendation, Long> {

    List<AttributeRecommendation> findAllByCategoryAttrRecom_Id(Long categoryAttrRecom_id);

    @Query( name = "findAllForTraceabilityAuditId",
        value =
        "SELECT ar \n" +
        "FROM  \n" +
        "    AttributeRecommendation as ar, CategoryAttrRecommendation as car, AuditTaskRecommendation as atr, AuditProcessRecommendation as apr, Recommendation as r\n" +
        "WHERE  \n" +
        "    r.traceabilityAudit.id = :traceability_audit_id \n" +
        "    AND apr.recommendation.id = r.id \n" +
        "    AND atr.auditProcessRecom.id = apr.id \n" +
        "    AND car.auditTaskRecom.id = atr.id \n" +
        "    AND ar.categoryAttrRecom.id = car.id"
    )
    Set<AttributeRecommendation> findAllForTraceabilityAuditId(@Param("traceability_audit_id") long traceability_audit_id);

}
