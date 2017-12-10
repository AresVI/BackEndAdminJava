package com.labausegtic.aresvi.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the AuditAttributeAnalysis entity.
 */
public class AuditAttributeAnalysisDTO implements Serializable {

    private Long id;

    private Double percentageNotRequired;

    private Double percentageLevel1;

    private Double percentageLevel2;

    private Double percentageLevel3;

    private Double percentageLevel4;

    private Double percentageLevel5;

    private Long traceabilityAuditId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPercentageNotRequired() {
        return percentageNotRequired;
    }

    public void setPercentageNotRequired(Double percentageNotRequired) {
        this.percentageNotRequired = percentageNotRequired;
    }

    public Double getPercentageLevel1() {
        return percentageLevel1;
    }

    public void setPercentageLevel1(Double percentageLevel1) {
        this.percentageLevel1 = percentageLevel1;
    }

    public Double getPercentageLevel2() {
        return percentageLevel2;
    }

    public void setPercentageLevel2(Double percentageLevel2) {
        this.percentageLevel2 = percentageLevel2;
    }

    public Double getPercentageLevel3() {
        return percentageLevel3;
    }

    public void setPercentageLevel3(Double percentageLevel3) {
        this.percentageLevel3 = percentageLevel3;
    }

    public Double getPercentageLevel4() {
        return percentageLevel4;
    }

    public void setPercentageLevel4(Double percentageLevel4) {
        this.percentageLevel4 = percentageLevel4;
    }

    public Double getPercentageLevel5() {
        return percentageLevel5;
    }

    public void setPercentageLevel5(Double percentageLevel5) {
        this.percentageLevel5 = percentageLevel5;
    }

    public Long getTraceabilityAuditId() {
        return traceabilityAuditId;
    }

    public void setTraceabilityAuditId(Long traceabilityAuditId) {
        this.traceabilityAuditId = traceabilityAuditId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuditAttributeAnalysisDTO auditAttributeAnalysisDTO = (AuditAttributeAnalysisDTO) o;
        if(auditAttributeAnalysisDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), auditAttributeAnalysisDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AuditAttributeAnalysisDTO{" +
            "id=" + getId() +
            ", percentageNotRequired='" + getPercentageNotRequired() + "'" +
            ", percentageLevel1='" + getPercentageLevel1() + "'" +
            ", percentageLevel2='" + getPercentageLevel2() + "'" +
            ", percentageLevel3='" + getPercentageLevel3() + "'" +
            ", percentageLevel4='" + getPercentageLevel4() + "'" +
            ", percentageLevel5='" + getPercentageLevel5() + "'" +
            "}";
    }
}
