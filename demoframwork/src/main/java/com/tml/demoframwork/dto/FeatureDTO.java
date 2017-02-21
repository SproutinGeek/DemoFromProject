package com.tml.demoframwork.dto;

import java.io.Serializable;

/**
 * Data transfer object for Features
 * Created by 677271 on 8/3/2015.
 */
public class FeatureDTO implements Serializable {

    private int featureID;
    private String feature;

    public FeatureDTO() {

    }

    public FeatureDTO(String feature) {
        this.feature = feature;
    }

    public int getFeatureID() {
        return featureID;
    }

    public void setFeatureID(int featureID) {
        this.featureID = featureID;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
}

