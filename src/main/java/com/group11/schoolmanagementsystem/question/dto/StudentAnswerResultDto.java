package com.group11.schoolmanagementsystem.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentAnswerResultDto {
    private Long questionId;
    private Long choiceId;
    private boolean isCorrect;
}
