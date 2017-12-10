package com.labausegtic.aresvi.service.dto;

public class RecommendationParameterNextCategoryDTO {

    private String category;
    private double percentageNotRequired;
    private double percentageLevel1;
    private double percentageLevel2;
    private double percentageLevel3;
    private double percentageLevel4;
    private double percentageLevel5;

    public RecommendationParameterNextCategoryDTO() {
    }

    public RecommendationParameterNextCategoryDTO(String category, double percentageNotRequired, double percentageLevel1, double percentageLevel2, double percentageLevel3, double percentageLevel4, double percentageLevel5) {
        this.category = category;
        this.percentageNotRequired = percentageNotRequired;
        this.percentageLevel1 = percentageLevel1;
        this.percentageLevel2 = percentageLevel2;
        this.percentageLevel3 = percentageLevel3;
        this.percentageLevel4 = percentageLevel4;
        this.percentageLevel5 = percentageLevel5;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPercentageNotRequired() {
        return percentageNotRequired;
    }

    public void setPercentageNotRequired(double percentageNotRequired) {
        this.percentageNotRequired = percentageNotRequired;
    }

    public double getPercentageLevel1() {
        return percentageLevel1;
    }

    public void setPercentageLevel1(double percentageLevel1) {
        this.percentageLevel1 = percentageLevel1;
    }

    public double getPercentageLevel2() {
        return percentageLevel2;
    }

    public void setPercentageLevel2(double percentageLevel2) {
        this.percentageLevel2 = percentageLevel2;
    }

    public double getPercentageLevel3() {
        return percentageLevel3;
    }

    public void setPercentageLevel3(double percentageLevel3) {
        this.percentageLevel3 = percentageLevel3;
    }

    public double getPercentageLevel4() {
        return percentageLevel4;
    }

    public void setPercentageLevel4(double percentageLevel4) {
        this.percentageLevel4 = percentageLevel4;
    }

    public double getPercentageLevel5() {
        return percentageLevel5;
    }

    public void setPercentageLevel5(double percentageLevel5) {
        this.percentageLevel5 = percentageLevel5;
    }

    @Override
    public String toString() {
        return "RecommendationParameterDTO{" +
                "category='" + category + '\'' +
                ", percentageNotRequired=" + percentageNotRequired +
                ", percentageLevel1=" + percentageLevel1 +
                ", percentageLevel2=" + percentageLevel2 +
                ", percentageLevel3=" + percentageLevel3 +
                ", percentageLevel4=" + percentageLevel4 +
                ", percentageLevel5=" + percentageLevel5 +
                '}';
    }
}
