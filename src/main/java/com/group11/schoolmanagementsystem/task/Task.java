package com.group11.schoolmanagementsystem.task;

import com.group11.schoolmanagementsystem.enums.TaskType;
import com.group11.schoolmanagementsystem.section.Section;
import com.group11.schoolmanagementsystem.subject.Subject;
import com.group11.schoolmanagementsystem.subject_section.SubjectSection;
import com.group11.schoolmanagementsystem.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private TaskType type;

    @Column(nullable = false)
    private int quarter;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private Section section;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

}
