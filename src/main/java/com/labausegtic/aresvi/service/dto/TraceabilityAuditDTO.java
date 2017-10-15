package com.labausegtic.aresvi.service.dto;


import com.labausegtic.aresvi.domain.CompanyContactPerson;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the TraceabilityAudit entity.
 */
public class TraceabilityAuditDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private Instant creationDate;

    private Long companyId;

    private CompanyDTO company;

    private String category;

    private CompanyContactPerson companyContactPerson;

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

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public CompanyContactPerson getCompanyContactPerson() {
        return companyContactPerson;
    }

    public void setCompanyContactPerson(CompanyContactPerson companyContactPerson) {
        this.companyContactPerson = companyContactPerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TraceabilityAuditDTO traceabilityAuditDTO = (TraceabilityAuditDTO) o;
        if(traceabilityAuditDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), traceabilityAuditDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TraceabilityAuditDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            "}";
    }
}
