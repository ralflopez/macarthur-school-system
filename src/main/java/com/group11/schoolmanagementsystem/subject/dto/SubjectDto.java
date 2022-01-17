package com.group11.schoolmanagementsystem.subject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto {
    private Long id;

    private String name;

    private int grade;
}
