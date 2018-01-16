package com.dpiotr.controller;

import com.dpiotr.model.Subject;
import com.dpiotr.model.viewmodels.SubjectViewModel;
import com.dpiotr.repository.SubjectRepository;
import com.dpiotr.services.LoginService;
import com.dpiotr.services.SubjectService;
import com.dpiotr.utilities.AccessForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by dpiotr on 29.10.17.
 */
@Controller
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    LoginService loginService;

    @Autowired
    SubjectService subjectService;

    @GetMapping("/v1/manage_subjects")
    public ModelAndView getSubjects() {
        if(loginService.adminIsLogged()) {
            return new ModelAndView("manage_subjects", "subjects", subjectRepository.findAll());
        } else throw new AccessForbiddenException();
    }

    @GetMapping("/v1/files_by_subject")
    public ModelAndView getSubjectsList() {
        if(loginService.userIsLogged()) {
            return new ModelAndView("files_by_subject", "subjects", subjectRepository.findAll());
        } else throw new AccessForbiddenException();
    }

    @GetMapping("/v1/manage_subjects/add")
    public ModelAndView addSubjectForm() {
        if(loginService.adminIsLogged()) {
            return new ModelAndView("add_subject", "subject", new SubjectViewModel());
        } else throw new AccessForbiddenException();
    }

    @PostMapping("/v1/manage_subjects/add")
    public ModelAndView addSubject(@Valid @ModelAttribute("subject") SubjectViewModel subject, final BindingResult result, final RedirectAttributes redirectAttributes) {
        if(loginService.adminIsLogged()) {
            subject.setLastModified(new Date());
            subjectService.addSubject(subject);
            redirectAttributes.addFlashAttribute("message", "Dodano poprawnie.");
            return new ModelAndView("redirect:/v1/manage_subjects");
        } else throw new AccessForbiddenException();

    }

    @PostMapping("/v1/manage_subjects/edit")
    public ModelAndView editSubject(@RequestParam("id") Long id, @Valid @ModelAttribute("subject") SubjectViewModel subject, final BindingResult result, final RedirectAttributes redirectAttributes) {
        if(loginService.userIsLogged()) {
            Subject subjectToUpdate = subjectRepository.findOne(id);
            subjectService.editSubject(subjectToUpdate, subject);
            return new ModelAndView("redirect:/v1/manage_subjects");
        } else throw new AccessForbiddenException();

    }

    @GetMapping("/v1/manage_subjects/edit")
    public ModelAndView editSubject(@RequestParam("id") Long id) {
        if(loginService.userIsLogged()) {
            return new ModelAndView("edit_subject", "subject", subjectRepository.findById(id));
        } else throw new AccessForbiddenException();

    }

    @PostMapping("/v1/manage_subjects/delete")
    public ModelAndView deleteSubject(@RequestParam("id") Long id) {
        if(loginService.userIsLogged()) {
            subjectRepository.delete(id);
            return new ModelAndView("redirect:/v1/manage_subjects");
        } else throw new AccessForbiddenException();

    }
}
