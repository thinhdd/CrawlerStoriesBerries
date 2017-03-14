package com.crawler.storiesberries.repository;

import com.crawler.storiesberries.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by android on 3/7/2017.
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query("select ca from Category as ca where ca.name=:name")
    Category findByName(@Param("name") String type);
}
