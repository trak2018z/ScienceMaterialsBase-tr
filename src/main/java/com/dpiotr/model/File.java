package com.dpiotr.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dpiotr on 29.10.17.
 */
@Entity
@Table(name = "file")
public class File implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "url", nullable = false)
    private String url;

    protected File(){
    }

    public File(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }
}
