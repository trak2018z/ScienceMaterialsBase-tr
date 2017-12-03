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

    @Autowired
    FileRepository fileRepository;

    @GetMapping("/subjects")
    public ModelAndView getSubjects() {
        return new ModelAndView("subjects", "subjects", subjectRepository.findAll());
    }

    @GetMapping("/subjectslist")
    public ModelAndView getSubjectsList() {
        return new ModelAndView("subjectslist", "subjects", subjectRepository.findAll());
    }

    @GetMapping("/subjectslist/byId")
    public ModelAndView getSubjectsList(@RequestParam("id") Long id) {
        return new ModelAndView("fileslist", "files", fileRepository.findAllBySubjectId(id));
    }

    @GetMapping("/subjects/add")
    public ModelAndView addSubjectForm() {
        return new ModelAndView("add_subject", "subject", new SubjectViewModel());
    }

    @PostMapping("/subjects/add")
    public ModelAndView addSubject(@Valid @ModelAttribute("subject") SubjectViewModel subject, final BindingResult result, final RedirectAttributes redirectAttributes) {
        subjectService.addSubject(subject);
        //redirectAttributes.addFlashAttribute("message", "Dodano poprawnie.");
        return new ModelAndView("redirect:/subjects");
    }

    @PostMapping("/subjects/edit")
    public ModelAndView editSubject(@RequestParam("id") Long id, @Valid @ModelAttribute("subject") SubjectViewModel subject, final BindingResult result, final RedirectAttributes redirectAttributes) {
        Subject subjectToUpdate = subjectRepository.findOne(id);
        subjectService.editSubject(subjectToUpdate, subject);
        return new ModelAndView("redirect:/subjects");
    }

    @GetMapping("/subjects/edit")
    public ModelAndView editSubject(@RequestParam("id") Long id) {
        return new ModelAndView("edit_subject", "subject", subjectRepository.findById(id));
    }

    @RequestMapping(value = "/subjects/delete", method = RequestMethod.POST)
    public ModelAndView deleteSubject(@RequestParam("id") Long id) {
        subjectRepository.delete(id);
        return new ModelAndView("redirect:/subjects");
    }

    @RequestMapping(value = "/subjects/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Subject> redirectToListOfSubjects(@RequestParam("id") Long id) {
        subjectRepository.delete(id);
        return new ResponseEntity<Subject>(HttpStatus.OK);
    }

}
