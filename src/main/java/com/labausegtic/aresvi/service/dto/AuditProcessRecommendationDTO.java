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

    private Long recommendationId;

    private Long auditProcessId;

    private Set<CategoryAttrRecommendationDTO> CategoryAttrRecommendationDTOSet;

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

    public Set<CategoryAttrRecommendationDTO> getCategoryAttrRecommendationDTOSet() {
        return CategoryAttrRecommendationDTOSet;
    }

    public void setCategoryAttrRecommendationDTOSet(Set<CategoryAttrRecommendationDTO> CategoryAttrRecommendationDTOSet) {
        this.CategoryAttrRecommendationDTOSet = CategoryAttrRecommendationDTOSet;
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
