package com.labausegtic.aresvi.service.dto;

import java.io.Serializable;

public class InferenceParameterDTO implements Serializable{

    private double percentageNotRequired;

    private double percentageLevel1;
    private double percentageLevel2;
    private double percentageLevel3;
    private double percentageLevel4;
    private double percentageLevel5;

    private int levelComputerization;

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

    public int getLevelComputerization() {
        return levelComputerization;
    }

    public void setLevelComputerization(int levelComputerization) {
        this.levelComputerization = levelComputerization;
    }

    @Override
    public String toString() {
        return "InferenceParameterDTO{" +
            "percentageNotRequired=" + percentageNotRequired +
            ", percentageLevel1=" + percentageLevel1 +
            ", percentageLevel2=" + percentageLevel2 +
            ", percentageLevel3=" + percentageLevel3 +
            ", percentageLevel4=" + percentageLevel4 +
            ", percentageLevel5=" + percentageLevel5 +
            ", levelComputerization=" + levelComputerization +
            '}';
    }
}
