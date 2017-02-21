package com.tml.demoframwork.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Data transfer object for the Title.
 */
public class TitleDTO implements Serializable {
    private String titleValue;
    private List<SubTitleDTO> subTitleDTOs;
    private String introduction;

    public String getTitleValue() {
        return titleValue;
    }

    public void setTitleValue(String titleValue) {
        this.titleValue = titleValue;
    }

    public List<SubTitleDTO> getSubTitleDTOs() {
        return subTitleDTOs;
    }

    public void setSubTitleDTOs(List<SubTitleDTO> subTitleDTOs) {
        this.subTitleDTOs = subTitleDTOs;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
