package com.labausegtic.aresvi.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the CategoryAttRecommendation entity.
 */
public class CategoryAttRecommendationDTO implements Serializable {

    private Long id;

    @NotNull
    @Lob
    private String description;

    private Long auditTaskRecomId;

    private Long categoryAttributeId;

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

    public Long getAuditTaskRecomId() {
        return auditTaskRecomId;
    }

    public void setAuditTaskRecomId(Long auditTaskRecommendationId) {
        this.auditTaskRecomId = auditTaskRecommendationId;
    }

    public Long getCategoryAttributeId() {
        return categoryAttributeId;
    }

    public void setCategoryAttributeId(Long categoryAttributeId) {
        this.categoryAttributeId = categoryAttributeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CategoryAttRecommendationDTO categoryAttRecommendationDTO = (CategoryAttRecommendationDTO) o;
        if(categoryAttRecommendationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), categoryAttRecommendationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CategoryAttRecommendationDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
