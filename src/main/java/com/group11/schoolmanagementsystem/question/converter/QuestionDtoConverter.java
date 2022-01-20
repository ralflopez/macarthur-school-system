package com.group11.schoolmanagementsystem.question.converter;

import com.group11.schoolmanagementsystem.question.Answer;
import com.group11.schoolmanagementsystem.question.Choice;
import com.group11.schoolmanagementsystem.question.Question;
import com.group11.schoolmanagementsystem.question.dto.QuestionDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionDtoConverter {
    private ModelMapper modelMapper;

    @Autowired
    public QuestionDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public QuestionDto questionToDto(Question question) {
        List<String> choices = new ArrayList<>();
        for (Choice c: question.getChoices()) {
            choices.add(c.getChoice());
        }

        QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);
        questionDto.setChoices(choices);

        return questionDto;
    }
}
