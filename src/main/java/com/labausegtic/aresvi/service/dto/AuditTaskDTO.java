package com.labausegtic.aresvi.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
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

    private ContainerDTO container;

    private LocalDate revisedDate;

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

    public ContainerDTO getContainer() {
        return container;
    }

    public void setContainer(ContainerDTO container) {
        this.container = container;
    }

    public LocalDate getRevisedDate() {
        return revisedDate;
    }

    public void setRevisedDate(LocalDate revisedDate) {
        this.revisedDate = revisedDate;
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
