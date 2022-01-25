package com.group11.schoolmanagementsystem.question.converter;

import com.group11.schoolmanagementsystem.question.Question;
import com.group11.schoolmanagementsystem.question.dto.ChoiceDto;
import com.group11.schoolmanagementsystem.question.dto.QuestionDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class QuestionDtoConverter {
    private ModelMapper modelMapper;

    @Autowired
    public QuestionDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public QuestionDto questionToDto(Question question) {
        QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);
        questionDto.setChoices(
                question.getChoices().stream().map(c -> modelMapper.map(c, ChoiceDto.class)).collect(Collectors.toList())
        );

        return questionDto;
    }

}
