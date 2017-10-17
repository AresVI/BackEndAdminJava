package com.labausegtic.aresvi.service.dto;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the AuditTask entity.
 */
public class AuditTaskCompleteDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private Long containerId;

    private Set<CategoryAttributeCompleteDTO> categoryAttributeDTOSet;

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

    public Long getContainerId() {
        return containerId;
    }

    public void setContainerId(Long containerId) {
        this.containerId = containerId;
    }

    public Set<CategoryAttributeCompleteDTO> getCategoryAttributeDTOSet() {
        return categoryAttributeDTOSet;
    }

    public void setCategoryAttributeDTOSet(Set<CategoryAttributeCompleteDTO> categoryAttributeDTOSet) {
        this.categoryAttributeDTOSet = categoryAttributeDTOSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuditTaskCompleteDTO auditTaskDTO = (AuditTaskCompleteDTO) o;
        if(auditTaskDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), auditTaskDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AuditTaskDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
