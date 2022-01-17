package com.group11.schoolmanagementsystem.student.dto;

import com.group11.schoolmanagementsystem.enums.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UpdateStudentDto {
    @NotNull
    private Long lrn;

    @NotBlank
    private String firstName;

    private String middleName;

    @NotBlank
    private String lastName;

    private int birthMonth;

    private int birthDay;

    private int birthYear;

    @NotBlank
    @NotNull
    private Gender gender;

    @NotNull
    private Long sectionId;

    private String username;
    private String password;
}
