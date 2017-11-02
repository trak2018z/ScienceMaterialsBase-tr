package com.dpiotr.web;

/**
 * Created by dpiotr on 29.10.17.
 */

import com.dpiotr.model.File;
import com.dpiotr.repository.FileRepository;
import com.dpiotr.storage.StorageFileNotFoundException;
import com.dpiotr.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

    @Autowired
    FileRepository fileRepository;

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/upload")
    public String listUploadedFiles(Model model) throws IOException {

        Iterable<File> result = fileRepository.findAll();
        List<File> users = new ArrayList<>();
        result.forEach(users::add);
        List<String> links = users.stream().
                map(File::getUrl).collect(Collectors.toList());
        model.addAttribute("files", links);

        return "upload";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        File fileToSave = new File(file.getName(), "/files/" + file.getOriginalFilename());
        storageService.store(file);
        fileRepository.save(fileToSave);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/upload";
    }

    @DeleteMapping("/deleteFile")
    @ResponseBody
    public ResponseEntity<File> deleteFile(@RequestParam("id") Long id) {

        File file = fileRepository.findOne(id);
        //java.io.File fileToDelete = Paths.get(file.getUrl().substring(7)).toFile();
        String filename = file.getUrl().substring(7);
        storageService.deleteOne(filename);
        fileRepository.delete(id);

        return new ResponseEntity<File>(HttpStatus.OK);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}