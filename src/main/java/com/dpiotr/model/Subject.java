package com.dpiotr.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by dpiotr on 29.10.17.
 */
@Entity
@Table(name = "subject")
public class Subject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "subject_to_system_group", joinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "system_group_id", referencedColumnName = "id"))
    private Set<SystemGroup> systemGroups;

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
}
