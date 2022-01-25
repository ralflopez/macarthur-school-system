package com.group11.schoolmanagementsystem.task.converter;

import com.group11.schoolmanagementsystem.question.Question;
import com.group11.schoolmanagementsystem.question.converter.QuestionDtoConverter;
import com.group11.schoolmanagementsystem.task.Task;
import com.group11.schoolmanagementsystem.task.dto.TaskDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskDtoConverter {
    private ModelMapper modelMapper;
    private QuestionDtoConverter questionDtoConverter;

    @Autowired
    public TaskDtoConverter(ModelMapper modelMapper, QuestionDtoConverter questionDtoConverter) {
        this.modelMapper = modelMapper;
        this.questionDtoConverter = questionDtoConverter;
    }

    public TaskDto taskToDto(Task task) {
        TaskDto taskDto = modelMapper.map(task, TaskDto.class);
        taskDto.setSectionId(task.getSection().getId());
        taskDto.setSubjectId(task.getSubject().getId());
        taskDto.setTeacherId(task.getTeacher().getId());
        List<Question> questions = task.getQuestions();
        taskDto.setQuestions(
                questions.stream().map(q -> questionDtoConverter.questionToDto(q)).collect(Collectors.toList())
        );
        return taskDto;
    }
}
