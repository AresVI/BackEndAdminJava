package com.labausegtic.aresvi.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the AuditTask entity.
 */
public class AuditTaskDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private Long containerId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuditTaskDTO auditTaskDTO = (AuditTaskDTO) o;
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
