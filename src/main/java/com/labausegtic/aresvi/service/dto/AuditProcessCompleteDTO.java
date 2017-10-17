package com.labausegtic.aresvi.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class AuditProcessCompleteDTO  implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private Set<ContainerCompleteDTO> containerDTOSet;

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

    public Set<ContainerCompleteDTO> getContainerDTOSet() {
        return containerDTOSet;
    }

    public void setContainerDTOSet(Set<ContainerCompleteDTO> containerDTOSet) {
        this.containerDTOSet = containerDTOSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuditProcessDTO auditProcessDTO = (AuditProcessDTO) o;
        if(auditProcessDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), auditProcessDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AuditProcessDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
