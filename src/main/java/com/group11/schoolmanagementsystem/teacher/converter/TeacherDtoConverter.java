package com.group11.schoolmanagementsystem.teacher.converter;

import com.group11.schoolmanagementsystem.teacher.Teacher;
import com.group11.schoolmanagementsystem.teacher.dto.TeacherDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeacherDtoConverter {
    private ModelMapper modelMapper;

    @Autowired
    public TeacherDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TeacherDto teacherToDto(Teacher teacher) {
        TeacherDto teacherDto = modelMapper.map(teacher, TeacherDto.class);
        teacherDto.setDepartmentId(teacher.getDepartment().getId());
        teacherDto.setDepartment(teacher.getDepartment().getName());

        return teacherDto;
    }
}
