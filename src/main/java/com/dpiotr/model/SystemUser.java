package com.dpiotr.model;


import com.dpiotr.common.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by dpiotr on 26.10.17.
 */

@Entity
@Table(name = "system_user")
public class SystemUser implements Serializable {

    @Id
    @JsonView(View.withId.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @JsonView(View.Default.class)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @JsonView(View.Default.class)
    @Column(name = "surname")
    private String surname;

    @NotNull
    @JsonView(View.Default.class)
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @JsonView(View.Default.class)
    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "systemUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "systemUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<File> files;

    public SystemUser(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public SystemUser() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
