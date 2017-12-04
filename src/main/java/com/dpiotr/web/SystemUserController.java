package com.dpiotr.web;

import com.dpiotr.model.Subject;
import com.dpiotr.model.SystemUser;
import com.dpiotr.model.viewmodels.SubjectViewModel;
import com.dpiotr.model.viewmodels.SystemUserViewModel;
import com.dpiotr.repository.SystemUserRepository;
import com.dpiotr.services.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dpiotr on 28.10.17.
 */

@RestController
public class SystemUserController {

    @Autowired
    SystemUserRepository systemUserRepository;

    @Autowired
    SystemUserService systemUserService;

    @GetMapping("/manage_system_users")
    public ModelAndView getSystemGroups() {
        return new ModelAndView("manage_system_users", "system_users", systemUserRepository.findAll());
    }

    @RequestMapping(value = "/manage_system_users", method = RequestMethod.POST)
    public ResponseEntity<SystemUser> save(@RequestBody SystemUser systemUser) {

        systemUserRepository.save(systemUser);
        return new ResponseEntity<SystemUser>(systemUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/manage_system_users/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<SystemUser>> getAll() {

        Iterable<SystemUser> result = systemUserRepository.findAll();
        List<SystemUser> systemUsers = new ArrayList<>();
        result.forEach(systemUsers::add);

        return new ResponseEntity<List<SystemUser>>(systemUsers, HttpStatus.OK);
    }

    @RequestMapping(value = "/manage_system_users/findById", method = RequestMethod.GET)
    public ResponseEntity<SystemUser> findById(@RequestParam("id") long id) {
        SystemUser systemUser = systemUserRepository.findOne(id);
        return new ResponseEntity<SystemUser>(systemUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/manage_system_users/findByName", method = RequestMethod.GET)
    public ResponseEntity<List<SystemUser>> findByName(@RequestParam("name") String name) {

        Iterable<SystemUser> result = systemUserRepository.findByName(name);
        List<SystemUser> systemUsers = new ArrayList<>();
        result.forEach(systemUsers::add);
        return new ResponseEntity<List<SystemUser>>(systemUsers, HttpStatus.OK);

    }

    @RequestMapping(value = "/manage_system_users/findBySurname", method = RequestMethod.GET)
    public ResponseEntity<List<SystemUser>> findBySurname(@RequestParam("surname") String surname) {

        Iterable<SystemUser> result = systemUserRepository.findBySurname(surname);
        List<SystemUser> systemUsers = new ArrayList<>();
        result.forEach(systemUsers::add);
        return new ResponseEntity<List<SystemUser>>(systemUsers, HttpStatus.OK);

    }

    @RequestMapping(value = "/manage_system_users/findByEmail", method = RequestMethod.GET)
    public ResponseEntity<SystemUser> findByEmail(@RequestParam("email") String email) {

        SystemUser result = systemUserRepository.findByEmail(email);
        return new ResponseEntity<SystemUser>(result, HttpStatus.OK);

    }

    @PostMapping("/manage_system_user/edit")
    public ModelAndView editSubject(@RequestParam("id") Long id, @Valid @ModelAttribute("subject") SystemUserViewModel systemUser, final BindingResult result, final RedirectAttributes redirectAttributes) {
        SystemUser systemUserToUpdate = systemUserRepository.findOne(id);
        //TODO editSystemUser method and SystemUserViewModel
        systemUserService.editSystemUser(systemUserToUpdate, systemUser);
        return new ModelAndView("redirect:/manage_system_users");
    }

    @GetMapping("/manage_system_User/edit")
    public ModelAndView editSubject(@RequestParam("id") Long id) {
        return new ModelAndView("edit_system_user", "system_user", systemUserRepository.findById(id));
    }

    @PostMapping("/manage_system_users/delete")
    public ModelAndView deleteSubject(@RequestParam("id") Long id) {
        systemUserRepository.delete(id);
        return new ModelAndView("redirect:/manage_system_users");
    }

}

