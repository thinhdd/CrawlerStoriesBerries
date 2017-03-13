package com.crawler.storiesberries.dto;

import java.util.List;

/**
 * Created by android on 3/5/2017.
 */
public class StoryDTO {
    String title;
    String shortDes;
    String thumbnail;
    String imageUrls;

    public StoryDTO(String title, String shortDes, String thumbnail, String imageUrls) {
        this.title = title;
        this.shortDes = shortDes;
        this.thumbnail = thumbnail;
        this.imageUrls = imageUrls;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDes() {
        return shortDes;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }
}
