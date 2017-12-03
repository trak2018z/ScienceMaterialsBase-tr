package com.dpiotr.web;

import com.dpiotr.model.SystemGroup;
import com.dpiotr.repository.SystemGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by dpiotr on 01.11.17.
 */
@RestController
public class SystemGroupController {

    @Autowired
    SystemGroupRepository systemGroupRepository;

    @GetMapping("/systemgroups")
    public ModelAndView getSystemGroups() {
        return new ModelAndView("systemgroups", "systemgroups", systemGroupRepository.findAll());
    }

    @RequestMapping(value = "/systemgroups", method = RequestMethod.POST)
    public ResponseEntity<SystemGroup> addSystemGroup(@RequestBody SystemGroup systemGroup) {
        systemGroupRepository.save(systemGroup);
        return new ResponseEntity<SystemGroup>(systemGroup, HttpStatus.OK);
    }

    @RequestMapping(value = "/systemgroups", method = RequestMethod.DELETE)
    public ResponseEntity<SystemGroup> deleteSubject(@RequestParam("id") Long id) {
        systemGroupRepository.delete(id);
        return new ResponseEntity<SystemGroup>(HttpStatus.OK);
    }

}
