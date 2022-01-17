package com.group11.schoolmanagementsystem.section.dto;

import com.group11.schoolmanagementsystem.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectionDto {
    private Long id;

    private int grade;

    private String name;

    private Long adviserId;

    private String adviser;
}
