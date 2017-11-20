package com.dpiotr.model.viewmodels;

import de.malkusch.validation.constraints.EqualProperties;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by dpiotr on 20.11.17.
 */
@EqualProperties(value = {"password", "passwordRepeat"}, message = "{Passwords.dontMatch}")
public class RegistrationViewModel {


    @Pattern(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-+]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "{Email.invalidEmail}")
    @Size(max = 255, message = "{Size.email}")
    private String email;

    @NotBlank(message = "{NotEmpty.message}")
    @Size(max = 32, message = "{Size.firstName}")
    private String firstName;

    @NotBlank(message = "{NotEmpty.message}")
    @Size(max = 32, message = "{Size.lastName}")
    private String lastName;

    @Size(min = 8, message = "{Size.password}")
    private String password;

    @NotEmpty(message = "{NotEmpty.repeatedPassword}")
    private String passwordRepeat;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }
}