package com.group11.schoolmanagementsystem.department.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDepartmentDto {
    @NotBlank
    @NonNull
    private String name;
}
