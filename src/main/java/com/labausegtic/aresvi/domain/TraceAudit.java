package com.labausegtic.aresvi.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A TraceAudit.
 */
@Entity
@Table(name = "trace_audit")
public class TraceAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TraceabilityAudit traceabilityAudit;

    @ManyToOne
    private AuditTaskRecommendation auditTaskRecommendation;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TraceabilityAudit getTraceabilityAudit() {
        return traceabilityAudit;
    }

    public TraceAudit traceabilityAudit(TraceabilityAudit traceabilityAudit) {
        this.traceabilityAudit = traceabilityAudit;
        return this;
    }

    public void setTraceabilityAudit(TraceabilityAudit traceabilityAudit) {
        this.traceabilityAudit = traceabilityAudit;
    }

    public AuditTaskRecommendation getAuditTaskRecommendation() {
        return auditTaskRecommendation;
    }

    public TraceAudit auditTaskRecommendation(AuditTaskRecommendation auditTaskRecommendation) {
        this.auditTaskRecommendation = auditTaskRecommendation;
        return this;
    }

    public void setAuditTaskRecommendation(AuditTaskRecommendation auditTaskRecommendation) {
        this.auditTaskRecommendation = auditTaskRecommendation;
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
        TraceAudit traceAudit = (TraceAudit) o;
        if (traceAudit.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), traceAudit.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TraceAudit{" +
            "id=" + getId() +
            "}";
    }
}
