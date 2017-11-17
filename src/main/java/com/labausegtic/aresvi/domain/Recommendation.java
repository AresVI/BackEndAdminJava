package com.labausegtic.aresvi.domain;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Recommendation.
 */
@Entity
@Table(name = "recommendation")
public class Recommendation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "qualification")
    private Integer qualification;

    @Column(name = "reviewed")
    private boolean reviewed;

    @Column(name = "level_computerization")
    private Integer levelComputerization;

    @CreatedDate
    @Column(name = "creation_date", updatable = false)
    private Instant creationDate;

    @ManyToOne
    private TraceabilityAudit traceabilityAudit;

    @ManyToOne
    private Auditor auditor;

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

    public Recommendation name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQualification() {
        return qualification;
    }

    public Recommendation qualification(Integer qualification) {
        this.qualification = qualification;
        return this;
    }

    public void setQualification(Integer qualification) {
        this.qualification = qualification;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public Recommendation creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public TraceabilityAudit getTraceabilityAudit() {
        return traceabilityAudit;
    }

    public Recommendation traceabilityAudit(TraceabilityAudit traceabilityAudit) {
        this.traceabilityAudit = traceabilityAudit;
        return this;
    }

    public void setTraceabilityAudit(TraceabilityAudit traceabilityAudit) {
        this.traceabilityAudit = traceabilityAudit;
    }

    public Auditor getAuditor() {
        return auditor;
    }

    public void setAuditor(Auditor auditor) {
        this.auditor = auditor;
    }

    public Integer getLevelComputerization() {
        return levelComputerization;
    }

    public void setLevelComputerization(Integer levelComputerization) {
        this.levelComputerization = levelComputerization;
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
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
        Recommendation recommendation = (Recommendation) o;
        if (recommendation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), recommendation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Recommendation{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", qualification='" + getQualification() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            "}";
    }
}
