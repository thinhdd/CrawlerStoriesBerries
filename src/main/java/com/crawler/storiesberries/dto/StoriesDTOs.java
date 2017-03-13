package com.crawler.storiesberries.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 3/4/2017.
 */
public class StoriesDTOs {
    List<StoriesDTO> storiesDTOs = new ArrayList<StoriesDTO>();

    public List<StoriesDTO> getStoriesDTOs() {
        return storiesDTOs;
    }

    public void setStoriesDTOs(List<StoriesDTO> storiesDTOs) {
        this.storiesDTOs = storiesDTOs;
    }
}
