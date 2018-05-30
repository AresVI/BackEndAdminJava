package com.labausegtic.aresvi.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ProductType.
 */
@Entity
@Table(name = "product_type")
public class ProductType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany
    @NotNull
    @JoinTable(name = "product_type_audit_process",
               joinColumns = @JoinColumn(name="product_types_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="audit_processes_id", referencedColumnName="id"))
    private Set<AuditProcess> auditProcesses = new HashSet<>();

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

    public ProductType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AuditProcess> getAuditProcesses() {
        return auditProcesses;
    }

    public ProductType auditProcesses(Set<AuditProcess> auditProcesses) {
        this.auditProcesses = auditProcesses;
        return this;
    }

    public ProductType addAuditProcess(AuditProcess auditProcess) {
        this.auditProcesses.add(auditProcess);
        return this;
    }

    public ProductType removeAuditProcess(AuditProcess auditProcess) {
        this.auditProcesses.remove(auditProcess);
        return this;
    }

    public void setAuditProcesses(Set<AuditProcess> auditProcesses) {
        this.auditProcesses = auditProcesses;
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
        ProductType productType = (ProductType) o;
        if (productType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
