package com.dpiotr.web;

import com.dpiotr.model.SystemUser;
import com.dpiotr.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dpiotr on 28.10.17.
 */

@RestController
public class SystemUserController {

    @Autowired
    SystemUserRepository systemUserRepository;

    @GetMapping("/manage_system_users")
    public ModelAndView getSystemGroups() {
        return new ModelAndView("manage_system_users", "system_users", systemUserRepository.findAll());
    }

    @RequestMapping(value = "/systemusers", method = RequestMethod.POST)
    public ResponseEntity<SystemUser> save(@RequestBody SystemUser systemUser) {

        systemUserRepository.save(systemUser);
        return new ResponseEntity<SystemUser>(systemUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/systemusers/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<SystemUser>> getAll() {

        Iterable<SystemUser> result = systemUserRepository.findAll();
        List<SystemUser> systemUsers = new ArrayList<>();
        result.forEach(systemUsers::add);

        return new ResponseEntity<List<SystemUser>>(systemUsers, HttpStatus.OK);
    }

    @RequestMapping(value = "/systemusers/findById", method = RequestMethod.GET)
    public ResponseEntity<SystemUser> findById(@RequestParam("id") long id) {
        SystemUser systemUser = systemUserRepository.findOne(id);
        return new ResponseEntity<SystemUser>(systemUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/systemusers/findByName", method = RequestMethod.GET)
    public ResponseEntity<List<SystemUser>> findByName(@RequestParam("name") String name) {

        Iterable<SystemUser> result = systemUserRepository.findByName(name);
        List<SystemUser> systemUsers = new ArrayList<>();
        result.forEach(systemUsers::add);
        return new ResponseEntity<List<SystemUser>>(systemUsers, HttpStatus.OK);

    }

    @RequestMapping(value = "/systemusers/findBySurname", method = RequestMethod.GET)
    public ResponseEntity<List<SystemUser>> findBySurname(@RequestParam("surname") String surname) {

        Iterable<SystemUser> result = systemUserRepository.findBySurname(surname);
        List<SystemUser> systemUsers = new ArrayList<>();
        result.forEach(systemUsers::add);
        return new ResponseEntity<List<SystemUser>>(systemUsers, HttpStatus.OK);

    }

    @RequestMapping(value = "/systemusers/findByEmail", method = RequestMethod.GET)
    public ResponseEntity<SystemUser> findByEmail(@RequestParam("email") String email) {

        SystemUser result = systemUserRepository.findByEmail(email);
        return new ResponseEntity<SystemUser>(result, HttpStatus.OK);

    }

    @RequestMapping(value = "/systemusers", method = RequestMethod.DELETE)
    public ResponseEntity<List<SystemUser>> delete(@RequestParam("id") Long id) {

        systemUserRepository.delete(id);
        return new ResponseEntity<List<SystemUser>>(HttpStatus.OK);

    }
}

