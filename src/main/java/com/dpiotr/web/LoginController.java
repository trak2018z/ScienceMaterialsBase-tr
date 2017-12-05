package com.dpiotr.web;

import com.dpiotr.model.viewmodels.LoginViewModel;
import com.dpiotr.services.LoginService;
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
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        if (loginService.userIsLogged()) {
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("login", "model", new LoginViewModel());
    }

    @PostMapping("/login")
    public ModelAndView logUser(@Valid @ModelAttribute("model") LoginViewModel model, final BindingResult result, final RedirectAttributes redirectAttributes) {
        if (loginService.userIsLogged()) {
            return new ModelAndView("redirect:/");
        }

        if (!result.hasErrors()) {
            if (loginService.logUser(model)) {
                redirectAttributes.addFlashAttribute("message", "Jesteś zalogowany.");
                return new ModelAndView("redirect:/");
            } else {
                result.reject("LoginError.incorrectLoginData", "Niepoprawne dane logowania.");
            }
        }
        return new ModelAndView("login", "model", model);

    }

    @RequestMapping("/logout")
    public ModelAndView logout(final RedirectAttributes redirectAttributes) {
        if (!loginService.userIsLogged()) {
            return new ModelAndView("redirect:/");
        }
        loginService.logoutUser();
        redirectAttributes.addFlashAttribute("message", "Wylogowano poprawnie");
        return new ModelAndView("redirect:/");
    }
}