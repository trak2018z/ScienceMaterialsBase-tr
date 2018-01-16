package com.dpiotr.controller.rest;

import com.dpiotr.common.View;
import com.dpiotr.model.SystemUser;
import com.dpiotr.repository.SystemUserRepository;
import com.dpiotr.services.SystemUserService;
import com.dpiotr.utilities.PasswordUtilities;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dpiotr on 30.12.17.
 */

@RestController
public class RestSystemUserController {

    @Autowired
    SystemUserRepository systemUserRepository;

    @GetMapping(value = "/rest/manage_system_users/getAll")
    @JsonView(View.withId.class)
    public ResponseEntity<List<SystemUser>> getAll() {
        Iterable<SystemUser> result = systemUserRepository.findAll();
        List<SystemUser> systemUsers = new ArrayList<>();
        result.forEach(systemUsers::add);
        return new ResponseEntity<List<SystemUser>>(systemUsers, HttpStatus.OK);
    }

    @GetMapping(value = "/rest/manage_system_users/findById")
    @JsonView(View.withId.class)
    public ResponseEntity<SystemUser> findById(@RequestParam("id") long id) {
        SystemUser systemUser = systemUserRepository.findOne(id);
        return new ResponseEntity<SystemUser>(systemUser, HttpStatus.OK);
    }

    @GetMapping(value = "/rest/manage_system_users/findByName")
    @JsonView(View.withId.class)
    public ResponseEntity<List<SystemUser>> findByName(@RequestParam("name") String name) {

        Iterable<SystemUser> result = systemUserRepository.findByName(name);
        List<SystemUser> systemUsers = new ArrayList<>();
        result.forEach(systemUsers::add);
        return new ResponseEntity<List<SystemUser>>(systemUsers, HttpStatus.OK);

    }

    @GetMapping(value = "/rest/manage_system_users/findBySurname")
    @JsonView(View.withId.class)
    public ResponseEntity<List<SystemUser>> findBySurname(@RequestParam("surname") String surname) {

        Iterable<SystemUser> result = systemUserRepository.findBySurname(surname);
        List<SystemUser> systemUsers = new ArrayList<>();
        result.forEach(systemUsers::add);
        return new ResponseEntity<List<SystemUser>>(systemUsers, HttpStatus.OK);

    }

    @GetMapping(value = "/rest/manage_system_users/findByEmail")
    @JsonView(View.withId.class)
    public ResponseEntity<SystemUser> findByEmail(@RequestParam("email") String email) {

        SystemUser result = systemUserRepository.findByEmail(email);
        return new ResponseEntity<SystemUser>(result, HttpStatus.OK);

    }

    @PostMapping(value = "/rest/manage_system_users/add")
    @JsonView(View.Default.class)
    public ResponseEntity<SystemUser> save(@RequestBody SystemUser systemUser) {
        systemUser.setPassword(PasswordUtilities.getHashFor(systemUser.getPassword()));
        systemUserRepository.save(systemUser);
        return new ResponseEntity<SystemUser>(systemUser, HttpStatus.OK);
    }

    @DeleteMapping("/rest/manage_system_users/delete")
    public ResponseEntity delete(@RequestParam("id") Long id) {
        systemUserRepository.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
