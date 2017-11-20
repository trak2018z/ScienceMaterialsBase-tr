package com.dpiotr.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by dpiotr on 26.10.17.
 */

@Entity
@Table(name = "system_user")
public class SystemUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //@Column(name = "created_at")
    //private LocalDateTime createdAt;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "systemUser",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Comment> comments;

    public SystemUser(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    protected SystemUser() {

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

    @Override
    public String toString() {
        return String.format("SystemUser[id=%d, firstName=%s, secondName=%s, email=%s]", id, name, surname, email);
    }
}
