package com.dpiotr.model.viewmodels;

import java.util.Date;

/**
 * Created by dpiotr on 01.12.17.
 */
public class SubjectViewModel {

    private String name;
    private String description;
    private Date lastModified;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
