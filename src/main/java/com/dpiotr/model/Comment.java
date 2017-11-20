package com.dpiotr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by dpiotr on 29.10.17.
 */
@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "comment_content")
    private String content;
    @ManyToOne
    @JoinColumn(name = "system_user_id", nullable = false)
    private SystemUser systemUser;
    //@Column(name = "file_id")
    //private File file;

    public Comment(String content, SystemUser systemUser, File file) {
        this.content = content;
        //this.file = file;
    }

    protected Comment(){

    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

}
