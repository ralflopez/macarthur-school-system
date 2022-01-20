package com.group11.schoolmanagementsystem.task;

import com.group11.schoolmanagementsystem.task.dto.CreateTaskDto;
import com.group11.schoolmanagementsystem.task.dto.UpdateTaskDto;
import com.group11.schoolmanagementsystem.task.dto.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task")
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

    @PostMapping("/delete/{id}")
    public ResponseEntity<TaskDto> deleteTask(@PathVariable("id") Long id) {
        TaskDto taskDto = taskService.delete(id);
        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    @GetMapping("/subject")
    public ResponseEntity<List<TaskDto>> getTaskBySubjectSection(@RequestParam("subjectId") Long subjectId, @RequestParam("sectionId") Long sectionId) {
        List<TaskDto> taskDto = taskService.findBySubjectSection(sectionId, subjectId);
        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<TaskDto> updateTask(@RequestBody UpdateTaskDto updateTaskDto, @PathVariable("id") Long id) {
        TaskDto taskDto = taskService.update(id, updateTaskDto);
        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }
}
