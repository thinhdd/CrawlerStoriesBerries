package com.crawler.storiesberries.service.impl;

import com.crawler.storiesberries.TypeStory;
import com.crawler.storiesberries.dto.StoriesDTOs;
import com.crawler.storiesberries.model.Category;
import com.crawler.storiesberries.model.Story;
import com.crawler.storiesberries.repository.CategoryRepository;
import com.crawler.storiesberries.repository.StoryRepository;
import com.crawler.storiesberries.service.IStoriesBerriesService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.*;

/**
 * Created by android on 3/4/2017.
 */
@Component
public class StoriesBerriesService implements IStoriesBerriesService {

    public static final String url = "http://storyberries.com/";
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    StoryRepository storyRepository;

    @Override
    public void crawlerAll() throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("h2.title a");
        Map<String, Story> storyMap= new HashMap<String, Story>();
        int j = 0;
        for (Element element : links) {

            j += 1;
            String subUrl = element.attr("href");
            String type = element.childNode(0).toString();
            Document subDoc = Jsoup.connect(subUrl).get();
            Category category = new Category();
            category.setName(type);
            categoryRepository.save(category);
            Elements pageElements = subDoc.select("a.page-numbers");
            int countPage = Integer.parseInt(pageElements.get(pageElements.size() - 2).childNode(0).toString());

            for (int page = 1; page < countPage; page++) {
                String pageUrl = subUrl + "page/" + page;
                System.out.println("page url " + pageUrl);
                Document pageDoc = Jsoup.connect(pageUrl).get();
                Elements elements = pageDoc.select("div.entry-image-inner a[href]");
                for (Element elementInPage : elements) {
                    String href = elementInPage.attr("href");
                    System.out.println("story href " + href);
                    String title = elementInPage.attr("title");
                    String thumbnail = elementInPage.select("img").get(0).attr("src");
                    Document detailStoryDoc = Jsoup.connect(href).get();
                    String shortDes = detailStoryDoc.select("div.manual-excerpt").get(0).childNode(0).toString();
                    Elements elementByRegex = detailStoryDoc.select("div[itemprop=articleBody] p");
                    Story story = new Story();
                    if(storyMap.containsKey(title))
                    {
                        story = storyMap.get(title);
                        story.getCategories().add(category);
                        continue;
                    }
                    story.setName(title);
                    story.setThumbnail(thumbnail);
                    story.setShortDes(shortDes);
                    if(story.getCategories()==null)
                    {
                        story.setCategories(new ArrayList<Category>());
                    }
                    story.getCategories().add(category);
                    storyMap.put(title, story);
                    StringBuilder content = new StringBuilder();
                    for (Element dataContent : elementByRegex) {
                        Elements imgs = dataContent.select("img");
                        if (imgs.size() == 0) {
                            if(dataContent.childNodes().size()!=0)
                            {
                                String str = dataContent.childNode(0).toString();
                                content.append(str);
                            }
                        } else {
                            content.append(imgs.get(0).attr("src"));
                            content.append("@@@");
                        }
                    }
                    story.setContent(content.toString());
                }
            }
        }
        Collection<Story> values = storyMap.values();
        storyRepository.save(values);
    }

    public void crawlerDetailStory(String url, String type) {

    }

    @Override
    public void crawlerOnlyImage(@RequestBody StoriesDTOs storiesDTOs) throws IOException {

    }
}
