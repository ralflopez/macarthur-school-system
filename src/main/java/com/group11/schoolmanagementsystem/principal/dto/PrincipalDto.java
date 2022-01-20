package com.group11.schoolmanagementsystem.principal.dto;

import com.group11.schoolmanagementsystem.school.School;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrincipalDto {
    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;
}
