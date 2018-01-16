package com.dpiotr.model;

import com.dpiotr.common.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by dpiotr on 29.10.17.
 */
@Entity
@Table(name = "subject")
public class Subject implements Serializable {

    @Id
    @JsonView(View.withId.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @JsonView(View.Default.class)
    @Column(name = "name")
    private String name;

    @JsonView(View.Default.class)
    @Column(name = "description")
    private String description;

    @JsonView(View.Default.class)
    @Column(name = "last_modified")
    private Date lastModified;

    @JsonIgnore
    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<File> files;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(name = "subject_to_system_group", joinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "system_group_id", referencedColumnName = "id"))
    private Set<SystemGroup> systemGroups;

    public Subject(String name, String description, Date lastModified) {
        this.lastModified = lastModified;
        this.name = name;
        this.description = description;
    }

    public Subject(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected Subject() {
    }

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

    public Long getId() {
        return id;
    }

    public Set<SystemGroup> getSystemGroups() {
        return systemGroups;
    }

    public void setSystemGroups(Set<SystemGroup> systemGroups) {
        this.systemGroups = systemGroups;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }
}
