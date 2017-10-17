package com.labausegtic.aresvi.service.dto;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the CategoryAttribute entity.
 */
public class CategoryAttributeCompleteDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private Long auditTaskId;

    private Set<AttributeCompleteDTO> attributeCompleteDTOSet;

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

    public Long getAuditTaskId() {
        return auditTaskId;
    }

    public void setAuditTaskId(Long auditTaskId) {
        this.auditTaskId = auditTaskId;
    }

    public Set<AttributeCompleteDTO> getAttributeCompleteDTOSet() {
        return attributeCompleteDTOSet;
    }

    public void setAttributeCompleteDTOSet(Set<AttributeCompleteDTO> attributeCompleteDTOSet) {
        this.attributeCompleteDTOSet = attributeCompleteDTOSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CategoryAttributeCompleteDTO categoryAttributeDTO = (CategoryAttributeCompleteDTO) o;
        if(categoryAttributeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), categoryAttributeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CategoryAttributeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
