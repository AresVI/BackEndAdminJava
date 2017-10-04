package com.labausegtic.aresvi.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CategoryAttributeRecommendation.
 */
@Entity
@Table(name = "category_attr_recommendation")
public class CategoryAttributeRecommendation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    private AuditTaskRecommendation auditTaskRecom;

    @ManyToOne
    private CategoryAttribute categoryAttribute;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public CategoryAttributeRecommendation description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AuditTaskRecommendation getAuditTaskRecom() {
        return auditTaskRecom;
    }

    public CategoryAttributeRecommendation auditTaskRecom(AuditTaskRecommendation auditTaskRecommendation) {
        this.auditTaskRecom = auditTaskRecommendation;
        return this;
    }

    public void setAuditTaskRecom(AuditTaskRecommendation auditTaskRecommendation) {
        this.auditTaskRecom = auditTaskRecommendation;
    }

    public CategoryAttribute getCategoryAttribute() {
        return categoryAttribute;
    }

    public CategoryAttributeRecommendation categoryAttribute(CategoryAttribute categoryAttribute) {
        this.categoryAttribute = categoryAttribute;
        return this;
    }

    public void setCategoryAttribute(CategoryAttribute categoryAttribute) {
        this.categoryAttribute = categoryAttribute;
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
        CategoryAttributeRecommendation categoryAttributeRecommendation = (CategoryAttributeRecommendation) o;
        if (categoryAttributeRecommendation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), categoryAttributeRecommendation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CategoryAttributeRecommendation{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
