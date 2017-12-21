package com.labausegtic.aresvi.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Company.
 */
@Entity
@NamedNativeQuery(name = "Company.findByIdentifier",
    query="SELECT * FROM company c WHERE c.identifier = ':identifier'",
    resultClass = Company.class
)
@Table(
    name = "company",
    uniqueConstraints = @UniqueConstraint(columnNames = "identifier")
)
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(name = "identifier", nullable = false, unique = true)
    private String identifier;

    @ManyToMany
    @JoinTable(name = "auditor_companies",
        inverseJoinColumns = @JoinColumn(name="auditors_id", referencedColumnName="id"),
        joinColumns = @JoinColumn(name="companies_id", referencedColumnName="id"))
    private Set<Auditor> internal_auditors = new HashSet<>();

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

    public Company name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Company identifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public Set<Auditor> getInternal_auditors() {
        return internal_auditors;
    }

    public void setInternal_auditors(Set<Auditor> internal_auditors) {
        this.internal_auditors = internal_auditors;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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
        Company company = (Company) o;
        if (company.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), company.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Company{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", identifier='" + getIdentifier() + "'" +
            "}";
    }
}
