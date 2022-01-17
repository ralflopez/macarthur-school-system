package com.group11.schoolmanagementsystem.material;

import com.group11.schoolmanagementsystem.exception.ApiRequestException;
import com.group11.schoolmanagementsystem.task.Task;
import com.group11.schoolmanagementsystem.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {
    private MaterialRepository materialRepository;
    private TaskRepository taskRepository;
    public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";

    @Autowired
    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public void addFile(MultipartFile[] files, Long teacherId, Long taskId) {
        List<Material> materials = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            File directory = new File(uploadDirectory + "/" + teacherId + "/" + taskId);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File file = new File(uploadDirectory + "/" + teacherId + "/" + taskId + "/" + multipartFile.getOriginalFilename());
            try {
                multipartFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Optional<Task> task = taskRepository.findById(taskId);
            if (task.isEmpty()) {
                throw new ApiRequestException("Invalid Task Id");
            }

            Material material = Material.builder()
                    .filePath(uploadDirectory + "/" + teacherId + "/" + taskId + "/" + multipartFile.getOriginalFilename())
                    .task(task.get())
                    .build();

            materials.add(material);
        }
        materialRepository.saveAll(materials);
    }
}
