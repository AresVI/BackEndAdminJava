package com.labausegtic.aresvi.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the RecommendationAttribute entity.
 */
public class RecommendationAttributeDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private Long weightingId;

    private Long recommendationId;

    private String recommendationName;

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

    public Long getWeightingId() {
        return weightingId;
    }

    public void setWeightingId(Long weightingId) {
        this.weightingId = weightingId;
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

        RecommendationAttributeDTO recommendationAttributeDTO = (RecommendationAttributeDTO) o;
        if(recommendationAttributeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), recommendationAttributeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RecommendationAttributeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
