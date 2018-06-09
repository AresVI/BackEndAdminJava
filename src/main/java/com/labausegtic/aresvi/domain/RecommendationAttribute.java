package com.labausegtic.aresvi.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A RecommendationAttribute.
 */
@Entity
@Table(name = "recommendation_attribute")
public class RecommendationAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @NotNull
    private Weighting weighting;

    @ManyToOne(optional = false)
    @NotNull
    private Recommendation recommendation;

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

    public RecommendationAttribute name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weighting getWeighting() {
        return weighting;
    }

    public RecommendationAttribute weighting(Weighting weighting) {
        this.weighting = weighting;
        return this;
    }

    public void setWeighting(Weighting weighting) {
        this.weighting = weighting;
    }

    public Recommendation getRecommendation() {
        return recommendation;
    }

    public RecommendationAttribute recommendation(Recommendation recommendation) {
        this.recommendation = recommendation;
        return this;
    }

    public void setRecommendation(Recommendation recommendation) {
        this.recommendation = recommendation;
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
        RecommendationAttribute recommendationAttribute = (RecommendationAttribute) o;
        if (recommendationAttribute.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), recommendationAttribute.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RecommendationAttribute{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
