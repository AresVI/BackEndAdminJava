package com.labausegtic.aresvi.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Attribute.
 */
@Entity
@Table(name = "attribute")
public class Attribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "required", nullable = false)
    private Boolean required;

    @OneToOne
    @JoinColumn(unique = true)
    private Weighting weighting;

    @ManyToOne
    private CategoryAttribute categoryAttribute;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Attribute name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isRequired() {
        return required;
    }

    public Attribute required(Boolean required) {
        this.required = required;
        return this;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Weighting getWeighting() {
        return weighting;
    }

    public Attribute weighting(Weighting weighting) {
        this.weighting = weighting;
        return this;
    }

    public void setWeighting(Weighting weighting) {
        this.weighting = weighting;
    }

    public CategoryAttribute getCategoryAttribute() {
        return categoryAttribute;
    }

    public Attribute categoryAttribute(CategoryAttribute categoryAttribute) {
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
        Attribute attribute = (Attribute) o;
        if (attribute.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), attribute.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Attribute{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", required='" + isRequired() + "'" +
            "}";
    }
}
