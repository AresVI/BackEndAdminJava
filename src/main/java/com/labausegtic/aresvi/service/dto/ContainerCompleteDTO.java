package com.labausegtic.aresvi.service.dto;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the Container entity.
 */
public class ContainerCompleteDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private Long participantId;

    private Long auditProcessId;

    private Set<AuditTaskCompleteDTO> auditTaskDTOSet;

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

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public Long getAuditProcessId() {
        return auditProcessId;
    }

    public void setAuditProcessId(Long auditProcessId) {
        this.auditProcessId = auditProcessId;
    }

    public Set<AuditTaskCompleteDTO> getAuditTaskDTOSet() {
        return auditTaskDTOSet;
    }

    public void setAuditTaskDTOSet(Set<AuditTaskCompleteDTO> auditTaskDTOSet) {
        this.auditTaskDTOSet = auditTaskDTOSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContainerCompleteDTO containerDTO = (ContainerCompleteDTO) o;
        if(containerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), containerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContainerDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
