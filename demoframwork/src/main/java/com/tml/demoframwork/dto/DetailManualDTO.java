package com.tml.demoframwork.dto;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Data transfer object for DetailManual
 * Created by 1021354 on 22-06-2015.
 */
public class DetailManualDTO extends BaseSearchSnapShotDTO {

    private String maintenenceTips;
    @NonNull
    private ContentCategoryDTO contentCategoryDTO;
    @NonNull
    private List<TitleDTO> titleDTOs;


    public ContentCategoryDTO getContentCategoryDTO() {
        return contentCategoryDTO;
    }

    public void setContentCategoryDTO(ContentCategoryDTO contentCategoryDTO) {
        this.contentCategoryDTO = contentCategoryDTO;
    }

    public List<TitleDTO> getTitleDTOs() {
        return titleDTOs;
    }

    public void setTitleDTOs(List<TitleDTO> titleDTOs) {
        this.titleDTOs = titleDTOs;
    }

    public String getMaintenenceTips() {
        return maintenenceTips;
    }

    public void setMaintenenceTips(String maintenenceTips) {
        this.maintenenceTips = maintenenceTips;
    }
}
