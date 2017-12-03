package com.labausegtic.aresvi.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AuditTaskStandardObservation entity.
 */
public class AuditTaskStandardObservationDTO implements Serializable {

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

        AuditTaskStandardObservationDTO auditTaskStandardObservationDTO = (AuditTaskStandardObservationDTO) o;
        if(auditTaskStandardObservationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), auditTaskStandardObservationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AuditTaskStandardObservationDTO{" +
            "id=" + getId() +
            ", observation='" + getObservation() + "'" +
            "}";
    }
}
