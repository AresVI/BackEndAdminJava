package com.labausegtic.aresvi.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the AuditTaskRecommendation entity.
 */
public class AuditTaskRecommendationDTO implements Serializable {

    private Long id;

    @NotNull
    @Lob
    private String description;

    private boolean reviewed;

    private String standardObservation;

    private Long auditProcessRecomId;

    private Long auditTaskId;

    private AuditTaskDTO auditTask;

    private List<CategoryAttrRecommendationDTO> categoryAttrRecommendationSet;

    private Instant revisedDate;

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

    public Long getAuditProcessRecomId() {
        return auditProcessRecomId;
    }

    public void setAuditProcessRecomId(Long auditProcessRecommendationId) {
        this.auditProcessRecomId = auditProcessRecommendationId;
    }

    public Long getAuditTaskId() {
        return auditTaskId;
    }

    public void setAuditTaskId(Long auditTaskId) {
        this.auditTaskId = auditTaskId;
    }

    public AuditTaskDTO getAuditTask() {
        return auditTask;
    }

    public void setAuditTask(AuditTaskDTO auditTask) {
        this.auditTask = auditTask;
    }

    public List<CategoryAttrRecommendationDTO> getCategoryAttrRecommendationSet() {
        return categoryAttrRecommendationSet;
    }

    public void setCategoryAttrRecommendationSet(List<CategoryAttrRecommendationDTO> categoryAttrRecommendationSet) {
        this.categoryAttrRecommendationSet = categoryAttrRecommendationSet;
    }

    public void addCategoryAttrRecommendationSet(CategoryAttrRecommendationDTO categoryAttrRecommendation) {
        if(this.categoryAttrRecommendationSet == null) {
            this.categoryAttrRecommendationSet = new ArrayList<>();
        }
        this.categoryAttrRecommendationSet.add(categoryAttrRecommendation);
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    public Instant getRevisedDate() {
        return revisedDate;
    }

    public void setRevisedDate(Instant revisedDate) {
        this.revisedDate = revisedDate;
    }

    public String getStandardObservation() {
        return standardObservation;
    }

    public void setStandardObservation(String standardObservation) {
        this.standardObservation = standardObservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuditTaskRecommendationDTO auditTaskRecommendationDTO = (AuditTaskRecommendationDTO) o;
        if(auditTaskRecommendationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), auditTaskRecommendationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AuditTaskRecommendationDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
