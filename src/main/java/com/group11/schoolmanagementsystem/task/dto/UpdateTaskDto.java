package com.group11.schoolmanagementsystem.task.dto;

import com.group11.schoolmanagementsystem.question.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskDto {
    private String name;
    private String type;
    private int quarter;
}
