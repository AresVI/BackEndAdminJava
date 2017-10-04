package com.labausegtic.aresvi.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the AuditProcess entity.
 */
public class AuditProcessDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private Long traceabilityAuditId;

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

    public Long getTraceabilityAuditId() {
        return traceabilityAuditId;
    }

    public void setTraceabilityAuditId(Long traceabilityAuditId) {
        this.traceabilityAuditId = traceabilityAuditId;
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
