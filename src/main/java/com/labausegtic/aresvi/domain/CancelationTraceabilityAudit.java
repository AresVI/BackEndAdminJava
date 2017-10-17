package com.labausegtic.aresvi.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CancelationTraceabilityAudit.
 */
@Entity
@Table(name = "cancelation_traceability_audit")
public class CancelationTraceabilityAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 1000)
    @Column(name = "justification", length = 1000, nullable = false)
    private String justification;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private TraceabilityAudit traceabilityAudit;

    @ManyToOne
    private User user;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJustification() {
        return justification;
    }

    public CancelationTraceabilityAudit justification(String justification) {
        this.justification = justification;
        return this;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public TraceabilityAudit getTraceabilityAudit() {
        return traceabilityAudit;
    }

    public CancelationTraceabilityAudit traceabilityAudit(TraceabilityAudit traceabilityAudit) {
        this.traceabilityAudit = traceabilityAudit;
        return this;
    }

    public void setTraceabilityAudit(TraceabilityAudit traceabilityAudit) {
        this.traceabilityAudit = traceabilityAudit;
    }

    public User getUser() {
        return user;
    }

    public CancelationTraceabilityAudit user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
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
        CancelationTraceabilityAudit cancelationTraceabilityAudit = (CancelationTraceabilityAudit) o;
        if (cancelationTraceabilityAudit.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cancelationTraceabilityAudit.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CancelationTraceabilityAudit{" +
            "id=" + getId() +
            ", justification='" + getJustification() + "'" +
            "}";
    }
}
