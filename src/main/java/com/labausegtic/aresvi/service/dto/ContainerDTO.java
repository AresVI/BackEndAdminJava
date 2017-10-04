package com.labausegtic.aresvi.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Container entity.
 */
public class ContainerDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private Long participantId;

    private Long auditProcessId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContainerDTO containerDTO = (ContainerDTO) o;
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
