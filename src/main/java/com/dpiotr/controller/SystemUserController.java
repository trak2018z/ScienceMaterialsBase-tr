package com.dpiotr.controller;

import com.dpiotr.model.Subject;
import com.dpiotr.model.SystemUser;
import com.dpiotr.model.viewmodels.SubjectViewModel;
import com.dpiotr.model.viewmodels.SystemUserViewModel;
import com.dpiotr.repository.SystemUserRepository;
import com.dpiotr.services.LoginService;
import com.dpiotr.services.SystemUserService;
import com.dpiotr.utilities.AccessForbiddenException;
import com.dpiotr.utilities.PasswordUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

@Controller
public class SystemUserController {

    @Autowired
    SystemUserRepository systemUserRepository;

    @Autowired
    SystemUserService systemUserService;

    @Autowired
    LoginService loginService;

    @GetMapping("/v1/manage_system_users")
    public ModelAndView getSystemUsers() {
        if(loginService.adminIsLogged()) {
            return new ModelAndView("manage_system_users", "system_users", systemUserRepository.findAll());
        } else throw new AccessForbiddenException();
    }


    @PostMapping("/v1/manage_system_users/delete")
    public ModelAndView deleteSystemUser(@RequestParam("id") Long id) {
        if(loginService.adminIsLogged()) {
            systemUserRepository.delete(id);
            return new ModelAndView("redirect:/v1/manage_system_users");
        } else throw new AccessForbiddenException();
    }

    @PostMapping("/v1/manage_system_users/edit")
    public ModelAndView editSystemUser(@RequestParam("id") Long id, @Valid @ModelAttribute("system_user") SystemUserViewModel systemUser, final BindingResult result, final RedirectAttributes redirectAttributes) {
        if(loginService.adminIsLogged()) {

            SystemUser systemUserToUpdate = systemUserRepository.findOne(id);
            systemUserService.editSystemUser(systemUserToUpdate, systemUser);
            return new ModelAndView("redirect:/v1/manage_system_users");

        } else throw new AccessForbiddenException();

    }

    @GetMapping("/v1/manage_system_users/edit")
    public ModelAndView editSystemUser(@RequestParam("id") Long id) {
        if(loginService.adminIsLogged()) {
            SystemUser su = systemUserRepository.findById(id);
            su.setPassword("");
            return new ModelAndView("edit_system_user", "system_user", su);
        } else throw new AccessForbiddenException();

    }

}

