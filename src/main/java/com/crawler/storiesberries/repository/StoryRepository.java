package com.crawler.storiesberries.repository;

import com.crawler.storiesberries.model.Story;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by android on 3/13/2017.
 */
public interface StoryRepository extends CrudRepository<Story, Long> {
}
