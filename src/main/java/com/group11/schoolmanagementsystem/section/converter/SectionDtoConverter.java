package com.group11.schoolmanagementsystem.section.dto;

import com.group11.schoolmanagementsystem.section.Section;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SectionDtoConverter {
    private ModelMapper modelMapper;

    @Autowired
    public SectionDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SectionDto sectionToDto(Section section) {
        SectionDto sectionDto = modelMapper.map(section, SectionDto.class);
        sectionDto.setAdviser(section.getAdviser().getFirstName() + " " + section.getAdviser().getLastName());
        sectionDto.setAdviserId(section.getAdviser().getId());

        return sectionDto;
    }
}
