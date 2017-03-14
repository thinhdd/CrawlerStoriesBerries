package com.crawler.storiesberries.repository;

import com.crawler.storiesberries.model.Story;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by android on 3/13/2017.
 */
public interface StoryRepository extends CrudRepository<Story, Long> {
    @Query("select st from Story as st")
    public List<Story> getAllStories();
}
