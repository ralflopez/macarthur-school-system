package com.group11.schoolmanagementsystem.material.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMaterialDto {
    @NotBlank
    @NotNull
    private String teacherId;
    @NotBlank
    @NotNull
    private String taskId;
}
