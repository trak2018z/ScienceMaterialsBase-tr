package com.dpiotr.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by dpiotr on 29.10.17.
 */
@Entity
@Table(name = "system_group")
public class SystemGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;


    @ManyToMany(mappedBy = "systemGroups", cascade = CascadeType.ALL)
    private Set<Subject> subjects;

    public SystemGroup(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected SystemGroup() {

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

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Long getId() {
        return id;
    }

}

