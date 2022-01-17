package com.group11.schoolmanagementsystem.subject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignSubjectDto {
    private Long teacherId;
    private Long subjectId;
    private Long sectionId;
}
