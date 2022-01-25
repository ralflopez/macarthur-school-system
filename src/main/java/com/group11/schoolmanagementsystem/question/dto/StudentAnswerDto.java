package com.group11.schoolmanagementsystem.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAnswerDto {
    private Long questionId;
    private Long choiceId;
}
