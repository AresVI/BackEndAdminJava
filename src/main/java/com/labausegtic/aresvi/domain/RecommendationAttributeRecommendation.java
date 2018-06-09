package com.labausegtic.aresvi.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A RecommendationAttributeRecommendation.
 */
@Entity
@Table(name = "recommendation_attribute_recom")
public class RecommendationAttributeRecommendation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "implemented")
    private Boolean implemented;

    @ManyToOne(optional = false)
    @NotNull
    private RecommendationAttribute recommendationAttribute;

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

    public Boolean isImplemented() {
        return implemented;
    }

    public RecommendationAttributeRecommendation implemented(Boolean implemented) {
        this.implemented = implemented;
        return this;
    }

    public void setImplemented(Boolean implemented) {
        this.implemented = implemented;
    }

    public RecommendationAttribute getRecommendationAttribute() {
        return recommendationAttribute;
    }

    public RecommendationAttributeRecommendation recommendationAttribute(RecommendationAttribute recommendationAttribute) {
        this.recommendationAttribute = recommendationAttribute;
        return this;
    }

    public void setRecommendationAttribute(RecommendationAttribute recommendationAttribute) {
        this.recommendationAttribute = recommendationAttribute;
    }

    public Recommendation getRecommendation() {
        return recommendation;
    }

    public RecommendationAttributeRecommendation recommendation(Recommendation recommendation) {
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
        RecommendationAttributeRecommendation recommendationAttributeRecommendation = (RecommendationAttributeRecommendation) o;
        if (recommendationAttributeRecommendation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), recommendationAttributeRecommendation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RecommendationAttributeRecommendation{" +
            "id=" + getId() +
            ", implemented='" + isImplemented() + "'" +
            "}";
    }
}
