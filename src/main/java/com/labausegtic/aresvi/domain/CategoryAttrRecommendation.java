package com.labausegtic.aresvi.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CategoryAttrRecommendation.
 */
@Entity
@Table(name = "category_attr_recommendation")
public class CategoryAttrRecommendation implements Serializable {

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

    public CategoryAttrRecommendation description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AuditTaskRecommendation getAuditTaskRecom() {
        return auditTaskRecom;
    }

    public CategoryAttrRecommendation auditTaskRecom(AuditTaskRecommendation auditTaskRecommendation) {
        this.auditTaskRecom = auditTaskRecommendation;
        return this;
    }

    public void setAuditTaskRecom(AuditTaskRecommendation auditTaskRecommendation) {
        this.auditTaskRecom = auditTaskRecommendation;
    }

    public CategoryAttribute getCategoryAttribute() {
        return categoryAttribute;
    }

    public CategoryAttrRecommendation categoryAttribute(CategoryAttribute categoryAttribute) {
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
        CategoryAttrRecommendation categoryAttrRecommendation = (CategoryAttrRecommendation) o;
        if (categoryAttrRecommendation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), categoryAttrRecommendation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CategoryAttrRecommendation{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
