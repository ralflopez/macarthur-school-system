package com.group11.schoolmanagementsystem.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskDto {
    private String name;
    private String type;
    private int quarter;
    private Long subjectId;
    private Long sectionId;
    private Long teacherId;
}
