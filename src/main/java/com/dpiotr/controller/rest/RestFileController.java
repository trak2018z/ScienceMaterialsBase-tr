package com.dpiotr.controller.rest;

import com.dpiotr.common.View;
import com.dpiotr.model.File;
import com.dpiotr.repository.FileRepository;
import com.dpiotr.storage.StorageService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dpiotr on 30.12.17.
 */

@RestController
public class RestFileController {

    @Autowired
    FileRepository fileRepository;

    @Autowired
    StorageService storageService;

    @GetMapping("/rest/files/getAll")
    @JsonView(View.withId.class)
    public ResponseEntity<List<File>> getFiles() {
        Iterable<File> result = fileRepository.findAll();
        List<File> files = new ArrayList<>();
        result.forEach(files::add);
        return new ResponseEntity<List<File>>(files, HttpStatus.OK);
    }

    @GetMapping("/rest/files/by_subject_id")
    @JsonView(View.withId.class)
    public ResponseEntity<List<File>> getSubjectsList(@RequestParam("id") Long id) {
        Iterable<File> result = fileRepository.findAllBySubjectId(id);
        List<File> files = new ArrayList<>();
        result.forEach(files::add);
        return new ResponseEntity<List<File>>(files, HttpStatus.OK);
    }

    @DeleteMapping("/rest/files/delete")
    @JsonView(View.withId.class)
    public ResponseEntity delete(@RequestParam("id") Long id) {
        File file = fileRepository.findOne(id);
        String filename = file.getUrl();
        storageService.deleteOne(filename);
        fileRepository.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
