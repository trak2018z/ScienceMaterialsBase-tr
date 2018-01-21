package com.dpiotr.controller;

/**
 * Created by dpiotr on 29.10.17.
 */

import com.dpiotr.model.File;
import com.dpiotr.model.Subject;
import com.dpiotr.repository.FileRepository;
import com.dpiotr.repository.SubjectRepository;
import com.dpiotr.services.LoginService;
import com.dpiotr.session.CurrentSystemUser;
import com.dpiotr.storage.StorageFileNotFoundException;
import com.dpiotr.storage.StorageService;
import com.dpiotr.utilities.AccessForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class FileController {

    @Autowired
    FileRepository fileRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    LoginService loginService;

    @Autowired
    CurrentSystemUser currentSystemUser;

    private final StorageService storageService;

    @Autowired
    public FileController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/v1/all_files")
    public ModelAndView getFiles() {
        if(loginService.userIsLogged()) {
            return new ModelAndView("all_files", "files", fileRepository.findAll());
        } else throw new AccessForbiddenException();
    }

    @GetMapping("/v1/files/by_subject_id")
    public ModelAndView getFilesList(@RequestParam("id") Long id) {
        if(loginService.userIsLogged()) {
            return new ModelAndView("files", "files", fileRepository.findAllBySubjectId(id));
        } else throw new AccessForbiddenException();

    }

    @GetMapping("/v1/all_files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        if(loginService.userIsLogged()) {
            Resource file = storageService.loadAsResource(filename);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        } else throw new AccessForbiddenException();
    }

    @PostMapping("/v1/all_files/subjectid")
    public String handleFileUploadBySubjectId(@RequestParam("file") MultipartFile file,
                                   @RequestParam("id") Long subjectid,
                                   RedirectAttributes redirectAttributes) {
        if(loginService.adminIsLogged()) {
            File fileToSave = new File(file.getName(),file.getOriginalFilename());
            storageService.store(file);
            Subject subject = subjectRepository.findOne(subjectid);
            fileToSave.setSubject(subject);
            fileToSave.setSystemUser(currentSystemUser.getSystemUser());
            Date currentDate = new Date();
            fileToSave.setDateAdded(currentDate);
            subject.setLastModified(currentDate);
            subjectRepository.save(subject);
            if (fileRepository.existsByUrl(fileToSave.getUrl())) {
                redirectAttributes.addFlashAttribute("message",
                        "Taki plik już już istnieje: " + file.getOriginalFilename() + "!");
                return "redirect:/v1/files/by_subject_id?id=" + subjectid;
            }
            fileRepository.save(fileToSave);
            redirectAttributes.addFlashAttribute("message",
                    "Poprawnie dodano " + file.getOriginalFilename() + "!");
            return "redirect:/v1/files/by_subject_id?id=" + subjectid;
        } else throw new AccessForbiddenException();
    }

    @RequestMapping(value = "/v1/all_files/delete", method = RequestMethod.POST)
    public ModelAndView deleteFile(@RequestParam("id") Long id) {
        if(loginService.adminIsLogged()) {
            File file = fileRepository.findOne(id);
            String filename = file.getUrl();
            storageService.deleteOne(filename);
            fileRepository.delete(id);
            return new ModelAndView("redirect:/v1/all_files");
        } else throw new AccessForbiddenException();
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}