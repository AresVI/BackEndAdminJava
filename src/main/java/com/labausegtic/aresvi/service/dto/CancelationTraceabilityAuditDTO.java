package com.labausegtic.aresvi.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the CancelationTraceabilityAudit entity.
 */
public class CancelationTraceabilityAuditDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 1000)
    private String justification;

    private Long traceabilityAuditId;

    private String traceabilityAuditName;

    private Long userId;

    private String userLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public Long getTraceabilityAuditId() {
        return traceabilityAuditId;
    }

    public void setTraceabilityAuditId(Long traceabilityAuditId) {
        this.traceabilityAuditId = traceabilityAuditId;
    }

    public String getTraceabilityAuditName() {
        return traceabilityAuditName;
    }

    public void setTraceabilityAuditName(String traceabilityAuditName) {
        this.traceabilityAuditName = traceabilityAuditName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CancelationTraceabilityAuditDTO cancelationTraceabilityAuditDTO = (CancelationTraceabilityAuditDTO) o;
        if(cancelationTraceabilityAuditDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cancelationTraceabilityAuditDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CancelationTraceabilityAuditDTO{" +
            "id=" + getId() +
            ", justification='" + getJustification() + "'" +
            "}";
    }
}
