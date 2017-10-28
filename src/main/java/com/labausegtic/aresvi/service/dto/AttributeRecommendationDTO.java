package com.labausegtic.aresvi.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the AttributeRecommendation entity.
 */
public class AttributeRecommendationDTO implements Serializable {

    private Long id;

    @NotNull
    @Lob
    private String description;

    private Long categoryAttRecomId;

    private Long attributeId;

    private AttributeLiteDTO attribute;

    private boolean implemented;

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

    public Long getCategoryAttRecomId() {
        return categoryAttRecomId;
    }

    public void setCategoryAttRecomId(Long categoryAttRecommendationId) {
        this.categoryAttRecomId = categoryAttRecommendationId;
    }

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    public AttributeLiteDTO getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeLiteDTO attribute) {
        this.attribute = attribute;
    }

    public boolean isImplemented() {
        return implemented;
    }

    public void setImplemented(boolean implemented) {
        this.implemented = implemented;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AttributeRecommendationDTO attributeRecommendationDTO = (AttributeRecommendationDTO) o;
        if(attributeRecommendationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), attributeRecommendationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AttributeRecommendationDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
