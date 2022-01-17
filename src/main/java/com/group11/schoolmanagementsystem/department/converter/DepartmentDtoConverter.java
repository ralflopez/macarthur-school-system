package com.group11.schoolmanagementsystem.department.converter;

import com.group11.schoolmanagementsystem.department.Department;
import com.group11.schoolmanagementsystem.department.dto.DepartmentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentDtoConverter {
    private ModelMapper modelMapper;

    @Autowired
    public DepartmentDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DepartmentDto departmentToDto(Department department) {
        return modelMapper.map(department, DepartmentDto.class);
    }
}
