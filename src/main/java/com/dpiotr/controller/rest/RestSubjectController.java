package com.dpiotr.controller.rest;

import com.dpiotr.common.View;
import com.dpiotr.model.Subject;
import com.dpiotr.repository.SubjectRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dpiotr on 30.12.17.
 */

@RestController
public class RestSubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping(value = "/rest/manage_subjects/getAll")
    @JsonView(View.withId.class)
    public ResponseEntity<List<Subject>> getAll() {
        Iterable<Subject> result = subjectRepository.findAll();
        List<Subject> subjects = new ArrayList<>();
        result.forEach(subjects::add);
        return new ResponseEntity<List<Subject>>(subjects, HttpStatus.OK);
    }

    @GetMapping(value = "/rest/manage_subjects/findById")
    @JsonView(View.withId.class)
    public ResponseEntity<Subject> findById(@RequestParam("id") long id) {
        Subject subject = subjectRepository.findOne(id);
        return new ResponseEntity<Subject>(subject, HttpStatus.OK);
    }

    @GetMapping(value = "/rest/manage_subjects/findByName")
    @JsonView(View.withId.class)
    public ResponseEntity<List<Subject>> findByName(@RequestParam("name") String name) {

        Iterable<Subject> result = subjectRepository.findByName(name);
        List<Subject> subjects = new ArrayList<>();
        result.forEach(subjects::add);
        return new ResponseEntity<List<Subject>>(subjects, HttpStatus.OK);

    }

    @PostMapping(value = "/rest/manage_subjects/add")
    @JsonView(View.Default.class)
    public ResponseEntity<Subject> save(@RequestBody Subject subject) {
        subjectRepository.save(subject);
        return new ResponseEntity<Subject>(subject, HttpStatus.OK);
    }

    @DeleteMapping("/rest/manage_subjects/delete")
    public ResponseEntity delete(@RequestParam("id") Long id) {
        subjectRepository.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
