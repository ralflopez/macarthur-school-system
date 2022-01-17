package com.group11.schoolmanagementsystem.teacher.dto;

import com.group11.schoolmanagementsystem.enums.SubjectDepartment;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateTeacherDto {
    @NotBlank
    @NotNull
    private String firstName;
    private String middleName;

    @NotBlank
    @NotNull
    private String lastName;

    private String username;
    private String password;

    private Long departmentId;
}
