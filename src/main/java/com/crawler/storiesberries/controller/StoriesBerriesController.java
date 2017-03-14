package com.crawler.storiesberries.controller;

import com.crawler.storiesberries.dto.StoriesDTOs;
import com.crawler.storiesberries.service.IStoriesBerriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

/**
 * Created by android on 3/4/2017.
 */
@Controller
public class StoriesBerriesController {

    @Autowired
    IStoriesBerriesService storiesBerriesService;

    @GetMapping("/crawlerAll")
    public void crawlerAllData() throws IOException, InterruptedException {
        storiesBerriesService.crawlerAll();
    }

    @PostMapping("/crawlerOnlyText")
    public void crawlerOnlyImage(@RequestBody StoriesDTOs storiesDTOs) throws IOException {
        storiesBerriesService.crawlerOnlyImage(storiesDTOs);
    }
}
