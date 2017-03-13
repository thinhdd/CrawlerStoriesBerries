package com.crawler.storiesberries.dto;

import com.crawler.storiesberries.TypeStory;

/**
 * Created by android on 3/4/2017.
 */
public class StoriesDTO {
    private String url;
    private TypeStory typeView;
    private String type;

    public StoriesDTO() {
    }

    public StoriesDTO(String url, TypeStory typeView, String type) {
        this.url = url;
        this.typeView = typeView;
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TypeStory getTypeView() {
        return typeView;
    }

    public void setTypeView(TypeStory typeView) {
        this.typeView = typeView;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
