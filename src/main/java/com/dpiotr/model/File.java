package com.dpiotr.model;

import com.dpiotr.common.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by dpiotr on 29.10.17.
 */
@Entity
@Table(name = "file")
public class File implements Serializable {

    @Id
    @JsonView(View.withId.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @JsonView(View.withId.class)
    @NotNull
    @JsonIgnore
    private String name;

    @Column(name = "url")
    @JsonView(View.withId.class)
    @NotNull
    private String url;

    @Column(name = "date_added")
    @JsonView(View.withId.class)
    private Date dateAdded;

    @JsonView(View.withId.class)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "system_user_id")
    @JsonIgnore
    private SystemUser systemUser;

    public File(String name, String url) {
        this.name = name;
        this.url = url;
    }

    protected File() {
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }
}
