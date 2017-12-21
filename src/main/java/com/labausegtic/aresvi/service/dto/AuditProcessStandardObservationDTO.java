package com.labausegtic.aresvi.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AuditProcessStandardObservation entity.
 */
public class AuditProcessStandardObservationDTO implements Serializable {

    private Long id;

    @NotNull
    private String observation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuditProcessStandardObservationDTO auditProcessStandardObservationDTO = (AuditProcessStandardObservationDTO) o;
        if(auditProcessStandardObservationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), auditProcessStandardObservationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AuditProcessStandardObservationDTO{" +
            "id=" + getId() +
            ", observation='" + getObservation() + "'" +
            "}";
    }
}
