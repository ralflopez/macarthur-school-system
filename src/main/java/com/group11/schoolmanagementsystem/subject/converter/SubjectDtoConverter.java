package com.group11.schoolmanagementsystem.subject.converter;

import com.group11.schoolmanagementsystem.subject.Subject;
import com.group11.schoolmanagementsystem.subject.dto.SubjectDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubjectDtoConverter {
    private ModelMapper modelMapper;

    @Autowired
    public SubjectDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SubjectDto subjectToDto(Subject subject) {
        return modelMapper.map(subject, SubjectDto.class);
    }
}
