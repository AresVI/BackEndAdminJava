package com.labausegtic.aresvi.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A AuditProcess.
 */
@Entity
@Table(name = "audit_process")
public class AuditProcess implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    private TraceabilityAudit traceabilityAudit;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public AuditProcess name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TraceabilityAudit getTraceabilityAudit() {
        return traceabilityAudit;
    }

    public AuditProcess traceabilityAudit(TraceabilityAudit traceabilityAudit) {
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
        AuditProcess auditProcess = (AuditProcess) o;
        if (auditProcess.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), auditProcess.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AuditProcess{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
