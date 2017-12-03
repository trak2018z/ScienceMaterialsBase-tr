package com.dpiotr.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by dpiotr on 29.10.17.
 */
@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;

    @NotNull
    @Column(name = "comment_content")
    private String content;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "system_user_id", nullable = false)
    private SystemUser systemUser;

    public Comment(String content, SystemUser systemUser, File file) {
        this.content = content;
    }

    protected Comment() {
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
