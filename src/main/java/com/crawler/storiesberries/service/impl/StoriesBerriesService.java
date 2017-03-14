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
import org.jsoup.nodes.Node;
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
    public void crawlerAll() throws IOException, InterruptedException {
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("h2.title a");
        Map<String, Story> storyMap = new HashMap<String, Story>();
        List<Story> stories = storyRepository.getAllStories();
        for (Story story : stories) {
            storyMap.put(story.getUrl(), story);
        }
        int j = 0;
        for (Element element : links) {

            j += 1;
            String subUrl = element.attr("href");
            String type = element.childNode(0).toString();
            boolean tryCategory;
            do {
                try {
                    Document subDoc = Jsoup.connect(subUrl).get();
                    Category category = categoryRepository.findByName(type);
                    if (category == null) {
                        category = new Category();
                        category.setName(type);
                        categoryRepository.save(category);
                    }
                    Elements pageElements = subDoc.select("a.page-numbers");
                    int countPage = Integer.parseInt(pageElements.get(pageElements.size() - 2).childNode(0).toString());

                    for (int page = 1; page <= countPage; page++) {
                        boolean retryPage;
                        do {
                            String pageUrl = subUrl + "page/" + page;

                            System.out.println("page url " + pageUrl);
                            try {
                                Document pageDoc = Jsoup.connect(pageUrl).get();
                                Elements elements = pageDoc.select("div.entry-image-inner a[href]");
                                for (Element elementInPage : elements) {
                                    String href = elementInPage.attr("href");
                                    System.out.println("story href " + href);
                                    String title = elementInPage.attr("title");
                                    String thumbnail = elementInPage.select("img").get(0).attr("src");
                                    Story story = new Story();
                                    if (storyMap.containsKey(href)) {
                                        story = storyMap.get(href);
                                        if (story.getCategories().contains(category)) {
                                            continue;
                                        }
                                        story.getCategories().add(category);
                                        story = storyRepository.save(story);
                                        storyMap.put(href, story);
                                        continue;
                                    }
                                    boolean tryStory;
                                    do {
                                        try {
                                            Document detailStoryDoc = Jsoup.connect(href).get();
                                            String shortDes = detailStoryDoc.select("div.manual-excerpt").get(0).childNode(0).toString();
                                            Elements elementByRegex = detailStoryDoc.select("div[itemprop=articleBody] p");
                                            story.setUrl(href);
                                            story.setName(title);
                                            story.setThumbnail(thumbnail);
                                            story.setShortDes(shortDes);
                                            if (story.getCategories() == null) {
                                                story.setCategories(new ArrayList<Category>());
                                            }
                                            story.getCategories().add(category);
                                            StringBuilder content = new StringBuilder();
                                            for (Element dataContent : elementByRegex) {
                                                Elements imgs = dataContent.select("img");
                                                if (imgs.size() == 0) {
                                                    if (dataContent.childNodes().size() != 0) {
                                                        for (Node node : dataContent.childNodes()) {
                                                            String str = node.toString();
                                                            content.append(str);
                                                        }
                                                    }
                                                } else {
                                                    content.append(imgs.get(0).attr("src"));
                                                    content.append("@@@");
                                                }
                                            }
                                            story.setContent(content.toString());
                                            story = storyRepository.save(story);
                                            storyMap.put(title, story);
                                            tryStory = false;
                                        } catch (Exception e) {
                                            tryStory = true;
                                            Thread.sleep(10000);
                                        }
                                    } while (tryStory);
                                }
                                retryPage = false;
                            } catch (Exception e) {
                                retryPage = true;
                                Thread.sleep(10000);
                            }
                        } while (retryPage);
                    }
                    tryCategory = false;
                } catch (Exception e) {
                    tryCategory = true;
                    Thread.sleep(10000);
                }
            } while (tryCategory);
        }
        Collection<Story> values = storyMap.values();
        storyRepository.save(values);
    }

    @Override
    public void crawlerOnlyImage(@RequestBody StoriesDTOs storiesDTOs) throws IOException {

    }
}
