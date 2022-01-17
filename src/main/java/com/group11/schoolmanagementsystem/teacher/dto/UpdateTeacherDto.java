package com.group11.schoolmanagementsystem.teacher.dto;

import com.group11.schoolmanagementsystem.enums.SubjectDepartment;
import lombok.Data;

@Data
public class UpdateTeacherDto {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String password;
    private Long departmentId;
}
