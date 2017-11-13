package com.labausegtic.aresvi.domain;

import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A TraceabilityAudit.
 */
@Entity
@Table(name = "traceability_audit")
public class TraceabilityAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category")
    private String category;

    @NotNull
    @Column(name = "status")
    private String status;

    @Column(name = "creation_date")
    private Instant creationDate;

    @Column(name = "finished_date")
    private Instant finishedDate;

    @NotNull
    @ManyToMany
    @JoinTable(name = "traceability_audit_audit_process",
        joinColumns = @JoinColumn(name="traceability_audit_id", referencedColumnName="id"),
        inverseJoinColumns = @JoinColumn(name="audit_process_id", referencedColumnName="id"))
    private Set<AuditProcess> auditProcesses = new HashSet<>();

    @NotNull
    @ManyToOne
    private Company company;

    @NotNull
    @ManyToOne
    private CompanyContactPerson companyContactPerson;

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

    public TraceabilityAudit name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public TraceabilityAudit category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public TraceabilityAudit creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Set<AuditProcess> getAuditProcesses() {
        return auditProcesses;
    }

    public TraceabilityAudit auditProcesses(Set<AuditProcess> auditProcesses) {
        this.auditProcesses = auditProcesses;
        return this;
    }

    public void setAuditProcesses(Set<AuditProcess> auditProcesses) {
        this.auditProcesses = auditProcesses;
    }

    public Company getCompany() {
        return company;
    }

    public TraceabilityAudit company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public CompanyContactPerson getCompanyContactPerson() {
        return companyContactPerson;
    }

    public void setCompanyContactPerson(CompanyContactPerson companyContactPerson) {
        this.companyContactPerson = companyContactPerson;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Instant finishedDate) {
        this.finishedDate = finishedDate;
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
        TraceabilityAudit traceabilityAudit = (TraceabilityAudit) o;
        if (traceabilityAudit.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), traceabilityAudit.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TraceabilityAudit{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", companyContactPerson='" + getCompanyContactPerson() + "'" +
            "}";
    }
}
