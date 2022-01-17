package com.group11.schoolmanagementsystem.subject_section.dto;

import com.group11.schoolmanagementsystem.section.dto.SectionDto;
import com.group11.schoolmanagementsystem.subject.dto.SubjectDto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectSectionDto {
    private SubjectDto subject;
    private SectionDto section;
}
