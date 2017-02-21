package com.tml.demoframwork.dto;

import java.io.Serializable;

/**
 * Sub title DTO contents
 */
public class SubTitleDTO implements Serializable {
    private ImageDTO contentImage;
    private String titleValue;

    public String getTitleValue() {
        return titleValue;
    }

    public void setTitleValue(String titleValue) {
        this.titleValue = titleValue;
    }

    public ImageDTO getContentImage() {
        return contentImage;
    }

    public void setContentImage(ImageDTO contentImage) {
        this.contentImage = contentImage;
    }
}
