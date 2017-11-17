package com.labausegtic.aresvi.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class AuditProcessCompleteDTO  implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private Set<ContainerCompleteDTO> containerSet;

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

    public Set<ContainerCompleteDTO> getContainerSet() {
        return containerSet;
    }

    public void setContainerSet(Set<ContainerCompleteDTO> containerSet) {
        this.containerSet = containerSet;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AuditProcess{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
