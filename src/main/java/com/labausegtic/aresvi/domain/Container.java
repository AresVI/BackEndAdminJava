package com.labausegtic.aresvi.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Container.
 */
@Entity
@Table(name = "container")
public class Container implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(unique = true)
    private Participant participant;

    @ManyToOne
    private AuditProcess auditProcess;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Container name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Participant getParticipant() {
        return participant;
    }

    public Container participant(Participant participant) {
        this.participant = participant;
        return this;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public AuditProcess getAuditProcess() {
        return auditProcess;
    }

    public Container auditProcess(AuditProcess auditProcess) {
        this.auditProcess = auditProcess;
        return this;
    }

    public void setAuditProcess(AuditProcess auditProcess) {
        this.auditProcess = auditProcess;
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
        Container container = (Container) o;
        if (container.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), container.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Container{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
