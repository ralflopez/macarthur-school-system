package com.group11.schoolmanagementsystem.material;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    public String upload(@RequestParam("files") MultipartFile[] files) {
        StringBuilder fileNames = new StringBuilder();
        for (MultipartFile file : files) {
            Path fileNamePath = Paths.get(uploadDirectory, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            try {
                Files.write(fileNamePath, file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "NICE";
    }

    // Reference: https://stackoverflow.com/questions/35680932/download-a-file-from-spring-boot-rest-service
    @GetMapping("/download")
    public ResponseEntity download() {
        Path path = Paths.get(uploadDirectory, "hello.txt");
        File file = path.toFile();

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=hello.txt");
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
