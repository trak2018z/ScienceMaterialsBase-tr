package com.dpiotr.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by dpiotr on 29.10.17.
 */
@Entity
@Table(name = "subject")
public class Subject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<File> files;

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

    public void setId(Long id) {
        this.id = id;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
