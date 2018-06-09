package com.labausegtic.aresvi.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the RecommendationAttributeRecommendation entity.
 */
public class RecommendationAttributeRecommendationDTO implements Serializable {

    private Long id;

    private Boolean implemented;

    private Long recommendationAttributeId;

    private String recommendationAttributeName;

    private Long recommendationId;

    private String recommendationName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isImplemented() {
        return implemented;
    }

    public void setImplemented(Boolean implemented) {
        this.implemented = implemented;
    }

    public Long getRecommendationAttributeId() {
        return recommendationAttributeId;
    }

    public void setRecommendationAttributeId(Long recommendationAttributeId) {
        this.recommendationAttributeId = recommendationAttributeId;
    }

    public String getRecommendationAttributeName() {
        return recommendationAttributeName;
    }

    public void setRecommendationAttributeName(String recommendationAttributeName) {
        this.recommendationAttributeName = recommendationAttributeName;
    }

    public Long getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(Long recommendationId) {
        this.recommendationId = recommendationId;
    }

    public String getRecommendationName() {
        return recommendationName;
    }

    public void setRecommendationName(String recommendationName) {
        this.recommendationName = recommendationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RecommendationAttributeRecommendationDTO recommendationAttributeRecommendationDTO = (RecommendationAttributeRecommendationDTO) o;
        if(recommendationAttributeRecommendationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), recommendationAttributeRecommendationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RecommendationAttributeRecommendationDTO{" +
            "id=" + getId() +
            ", implemented='" + isImplemented() + "'" +
            "}";
    }
}
