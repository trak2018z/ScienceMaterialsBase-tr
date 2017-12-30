package com.dpiotr.controller;

import com.dpiotr.model.SystemUser;
import com.dpiotr.model.viewmodels.SystemUserViewModel;
import com.dpiotr.repository.SystemUserRepository;
import com.dpiotr.services.SystemUserService;
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

    @GetMapping("/v1/manage_system_users")
    public ModelAndView getSystemGroups() {
        return new ModelAndView("manage_system_users", "system_users", systemUserRepository.findAll());
    }

    @PostMapping("/v1/manage_system_user/edit")
    public ModelAndView editSubject(@RequestParam("id") Long id, @Valid @ModelAttribute("subject") SystemUserViewModel systemUser, final BindingResult result, final RedirectAttributes redirectAttributes) {
        SystemUser systemUserToUpdate = systemUserRepository.findOne(id);
        //TODO editSystemUser method and SystemUserViewModel
        systemUserService.editSystemUser(systemUserToUpdate, systemUser);
        return new ModelAndView("redirect:/v1/manage_system_users");
    }

    @GetMapping("/v1/manage_system_user/edit")
    public ModelAndView editSubject(@RequestParam("id") Long id) {
        return new ModelAndView("edit_system_user", "system_user", systemUserRepository.findById(id));
    }

    @PostMapping("/v1/manage_system_users/delete")
    public ModelAndView deleteSubject(@RequestParam("id") Long id) {
        systemUserRepository.delete(id);
        return new ModelAndView("redirect:/v1/manage_system_users");
    }

}

