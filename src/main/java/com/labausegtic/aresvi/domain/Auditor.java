package com.labausegtic.aresvi.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Auditor.
 */
@Entity
@Table(name = "auditor")
public class Auditor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "internal")
    private Boolean internal;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private User user;

    @ManyToMany
    @JoinTable(name = "auditor_companies",
               joinColumns = @JoinColumn(name="auditors_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="companies_id", referencedColumnName="id"))
    private Set<Company> companies = new HashSet<>();

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isInternal() {
        return internal;
    }

    public Auditor internal(Boolean internal) {
        this.internal = internal;
        return this;
    }

    public void setInternal(Boolean internal) {
        this.internal = internal;
    }

    public User getUser() {
        return user;
    }

    public Auditor user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public Auditor companies(Set<Company> companies) {
        this.companies = companies;
        return this;
    }

    public Auditor addCompanies(Company company) {
        this.companies.add(company);
        company.getInternal_auditors().add(this);
        return this;
    }

    public Auditor removeCompanies(Company company) {
        this.companies.remove(company);
        company.getInternal_auditors().remove(this);
        return this;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
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
        Auditor auditor = (Auditor) o;
        if (auditor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), auditor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Auditor{" +
            "id=" + getId() +
            ", internal='" + isInternal() + "'" +
            "}";
    }
}
