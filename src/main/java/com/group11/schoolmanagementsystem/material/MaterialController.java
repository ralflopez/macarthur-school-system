package com.group11.schoolmanagementsystem.material;

import com.group11.schoolmanagementsystem.exception.ApiRequestException;
import com.group11.schoolmanagementsystem.material.dto.CreateMaterialDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/material")
public class MaterialController {
    private MaterialService materialService;
    public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";

    @Autowired
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    // Reference: https://www.youtube.com/watch?v=Hef5pJkNCvA, https://www.youtube.com/watch?v=znjhY71F-8I
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("files") MultipartFile[] files, @RequestParam("teacherId") Long teacherId, @RequestParam("taskId") Long taskId) {
        materialService.addFile(files, teacherId, taskId);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }

    // Reference: https://stackoverflow.com/questions/35680932/download-a-file-from-spring-boot-rest-service
    @GetMapping("/download")
    public ResponseEntity download(@RequestParam("teacherId") Long teacherId, @RequestParam("taskId") Long taskId, @RequestParam("origFileName") String origFileName) {
//        Path path = Paths.get(uploadDirectory, "English/Grade 1 - Section 1/English Teacher/Q1/Written/Chapter 1. Basic Grammar Intro.txt");
        Path path;
        try {
            path = Paths.get(uploadDirectory + "/" + teacherId + "/" + taskId + "/" + origFileName);
        } catch (Exception e) {
            throw new ApiRequestException("Cant Download File");
        }
        File file = path.toFile();

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + origFileName);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        try {
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Error");
        }
    }
}
