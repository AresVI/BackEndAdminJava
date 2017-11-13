package com.labausegtic.aresvi.service.dto;

import com.labausegtic.aresvi.domain.Attribute;
import com.labausegtic.aresvi.domain.AttributeRecommendation;

import java.io.Serializable;

/**
 * A DTO for the Attribute entity.
 */
public class ComparativeAttributeRecommendationDTO implements Serializable {

    private Attribute attribute;

    private int difference;

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }
}
