package com.group11.schoolmanagementsystem.subject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSubjectDto {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private int grade;
}
