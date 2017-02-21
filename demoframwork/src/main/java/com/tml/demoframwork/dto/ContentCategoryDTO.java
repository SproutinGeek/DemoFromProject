package com.tml.demoframwork.dto;

import java.io.Serializable;

/**
 * Data transfer object for ContentCategory
 * Created by 830393 on 7/14/2015.
 */
public class ContentCategoryDTO implements Serializable {
    private int contentCategoryID;
    private String contentCategory;

    public ContentCategoryDTO() {

    }

    public ContentCategoryDTO(String contentCategory) {
        this.contentCategory = contentCategory;
    }

    public String getContentCategory() {
        return contentCategory;
    }

    public void setContentCategory(String contentCategory) {
        this.contentCategory = contentCategory;
    }

    public int getContentCategoryID() {
        return contentCategoryID;
    }

    public void setContentCategoryID(int contentCategoryID) {
        this.contentCategoryID = contentCategoryID;
    }
}

