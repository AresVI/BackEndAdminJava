package com.labausegtic.aresvi.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A AttributeRecommendation.
 */
@Entity
@Table(name = "attribute_recommendation")
public class AttributeRecommendation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "implemented")
    private boolean implemented;

    @ManyToOne
    private CategoryAttrRecommendation categoryAttrRecom;

    @ManyToOne
    private Attribute attribute;

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

    public AttributeRecommendation description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isImplemented() {
        return implemented;
    }

    public void setImplemented(boolean implemented) {
        this.implemented = implemented;
    }

    public CategoryAttrRecommendation getCategoryAttRecom() {
        return categoryAttrRecom;
    }

    public AttributeRecommendation categoryAttrRecom(CategoryAttrRecommendation categoryAttrRecommendation) {
        this.categoryAttrRecom = categoryAttrRecommendation;
        return this;
    }

    public void setCategoryAttRecom(CategoryAttrRecommendation categoryAttrRecommendation) {
        this.categoryAttrRecom = categoryAttrRecommendation;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public AttributeRecommendation attribute(Attribute attribute) {
        this.attribute = attribute;
        return this;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
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
        AttributeRecommendation attributeRecommendation = (AttributeRecommendation) o;
        if (attributeRecommendation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), attributeRecommendation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AttributeRecommendation{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
