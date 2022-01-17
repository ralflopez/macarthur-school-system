package com.group11.schoolmanagementsystem.subject_section.converter;

import com.group11.schoolmanagementsystem.subject_section.SubjectSection;
import com.group11.schoolmanagementsystem.subject_section.dto.SubjectSectionDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubjectSectionDtoConverter {
    private ModelMapper modelMapper;

    @Autowired
    public SubjectSectionDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SubjectSectionDto subjectSectionToDto(SubjectSection subjectSection) {
        SubjectSectionDto subjectSectionDto = modelMapper.map(subjectSection, SubjectSectionDto.class);
        return subjectSectionDto;
    }
}
