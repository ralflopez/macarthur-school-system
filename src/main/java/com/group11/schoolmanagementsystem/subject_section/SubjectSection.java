package com.group11.schoolmanagementsystem.subject_section;

import com.group11.schoolmanagementsystem.section.Section;
import com.group11.schoolmanagementsystem.subject.Subject;
import com.group11.schoolmanagementsystem.teacher.Teacher;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectSection {
    @EmbeddedId
    SubjectSectionKey subjectSectionKey;

    @ManyToOne
    @MapsId("subjectId")
    @JoinColumn(name = "subject_id")
    Subject subject;

    @ManyToOne
    @MapsId("sectionId")
    @JoinColumn(name = "section_id")
    Section section;

    @OneToOne
    @JoinColumn(name = "teacher_id")
    Teacher teacher;
}
