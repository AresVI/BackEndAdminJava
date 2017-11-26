package com.labausegtic.aresvi.service.dto;


import com.labausegtic.aresvi.domain.TraceabilityAudit;
import com.labausegtic.aresvi.domain.User;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CancellationTraceabilityAudit entity.
 */
public class CancellationTraceabilityAuditDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 1000)
    private String justification;

    private Long traceabilityAuditId;

    private String traceabilityAuditName;

    private TraceabilityAudit traceabilityAudit;

    private Long userId;

    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TraceabilityAudit getTraceabilityAudit() {
        return traceabilityAudit;
    }

    public void setTraceabilityAudit(TraceabilityAudit traceabilityAudit) {
        this.traceabilityAudit = traceabilityAudit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CancellationTraceabilityAuditDTO cancellationTraceabilityAuditDTO = (CancellationTraceabilityAuditDTO) o;
        if(cancellationTraceabilityAuditDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cancellationTraceabilityAuditDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CancellationTraceabilityAuditDTO{" +
            "id=" + getId() +
            ", justification='" + getJustification() + "'" +
            "}";
    }
}
