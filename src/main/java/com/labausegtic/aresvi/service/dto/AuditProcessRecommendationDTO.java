package com.labausegtic.aresvi.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the AuditProcessRecommendation entity.
 */
public class AuditProcessRecommendationDTO implements Serializable {

    private Long id;

    @NotNull
    @Lob
    private String description;

    private String standardObservation;

    private boolean reviewed;

    private Long recommendationId;

    private RecommendationDTO recommendation;

    private Long auditProcessId;

    private AuditProcessDTO auditProcess;

    private Long bonitaBpmCaseId;

    private Set<AuditTaskRecommendationDTO> auditTaskRecommendationSet;

    private boolean taken;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStandardObservation() {
        return standardObservation;
    }

    public void setStandardObservation(String standardObservation) {
        this.standardObservation = standardObservation;
    }

    public Long getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(Long recommendationId) {
        this.recommendationId = recommendationId;
    }

    public Long getAuditProcessId() {
        return auditProcessId;
    }

    public void setAuditProcessId(Long auditProcessId) {
        this.auditProcessId = auditProcessId;
    }

    public AuditProcessDTO getAuditProcess() {
        return auditProcess;
    }

    public void setAuditProcess(AuditProcessDTO auditProcess) {
        this.auditProcess = auditProcess;
    }

    public Set<AuditTaskRecommendationDTO> getAuditTaskRecommendationSet() {
        return auditTaskRecommendationSet;
    }

    public void setAuditTaskRecommendationSet(Set<AuditTaskRecommendationDTO> auditTaskRecommendationSet) {
        this.auditTaskRecommendationSet = auditTaskRecommendationSet;
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    public RecommendationDTO getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(RecommendationDTO recommendation) {
        this.recommendation = recommendation;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuditProcessRecommendationDTO auditProcessRecommendationDTO = (AuditProcessRecommendationDTO) o;
        if(auditProcessRecommendationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), auditProcessRecommendationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AuditProcessRecommendationDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
