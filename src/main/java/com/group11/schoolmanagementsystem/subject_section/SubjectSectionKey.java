package com.group11.schoolmanagementsystem.subject_section;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectSectionKey implements Serializable {
    @Column(name = "subject_id")
    Long subjectId;
    @Column(name = "section_id")
    Long sectionId;
}
