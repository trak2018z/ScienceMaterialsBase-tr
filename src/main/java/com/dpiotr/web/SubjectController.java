package com.dpiotr.web;

import com.dpiotr.model.Subject;
import com.dpiotr.model.viewmodels.SubjectViewModel;
import com.dpiotr.repository.FileRepository;
import com.dpiotr.repository.SubjectRepository;
import com.dpiotr.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by dpiotr on 29.10.17.
 */
@RestController
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    SubjectService subjectService;

    @GetMapping("/manage_subjects")
    public ModelAndView getSubjects() {
        return new ModelAndView("manage_subjects", "subjects", subjectRepository.findAll());
    }

    @GetMapping("/files_by_subject")
    public ModelAndView getSubjectsList() {
        return new ModelAndView("files_by_subject", "subjects", subjectRepository.findAll());
    }

    @GetMapping("/manage_subjects/add")
    public ModelAndView addSubjectForm() {
        return new ModelAndView("add_subject", "subject", new SubjectViewModel());
    }

    @PostMapping("/manage_subjects/add")
    public ModelAndView addSubject(@Valid @ModelAttribute("subject") SubjectViewModel subject, final BindingResult result, final RedirectAttributes redirectAttributes) {
        subjectService.addSubject(subject);
        redirectAttributes.addFlashAttribute("message", "Dodano poprawnie.");
        return new ModelAndView("redirect:/manage_subjects");
    }

    @PostMapping("/manage_subjects/edit")
    public ModelAndView editSubject(@RequestParam("id") Long id, @Valid @ModelAttribute("subject") SubjectViewModel subject, final BindingResult result, final RedirectAttributes redirectAttributes) {
        Subject subjectToUpdate = subjectRepository.findOne(id);
        subjectService.editSubject(subjectToUpdate, subject);
        return new ModelAndView("redirect:/manage_subjects");
    }

    @GetMapping("/manage_subjects/edit")
    public ModelAndView editSubject(@RequestParam("id") Long id) {
        return new ModelAndView("edit_subject", "subject", subjectRepository.findById(id));
    }

    @PostMapping("/manage_subjects/delete")
    public ModelAndView deleteSubject(@RequestParam("id") Long id) {
        subjectRepository.delete(id);
        return new ModelAndView("redirect:/manage_subjects");
    }
}
