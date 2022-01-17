package com.group11.schoolmanagementsystem.department.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    @NonNull
    private Long id;

    private String name;
}
