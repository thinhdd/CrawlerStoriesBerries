package com.crawler.storiesberries.service;

import com.crawler.storiesberries.dto.StoriesDTOs;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

/**
 * Created by android on 3/4/2017.
 */
public interface IStoriesBerriesService {
    public void crawlerAll() throws IOException, InterruptedException;


    void crawlerOnlyImage(StoriesDTOs storiesDTOs) throws IOException;
}
