package com.dpiotr.controller;

import com.dpiotr.model.viewmodels.LoginViewModel;
import com.dpiotr.repository.FileRepository;
import com.dpiotr.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by dpiotr on 20.11.17.
 */
@Controller
public class HomeController {

    @Autowired
    LoginService loginService;

    @Autowired
    FileRepository fileRepository;

    @GetMapping("/")
    public ModelAndView login() {
        if (loginService.userIsLogged()) {
            return new ModelAndView("index","files", fileRepository.findAll());
        }
        return new ModelAndView("index", "model", new LoginViewModel());
    }
}

