package com.labausegtic.aresvi.service.dto;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

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

    private CompanyContactPersonDTO companyContactPerson;

    private Long companyContactPersonId;

    private String category;

    private String status;

    private Set<RecommendationDTO> recommendationSet;

    private Set<AuditProcessDTO> auditProcesses;

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

    public CompanyContactPersonDTO getCompanyContactPerson() {
        return companyContactPerson;
    }

    public void setCompanyContactPerson(CompanyContactPersonDTO companyContactPerson) {
        this.companyContactPerson = companyContactPerson;
    }

    public Long getCompanyContactPersonId() {
        return companyContactPersonId;
    }

    public void setCompanyContactPersonId(Long companyContactPersonId) {
        this.companyContactPersonId = companyContactPersonId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<RecommendationDTO> getRecommendationSet() {
        return recommendationSet;
    }

    public void setRecommendationDTOSet(Set<RecommendationDTO> recommendationSet) {
        this.recommendationSet = recommendationSet;
    }

    public void setRecommendationSet(Set<RecommendationDTO> recommendationSet) {
        this.recommendationSet = recommendationSet;
    }

    public Set<AuditProcessDTO> getAuditProcesses() {
        return auditProcesses;
    }

    public void setAuditProcesses(Set<AuditProcessDTO> auditProcesses) {
        this.auditProcesses = auditProcesses;
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
            ", companyContactPersonId='" + getCompanyContactPersonId() + "'" +
            "}";
    }
}
