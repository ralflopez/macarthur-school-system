package com.group11.schoolmanagementsystem.subject;

import com.group11.schoolmanagementsystem.section.Section;
import com.group11.schoolmanagementsystem.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne()
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToOne()
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;
}
