package com.labausegtic.aresvi.service.dto;

import com.labausegtic.aresvi.domain.Attribute;
import com.labausegtic.aresvi.domain.CategoryAttribute;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the Attribute entity.
 */
public class ComparativeCatAttrRecommendationDTO implements Serializable {

    private CategoryAttribute categoryAttribute;

    private List<ComparativeAttributeRecommendationDTO> comparativeAttributeRecommendationList;

    public CategoryAttribute getCategoryAttribute() {
        return categoryAttribute;
    }

    public void setCategoryAttribute(CategoryAttribute categoryAttribute) {
        this.categoryAttribute = categoryAttribute;
    }

    public List<ComparativeAttributeRecommendationDTO> getComparativeAttributeRecommendationList() {
        return comparativeAttributeRecommendationList;
    }

    public void setComparativeAttributeRecommendationList(List<ComparativeAttributeRecommendationDTO> comparativeAttributeRecommendationList) {
        this.comparativeAttributeRecommendationList = comparativeAttributeRecommendationList;
    }
}
