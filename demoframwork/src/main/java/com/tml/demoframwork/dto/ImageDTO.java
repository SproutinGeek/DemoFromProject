package com.tml.demoframwork.dto;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Data transfer object for Image
 * Created by 1021354 on 09-07-2015.
 */
public class ImageDTO implements Serializable {
    @NonNull
    private String filePath;
    private String imageCaption;
    private String imageDescription;
    private String notes;
    private String warnings;
    private String maintenanceTips;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getImageCaption() {
        return imageCaption;
    }

    public void setImageCaption(String imageCaption) {
        this.imageCaption = imageCaption;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getWarnings() {
        return warnings;
    }

    public void setWarnings(String warnings) {
        this.warnings = warnings;
    }

    public String getMaintenanceTips() {
        return maintenanceTips;
    }

    public void setMaintenanceTips(String maintenanceTips) {
        this.maintenanceTips = maintenanceTips;
    }
}
