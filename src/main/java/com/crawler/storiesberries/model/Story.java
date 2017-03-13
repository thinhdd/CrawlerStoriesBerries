package com.crawler.storiesberries.model;

import com.crawler.storiesberries.TypeStory;

import javax.persistence.*;
import java.util.List;

/**
 * Created by android on 3/5/2017.
 */
@Entity
@Table(name = "story")
public class Story{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    private String name;
    private String shortDes;
    private String thumbnail;
    private String content;
    private TypeStory typeStory;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "story_category", joinColumns = @JoinColumn(name = "story_id", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private List<Category> categories;

    public Story() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDes() {
        return shortDes;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TypeStory getTypeStory() {
        return typeStory;
    }

    public void setTypeStory(TypeStory typeStory) {
        this.typeStory = typeStory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
