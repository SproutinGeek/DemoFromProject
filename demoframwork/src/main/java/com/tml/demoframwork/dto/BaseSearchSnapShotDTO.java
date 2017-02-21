package com.tml.demoframwork.dto;

import java.io.Serializable;

/**
 * Data transfer object for BaseSearchSnapShot
 * Created by 1021354 on 13-07-2015.
 */
public class BaseSearchSnapShotDTO implements Serializable {

    private int content_Id;
    private int contentCategory_Id;

    //Feature content in the json
    private String contentHeading;
    private String contentShortDescription;
    private String contentXML_Path;
    private String imageThumbnailPath;
    private boolean isBookmarked;

    public int getContentCategory_Id() {
        return contentCategory_Id;
    }

    public void setContentCategory_Id(int contentCategory_Id) {
        this.contentCategory_Id = contentCategory_Id;
    }

    public String getContentHeading() {
        return contentHeading;
    }

    public void setContentHeading(String contentHeading) {
        this.contentHeading = contentHeading;
    }

    public String getContentShortDescription() {
        return contentShortDescription;
    }

    public void setContentShortDescription(String contentShortDescription) {
        this.contentShortDescription = contentShortDescription;
    }

    public String getContentXML_Path() {
        return contentXML_Path;
    }

    public void setContentXML_Path(String contentXML_Path) {
        this.contentXML_Path = contentXML_Path;
    }

    public int getContent_Id() {
        return content_Id;
    }

    public void setContent_Id(int content_Id) {
        this.content_Id = content_Id;
    }

    public String getImageThumbnailPath() {
        return imageThumbnailPath;
    }

    public void setImageThumbnailPath(String imageThumbnailPath) {
        this.imageThumbnailPath = imageThumbnailPath;
    }

    public boolean isBookmarked() {
        return isBookmarked;
    }

    public void setIsBookmarked(boolean isBookmarked) {
        this.isBookmarked = isBookmarked;
    }
}
