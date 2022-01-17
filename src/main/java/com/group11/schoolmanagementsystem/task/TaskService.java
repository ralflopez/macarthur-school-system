package com.group11.schoolmanagementsystem.task;

import com.group11.schoolmanagementsystem.enums.TaskType;
import com.group11.schoolmanagementsystem.exception.ApiRequestException;
import com.group11.schoolmanagementsystem.section.Section;
import com.group11.schoolmanagementsystem.subject.Subject;
import com.group11.schoolmanagementsystem.subject_section.SubjectSection;
import com.group11.schoolmanagementsystem.subject_section.SubjectSectionService;
import com.group11.schoolmanagementsystem.task.converter.TaskDtoConverter;
import com.group11.schoolmanagementsystem.task.dto.CreateTaskDto;
import com.group11.schoolmanagementsystem.task.dto.TaskDto;
import com.group11.schoolmanagementsystem.teacher.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
