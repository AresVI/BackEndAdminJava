package com.labausegtic.aresvi.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A AuditProcessStandardObservation.
 */
@Entity
@Table(name = "audit_process_observation")
public class AuditProcessStandardObservation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "observation", nullable = false)
    private String observation;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservation() {
        return observation;
    }

    public AuditProcessStandardObservation observation(String observation) {
        this.observation = observation;
        return this;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
    // jhipster-needle-entity-add-getters-setters - Jhipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuditProcessStandardObservation auditProcessStandardObservation = (AuditProcessStandardObservation) o;
        if (auditProcessStandardObservation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), auditProcessStandardObservation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AuditProcessStandardObservation{" +
            "id=" + getId() +
            ", observation='" + getObservation() + "'" +
            "}";
    }
}
