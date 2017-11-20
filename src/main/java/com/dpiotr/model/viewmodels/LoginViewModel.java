package com.dpiotr.model.viewmodels;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by dpiotr on 20.11.17.
 */
public class LoginViewModel {
    @NotBlank(message = "{NotEmpty.message}")
    private String email;

    @NotBlank(message = "{NotEmpty.message}")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}