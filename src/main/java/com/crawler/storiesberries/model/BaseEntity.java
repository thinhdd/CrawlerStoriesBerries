package com.crawler.storiesberries.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by android on 3/5/2017.
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
