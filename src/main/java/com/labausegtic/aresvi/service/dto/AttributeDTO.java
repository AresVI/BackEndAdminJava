package com.labausegtic.aresvi.service.dto;


import com.labausegtic.aresvi.domain.Weighting;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Attribute entity.
 */
public class AttributeDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Boolean required;

    private Long weightingId;

    private WeightingDTO weighting;

    private Long categoryAttributeId;

    private CategoryAttributeDTO categoryAttribute;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Long getWeightingId() {
        return weightingId;
    }

    public void setWeightingId(Long weightingId) {
        this.weightingId = weightingId;
    }

    public WeightingDTO getWeighting() {
        return weighting;
    }

    public void setWeighting(WeightingDTO weighting) {
        this.weighting = weighting;
    }

    public Long getCategoryAttributeId() {
        return categoryAttributeId;
    }

    public void setCategoryAttributeId(Long categoryAttributeId) {
        this.categoryAttributeId = categoryAttributeId;
    }

    public Boolean getRequired() {
        return required;
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

        AttributeDTO attributeDTO = (AttributeDTO) o;
        if(attributeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), attributeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AttributeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", required='" + isRequired() + "'" +
            "}";
    }
}
