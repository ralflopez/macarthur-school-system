package com.group11.schoolmanagementsystem.student.converter;

import com.group11.schoolmanagementsystem.student.Student;
import com.group11.schoolmanagementsystem.student.dto.StudentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentDtoConverter {
    private ModelMapper modelMapper;

    @Autowired
    public StudentDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public StudentDto studentToDto(Student student) {
        StudentDto studentDto = this.modelMapper.map(student, StudentDto.class);
        studentDto.setSection(student.getSection().getName());
        studentDto.setGrade(student.getSection().getGrade());
        return studentDto;
    }
}
