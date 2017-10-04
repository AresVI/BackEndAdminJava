package com.labausegtic.aresvi.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CategoryAttribute.
 */
@Entity
@Table(name = "category_attribute")
public class CategoryAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    private AuditTask auditTask;

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

    public CategoryAttribute name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuditTask getAuditTask() {
        return auditTask;
    }

    public CategoryAttribute auditTask(AuditTask auditTask) {
        this.auditTask = auditTask;
        return this;
    }

    public void setAuditTask(AuditTask auditTask) {
        this.auditTask = auditTask;
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
        CategoryAttribute categoryAttribute = (CategoryAttribute) o;
        if (categoryAttribute.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), categoryAttribute.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CategoryAttribute{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
