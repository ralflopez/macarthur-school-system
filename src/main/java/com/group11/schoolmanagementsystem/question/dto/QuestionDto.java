package com.group11.schoolmanagementsystem.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    private String question;
    private Long taskId;
    private int point;
    private List<ChoiceDto> choices;
    private String type;
}
