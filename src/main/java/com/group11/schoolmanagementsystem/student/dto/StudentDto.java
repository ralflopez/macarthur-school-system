package com.group11.schoolmanagementsystem.student.dto;

import com.group11.schoolmanagementsystem.enums.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class StudentDto {
    private Long lrn;
    private String firstName;
    private String middleName;
    private String lastName;
    private String birthMonth;
    private int birthDay;
    private int birthYear;
    private Gender gender;
    private int grade;
    private Long sectionId;
    private String section;
}
