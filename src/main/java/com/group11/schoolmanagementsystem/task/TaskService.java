package com.group11.schoolmanagementsystem.task;

import com.group11.schoolmanagementsystem.enums.TaskType;
import com.group11.schoolmanagementsystem.exception.ApiRequestException;
import com.group11.schoolmanagementsystem.question.Question;
import com.group11.schoolmanagementsystem.section.Section;
import com.group11.schoolmanagementsystem.subject.Subject;
import com.group11.schoolmanagementsystem.subject_section.SubjectSection;
import com.group11.schoolmanagementsystem.subject_section.SubjectSectionService;
import com.group11.schoolmanagementsystem.task.converter.TaskDtoConverter;
import com.group11.schoolmanagementsystem.task.dto.CreateTaskDto;
import com.group11.schoolmanagementsystem.task.dto.TaskDto;
import com.group11.schoolmanagementsystem.task.dto.UpdateTaskDto;
import com.group11.schoolmanagementsystem.teacher.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    private SubjectSectionService subjectSectionService;
    private TaskDtoConverter taskDtoConverter;

    @Autowired
    public TaskService(TaskRepository taskRepository, SubjectSectionService subjectSectionService, TaskDtoConverter taskDtoConverter) {
        this.taskRepository = taskRepository;
        this.subjectSectionService = subjectSectionService;
        this.taskDtoConverter = taskDtoConverter;
    }

    public TaskDto create(CreateTaskDto createTaskDto) {
        List<SubjectSection> subjectSections = subjectSectionService.getSubjectSectionsBySectionId(createTaskDto.getSectionId());

        Subject subject = null;
        Section section = null;
        Teacher teacher = null;

        for (SubjectSection s : subjectSections) {
            if (createTaskDto.getSubjectId() == s.getSubject().getId()) {
                subject = s.getSubject();
                section = s.getSection();
                teacher = s.getTeacher();
            }
        }

        if (subject == null || section == null || teacher == null) {
            throw new ApiRequestException("Subject Not Found");
        }

        Task task = Task.builder()
                .section(subjectSections.get(0).getSection())
                .subject(subject)
                .teacher(teacher)
                .type(TaskType.valueOf(createTaskDto.getType()))
                .quarter(createTaskDto.getQuarter())
                .name(createTaskDto.getName())
                .build();

        Task savedTask = taskRepository.save(task);

        return taskDtoConverter.taskToDto(savedTask);
    }

    public TaskDto delete(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isEmpty()) {
            throw new ApiRequestException("Task Not Found");
        }

        taskRepository.deleteById(taskId);

        return taskDtoConverter.taskToDto(task.get());
    }

    public List<TaskDto> findBySubjectSection(Long sectionId, Long subjectId) {
        Optional<List<Task>> tasks = taskRepository.findTasksBySection_IdAndSubject_Id(sectionId, subjectId);
        if (tasks.isEmpty()) {
            throw new ApiRequestException("Tasks Not Found");
        }
        return tasks.get().stream().map(t -> taskDtoConverter.taskToDto(t)).collect(Collectors.toList());
    }

    public TaskDto update(Long id, UpdateTaskDto updateTaskDto) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            throw new ApiRequestException("Task Not Found");
        }

        task.get().setName(updateTaskDto.getName());
        task.get().setQuarter(updateTaskDto.getQuarter());
        task.get().setType(TaskType.valueOf(updateTaskDto.getType()));

        return taskDtoConverter.taskToDto(task.get());
    }
}
