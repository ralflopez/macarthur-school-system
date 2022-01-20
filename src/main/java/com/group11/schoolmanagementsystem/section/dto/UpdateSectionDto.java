package com.group11.schoolmanagementsystem.section.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateSectionDto {
    private int grade;

    private String name;

    private Long adviserId;
}
