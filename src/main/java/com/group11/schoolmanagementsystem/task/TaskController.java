package com.group11.schoolmanagementsystem.task;

import com.group11.schoolmanagementsystem.task.dto.CreateTaskDto;
import com.group11.schoolmanagementsystem.task.dto.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResponseEntity<TaskDto> createTask(@RequestBody CreateTaskDto createTaskDto) {
        TaskDto taskDto = taskService.create(createTaskDto);
        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }
}
