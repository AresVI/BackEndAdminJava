package com.labausegtic.aresvi.service.dto;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the Product entity.
 */
public class CompanyProductDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private Set<ProductTypeProductDTO> ProductTypes;

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

    public Set<ProductTypeProductDTO> getProductTypes() {
        return ProductTypes;
    }

    public void setProductTypes(Set<ProductTypeProductDTO> productTypes) {
        ProductTypes = productTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyProductDTO that = (CompanyProductDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ProductTypes);
    }
}
