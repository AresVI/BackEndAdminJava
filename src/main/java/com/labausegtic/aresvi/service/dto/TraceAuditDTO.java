package com.labausegtic.aresvi.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the TraceAudit entity.
 */
public class TraceAuditDTO implements Serializable {

    private Long id;

    private Long traceabilityAuditId;

    private Long auditTaskRecommendationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTraceabilityAuditId() {
        return traceabilityAuditId;
    }

    public void setTraceabilityAuditId(Long traceabilityAuditId) {
        this.traceabilityAuditId = traceabilityAuditId;
    }

    public Long getAuditTaskRecommendationId() {
        return auditTaskRecommendationId;
    }

    public void setAuditTaskRecommendationId(Long auditTaskRecommendationId) {
        this.auditTaskRecommendationId = auditTaskRecommendationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TraceAuditDTO traceAuditDTO = (TraceAuditDTO) o;
        if(traceAuditDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), traceAuditDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TraceAuditDTO{" +
            "id=" + getId() +
            "}";
    }
}
