package com.labausegtic.aresvi.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the ProductType entity.
 */
public class ProductTypeDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private Set<AuditProcessDTO> auditProcesses = new HashSet<>();

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

        ProductTypeDTO productTypeDTO = (ProductTypeDTO) o;
        if(productTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductTypeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
