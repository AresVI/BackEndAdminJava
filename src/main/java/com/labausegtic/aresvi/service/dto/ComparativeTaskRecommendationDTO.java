package com.labausegtic.aresvi.service.dto;

import com.labausegtic.aresvi.domain.AuditTask;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the Attribute entity.
 */
public class ComparativeTaskRecommendationDTO implements Serializable {

    private AuditTask auditTask;

    private List<ComparativeCatAttrRecommendationDTO> comparativeCatAttrRecommendationList;

    public AuditTask getAuditTask() {
        return auditTask;
    }

    public void setAuditTask(AuditTask auditTask) {
        this.auditTask = auditTask;
    }

    public List<ComparativeCatAttrRecommendationDTO> getComparativeCatAttrRecommendationList() {
        return comparativeCatAttrRecommendationList;
    }

    public void setComparativeCatAttrRecommendationList(List<ComparativeCatAttrRecommendationDTO> comparativeCatAttrRecommendationList) {
        this.comparativeCatAttrRecommendationList = comparativeCatAttrRecommendationList;
    }
}
