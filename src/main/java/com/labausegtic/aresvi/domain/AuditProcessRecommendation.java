package com.labausegtic.aresvi.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A AuditProcessRecommendation.
 */
@Entity
@Table(name = "audit_process_recommendation")
public class AuditProcessRecommendation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "standard_observation")
    private String standardObservation;

    @Column(name = "reviewed")
    private boolean reviewed;

    @Column(name = "taken")
    private boolean taken;

    @Column(name = "bonita_bpm_base_id")
    private Long bonitaBpmCaseId;

    @ManyToOne
    private Recommendation recommendation;

    @ManyToOne
    private AuditProcess auditProcess;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public AuditProcessRecommendation description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStandardObservation() {
        return standardObservation;
    }

    public AuditProcessRecommendation standardObservation(String standardObservation) {
        this.standardObservation = standardObservation;
        return this;
    }

    public void setStandardObservation(String standardObservation) {
        this.standardObservation = standardObservation;
    }

    public Recommendation getRecommendation() {
        return recommendation;
    }

    public AuditProcessRecommendation recommendation(Recommendation recommendation) {
        this.recommendation = recommendation;
        return this;
    }

    public void setRecommendation(Recommendation recommendation) {
        this.recommendation = recommendation;
    }

    public AuditProcess getAuditProcess() {
        return auditProcess;
    }

    public AuditProcessRecommendation auditProcess(AuditProcess auditProcess) {
        this.auditProcess = auditProcess;
        return this;
    }

    public void setAuditProcess(AuditProcess auditProcess) {
        this.auditProcess = auditProcess;
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public Long getBonitaBpmCaseId() {
        return bonitaBpmCaseId;
    }

    public void setBonitaBpmCaseId(Long bonitaBpmCaseId) {
        this.bonitaBpmCaseId = bonitaBpmCaseId;
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
        AuditProcessRecommendation auditProcessRecommendation = (AuditProcessRecommendation) o;
        if (auditProcessRecommendation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), auditProcessRecommendation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AuditProcessRecommendation{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", standardObservation='" + getStandardObservation() + "'" +
            "}";
    }
}
