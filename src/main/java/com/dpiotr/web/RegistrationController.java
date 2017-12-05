package com.dpiotr.web;

import com.dpiotr.model.viewmodels.RegistrationViewModel;
import com.dpiotr.repository.SystemUserRepository;
import com.dpiotr.services.LoginService;
import com.dpiotr.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by dpiotr on 20.11.17.
 */
@RestController
public class RegistrationController {


    private final SystemUserRepository systemUserRepository;
    private LoginService loginService;


    @Autowired
    public RegistrationController(RegistrationService registrationService, SystemUserRepository systemUserRepository, LoginService loginService) {
        this.registrationService = registrationService;
        this.systemUserRepository = systemUserRepository;
        this.loginService = loginService;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        if (loginService.userIsLogged()) {
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("register", "model", new RegistrationViewModel());
    }

    private RegistrationService registrationService;

    @PostMapping("/register")
    public ModelAndView registerUser(@Valid @ModelAttribute("model") RegistrationViewModel model, final BindingResult result, final RedirectAttributes redirectAttributes) {
        if (loginService.userIsLogged()) {
            return new ModelAndView("redirect:/");
        }
        if (model != null) {
            if (systemUserRepository.existsByEmail(model.getEmail())) {
                result.rejectValue("email", "error.RegistrationError", "Konto o takim adresie już istnieje.");

            }
        }

        if (result.hasErrors()) {
            return new ModelAndView("register", "model", model);
        }

        registrationService.registerUser(model);
        redirectAttributes.addFlashAttribute("message", "Zarejestrowano poprawnie. Możesz się zalogować.");

        return new ModelAndView("redirect:/login");
    }
}
