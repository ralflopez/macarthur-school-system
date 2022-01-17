package com.group11.schoolmanagementsystem.task.converter;

import com.group11.schoolmanagementsystem.task.Task;
import com.group11.schoolmanagementsystem.task.dto.TaskDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskDtoConverter {
    private ModelMapper modelMapper;

    @Autowired
    public TaskDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TaskDto taskToDto(Task task) {
        TaskDto taskDto = modelMapper.map(task, TaskDto.class);
        taskDto.setSectionId(task.getSection().getId());
        taskDto.setSubjectId(task.getSubject().getId());
        taskDto.setTeacherId(task.getTeacher().getId());

        return taskDto;
    }
}
