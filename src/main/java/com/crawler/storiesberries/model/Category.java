package com.crawler.storiesberries.model;


import javax.persistence.*;
import java.util.List;

/**
 * Created by android on 3/5/2017.
 */
@Entity
@Table(name = "category")
public class Category{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    private String name;
    @ManyToMany(mappedBy = "categories")
    private List<Story> stories;

    public Category() {
    }

    public Category(String name, List<Story> stories) {
        this.name = name;
        this.stories = stories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }
}
