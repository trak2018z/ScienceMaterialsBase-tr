package com.dpiotr.web;

/**
 * Created by dpiotr on 29.10.17.
 */

import com.dpiotr.model.File;
import com.dpiotr.model.Subject;
import com.dpiotr.repository.FileRepository;
import com.dpiotr.repository.SubjectRepository;
import com.dpiotr.services.LoginService;
import com.dpiotr.storage.StorageFileNotFoundException;
import com.dpiotr.storage.StorageService;
import com.dpiotr.utilities.AccessForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileController {

    @Autowired
    FileRepository fileRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    LoginService loginService;

    private final StorageService storageService;

    @Autowired
    public FileController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/all_files")
    public ModelAndView getFiles() {
        if(loginService.userIsLogged()) {
            return new ModelAndView("all_files", "files", fileRepository.findAll());
        } else throw new AccessForbiddenException();
    }

    @GetMapping("/files/by_subject_id")
    public ModelAndView getSubjectsList(@RequestParam("id") Long id) {
        return new ModelAndView("files", "files", fileRepository.findAllBySubjectId(id));
    }

    @GetMapping("/all_files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/all_files/subjectid")
    public String handleFileUploadBySubjectId(@RequestParam("file") MultipartFile file,
                                   @RequestParam("id") Long subjectid,
                                   RedirectAttributes redirectAttributes) {
        File fileToSave = new File(file.getName(),file.getOriginalFilename());
        storageService.store(file);
        Subject subject = subjectRepository.findOne(subjectid);
        fileToSave.setSubject(subject);
        fileRepository.save(fileToSave);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/files/by_subject_id?id=" + subjectid;
    }

    @RequestMapping(value = "/all_files/delete", method = RequestMethod.POST)
    public ModelAndView deleteSubject(@RequestParam("id") Long id) {
        File file = fileRepository.findOne(id);
        String filename = file.getUrl();
        storageService.deleteOne(filename);
        fileRepository.delete(id);
        return new ModelAndView("redirect:/all_files");
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}