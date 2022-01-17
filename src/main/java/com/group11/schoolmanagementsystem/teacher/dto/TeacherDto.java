package com.group11.schoolmanagementsystem.teacher.dto;

import com.group11.schoolmanagementsystem.enums.SubjectDepartment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private Long departmentId;
    private String department;
}
