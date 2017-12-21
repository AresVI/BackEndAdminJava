package com.labausegtic.aresvi.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the CategoryAttrRecommendation entity.
 */
public class CategoryAttrRecommendationDTO implements Serializable {

    private Long id;

    @NotNull
    @Lob
    private String description;

    private Long auditTaskRecomId;

    private Long categoryAttributeId;

    private CategoryAttributeDTO categoryAttribute;

    private Set<AttributeRecommendationDTO> attributeRecommendationSet;

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

    public Set<AttributeRecommendationDTO> getAttributeRecommendationSet() {
        return attributeRecommendationSet;
    }

    public void setAttributeRecommendationSet(Set<AttributeRecommendationDTO> attributeRecommendationSet) {
        this.attributeRecommendationSet = attributeRecommendationSet;
    }

    public void addAttributeRecommendationSet(AttributeRecommendationDTO attributeRecommendation) {
        if(this.attributeRecommendationSet == null){
            this.attributeRecommendationSet = new HashSet<>();
        }
        this.attributeRecommendationSet.add(attributeRecommendation);
    }

    public CategoryAttributeDTO getCategoryAttribute() {
        return categoryAttribute;
    }

    public void setCategoryAttribute(CategoryAttributeDTO categoryAttribute) {
        this.categoryAttribute = categoryAttribute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CategoryAttrRecommendationDTO categoryAttrRecommendationDTO = (CategoryAttrRecommendationDTO) o;
        if(categoryAttrRecommendationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), categoryAttrRecommendationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CategoryAttrRecommendationDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
