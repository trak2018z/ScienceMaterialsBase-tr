package com.dpiotr.web;

import com.dpiotr.model.Subject;
import com.dpiotr.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dpiotr on 29.10.17.
 */
@RestController
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @RequestMapping(value = "/addSubject", method = RequestMethod.POST)
    public ResponseEntity<Subject> addSubject(@RequestBody Subject subject) {
        subjectRepository.save(subject);
        return new ResponseEntity<Subject>(subject, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateSubject", method = RequestMethod.POST)
    public ResponseEntity<Subject> updateSubject(@RequestBody Subject subject) {
        //TODO -----Check need creation new Object????---------
        Subject subjectToUpdate = subjectRepository.findOne(subject.getId());
        subjectToUpdate.setName(subject.getName());
        subjectToUpdate.setDescription(subject.getDescription());
        subjectToUpdate.setSystemGroups(subject.getSystemGroups());
        subjectRepository.save(subjectToUpdate);
        return new ResponseEntity<Subject>(subject, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteSubject", method = RequestMethod.DELETE)
    public ResponseEntity<Subject> deleteSubject(@RequestParam("id") Long id) {
        subjectRepository.delete(id);
        return new ResponseEntity<Subject>(HttpStatus.OK);
    }


}
