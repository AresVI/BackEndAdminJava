package com.labausegtic.aresvi.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A AuditAttributeAnalysis.
 */
@Entity
@Table(name = "audit_attribute_analysis")
public class AuditAttributeAnalysis implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "percentage_not_required")
    private Double percentageNotRequired;

    @Column(name = "percentage_level_1")
    private Double percentageLevel1;

    @Column(name = "percentage_level_2")
    private Double percentageLevel2;

    @Column(name = "percentage_level_3")
    private Double percentageLevel3;

    @Column(name = "percentage_level_4")
    private Double percentageLevel4;

    @Column(name = "percentage_level_5")
    private Double percentageLevel5;

    @OneToOne
    @JoinColumn(unique = true)
    private TraceabilityAudit traceabilityAudit;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPercentageNotRequired() {
        return percentageNotRequired;
    }

    public AuditAttributeAnalysis percentageNotRequired(Double percentageNotRequired) {
        this.percentageNotRequired = percentageNotRequired;
        return this;
    }

    public void setPercentageNotRequired(Double percentageNotRequired) {
        this.percentageNotRequired = percentageNotRequired;
    }

    public Double getPercentageLevel1() {
        return percentageLevel1;
    }

    public AuditAttributeAnalysis percentageLevel1(Double percentageLevel1) {
        this.percentageLevel1 = percentageLevel1;
        return this;
    }

    public void setPercentageLevel1(Double percentageLevel1) {
        this.percentageLevel1 = percentageLevel1;
    }

    public Double getPercentageLevel2() {
        return percentageLevel2;
    }

    public AuditAttributeAnalysis percentageLevel2(Double percentageLevel2) {
        this.percentageLevel2 = percentageLevel2;
        return this;
    }

    public void setPercentageLevel2(Double percentageLevel2) {
        this.percentageLevel2 = percentageLevel2;
    }

    public Double getPercentageLevel3() {
        return percentageLevel3;
    }

    public AuditAttributeAnalysis percentageLevel3(Double percentageLevel3) {
        this.percentageLevel3 = percentageLevel3;
        return this;
    }

    public void setPercentageLevel3(Double percentageLevel3) {
        this.percentageLevel3 = percentageLevel3;
    }

    public Double getPercentageLevel4() {
        return percentageLevel4;
    }

    public AuditAttributeAnalysis percentageLevel4(Double percentageLevel4) {
        this.percentageLevel4 = percentageLevel4;
        return this;
    }

    public void setPercentageLevel4(Double percentageLevel4) {
        this.percentageLevel4 = percentageLevel4;
    }

    public Double getPercentageLevel5() {
        return percentageLevel5;
    }

    public AuditAttributeAnalysis percentageLevel5(Double percentageLevel5) {
        this.percentageLevel5 = percentageLevel5;
        return this;
    }

    public void setPercentageLevel5(Double percentageLevel5) {
        this.percentageLevel5 = percentageLevel5;
    }

    public TraceabilityAudit getTraceabilityAudit() {
        return traceabilityAudit;
    }

    public AuditAttributeAnalysis traceabilityAudit(TraceabilityAudit traceabilityAudit) {
        this.traceabilityAudit = traceabilityAudit;
        return this;
    }

    public void setTraceabilityAudit(TraceabilityAudit traceabilityAudit) {
        this.traceabilityAudit = traceabilityAudit;
    }
    // jhipster-needle-entity-add-getters-setters - Jhipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuditAttributeAnalysis auditAttributeAnalysis = (AuditAttributeAnalysis) o;
        if (auditAttributeAnalysis.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), auditAttributeAnalysis.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AuditAttributeAnalysis{" +
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
