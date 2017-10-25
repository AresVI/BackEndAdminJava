package com.labausegtic.aresvi.service.dto;


import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Recommendation entity.
 */
public class RecommendationDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private Integer qualification;

    private Instant creationDate;

    private Long traceabilityAuditId;

    private Set<AuditProcessRecommendationDTO> auditProcessRecommendationSet;

    private Long auditorId;

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

    public Integer getQualification() {
        return qualification;
    }

    public void setQualification(Integer qualification) {
        this.qualification = qualification;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Long getTraceabilityAuditId() {
        return traceabilityAuditId;
    }

    public void setTraceabilityAuditId(Long traceabilityAuditId) {
        this.traceabilityAuditId = traceabilityAuditId;
    }

    public Set<AuditProcessRecommendationDTO> getAuditProcessRecommendationSet() {
        return auditProcessRecommendationSet;
    }

    public void setAuditProcessRecommendationSet(Set<AuditProcessRecommendationDTO> auditProcessRecommendationSet) {
        this.auditProcessRecommendationSet = auditProcessRecommendationSet;
    }

    public Long getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Long auditorId) {
        this.auditorId = auditorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RecommendationDTO recommendationDTO = (RecommendationDTO) o;
        if(recommendationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), recommendationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RecommendationDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", qualification='" + getQualification() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            "}";
    }
}
