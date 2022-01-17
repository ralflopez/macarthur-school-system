package com.group11.schoolmanagementsystem.task.dto;

import com.group11.schoolmanagementsystem.enums.TaskType;
import com.group11.schoolmanagementsystem.section.Section;
import com.group11.schoolmanagementsystem.subject.Subject;
import com.group11.schoolmanagementsystem.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String name;
    private String type;
    private int quarter;
    private Long subjectId;
    private Long sectionId;
    private Long teacherId;
}
